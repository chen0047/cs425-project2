package edu.jsu.mcis.cs425.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
    
    public Connection getConnection() {
        Connection conn = null;
        try {
            Context envContext = new InitialContext();
            Context initContext  = (Context)envContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)initContext.lookup("jdbc/db_pool");
            conn = ds.getConnection();
          
        }        
        catch (Exception e) { e.printStackTrace(); }
        
        return conn;
    }
    
    public HashMap getUserInfo(String username){
        
        HashMap<String,String> results = null;
           
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query = "";
        
        boolean hasresult;
        
        try{
            
            query = "SELECT * FROM user WHERE username=?";
            
            pstatement = conn.prepareStatement(query);
            pstatement.setString(1, username);
            
            hasresult = pstatement.execute();

            if(hasresult) {
                resultset = pstatement.getResultSet();
                
                if(resultset.next()){
                    //Initialize HashMap; add user data from resultset
                    results = new HashMap<>();
                    //use key name 'id'for the ID, and 'displayname' for the displayname
                    results.put("id", String.valueOf(resultset.getInt("id")));
                    results.put("displayname", resultset.getString("displayname"));
    
                    resultset.close();
                }
            }
            
            pstatement.close();            
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return results;
    }
    
    public String getSkillsListAsHTML(int id){
       
        StringBuilder s = new StringBuilder();
       
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query = "";
        
        boolean hasresult;
        
        try{
            
            query = "SELECT skills.*, a.userid\n" +
                    "FROM skills LEFT JOIN (SELECT * FROM applicants_to_skills WHERE userid =?) AS a\n" +
                    "ON skills.id = a.skillsid ORDER BY description";
            
            pstatement = conn.prepareStatement(query);
            pstatement.setInt(1, id);
       
            hasresult = pstatement.execute();

            if(hasresult) {
                
                resultset = pstatement.getResultSet();
                
                while(resultset.next()){

                    int skillsId = resultset.getInt("id");
                    String description = resultset.getString("description");
                    int userid = resultset.getInt("userid");

                    s.append("<input type=\"checkbox\" name=\"skills\" value=\"");
                    s.append(skillsId);
                    s.append("\" id=\"skills_").append(skillsId).append("\" ");
                    
                     //is the checkbox checked? If so, the attribute nees to go here
                    if(userid != 0){
                        s.append("checked");
                    }
                   
                    s.append(">\n");

                    s.append("<label for=\"skills_").append(skillsId).append("\">");
                    s.append(description).append("</label><br/>\n\n");
                }
            }
           
           
           
       }catch (Exception e){ e.printStackTrace(); }
       
       return s.toString();
       
    } 
    
    public void setSkillsList(int userid, String[] skills){
        
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query = "";
        int[] results = null;
        
        try{
            
            query = "DELETE FROM applicants_to_skills WHERE userid = ?";
            
            pstatement = conn.prepareStatement(query);
            pstatement.setInt(1, userid);
            pstatement.executeUpdate();
            
            query = "INSERT INTO applicants_to_skills (userid, skillsid) VALUES (?,?)";
            
            pstatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            for(int i = 0; i < skills.length; ++i){
                pstatement.setInt(1, userid);
                pstatement.setInt(2, Integer.parseInt(skills[i]));
                pstatement.addBatch();
            }
            
            results = pstatement.executeBatch();
            conn.commit();
            pstatement.close();
           
            //batch?query = "UPDATE applicants_to_skills SET skillsid = ? WHERE userid = ?";
           
        }catch (Exception e){e.printStackTrace();}
        
    }
    
    public String getJobsListAsHTML(int id){
        
        StringBuilder j = new StringBuilder();
       
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query = "";
        
        boolean hasresult;
        
        try{
            query = "SELECT jobs.id, jobs.name, a.userid FROM jobs\n" +
                    "LEFT JOIN (SELECT * FROM applicants_to_jobs WHERE userid=?)\n" +
                    "AS a ON jobs.id = a.jobsid\n" +
                    "WHERE jobs.id IN (SELECT jobsid AS id FROM\n" +
                    "(applicants_to_skills JOIN skills_to_jobs\n" +
                    "ON applicants_to_skills.skillsid = skills_to_jobs.skillsid)\n" +
                    "WHERE applicants_to_skills.userid = ?)\n" +
                    "ORDER BY jobs.name";
            
            /*SELECT a.userid, j.name, s.skillsid
            FROM (applicants_to_skills a JOIN skills_to_jobs s
            ON a.skillsid = s.skillsid)
            JOIN jobs j ON s.jobsid = j.id;*/
            
            pstatement = conn.prepareStatement(query);
            pstatement.setInt(1, id);
            pstatement.setInt(2, id);
       
            hasresult = pstatement.execute();

            if(hasresult) {
                
                resultset = pstatement.getResultSet();
                
                while(resultset.next()){
                    
                    int jobsid = resultset.getInt("id");
                    int userId = resultset.getInt("userid");
                    String name = resultset.getString("name");

                    j.append("<input type=\"checkbox\" name=\"jobs\" value=\"");
                    j.append(jobsid);
                    j.append("\" id=\"jobs_").append(jobsid).append("\" ");
                    
                     //is the checkbox checked? If so, the attribute needs to go here
                    if(userId != 0){
                        j.append("checked");
                    }
                   
                    j.append(">\n");

                    j.append("<label for=\"jobs_").append(jobsid).append("\">");
                    j.append(name).append("</label><br/>\n\n");
                }
            }
            
        }catch (Exception e){e.printStackTrace();};
        
        return j.toString();
    } 
    
    public void setJobsList(int userid, String[] jobs){
        
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query = "";
        int[] results = null;
        
        try{
            
            query = "DELETE FROM applicants_to_jobs WHERE userid = ?";
            
            pstatement = conn.prepareStatement(query);
            pstatement.setInt(1, userid);
            pstatement.execute();
            
            query = "INSERT INTO applicants_to_jobs (userid, jobsid) VALUES (?,?)";
            
            pstatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            for(int i = 0; i < jobs.length; ++i){
                pstatement.setInt(1, userid);
                pstatement.setInt(2, Integer.parseInt(jobs[i]));
                pstatement.addBatch();
            }
            
            results = pstatement.executeBatch();
            conn.commit();
            pstatement.close();
           
            //batch?query = "UPDATE applicants_to_skills SET skillsid = ? WHERE userid = ?";
           
        }catch (Exception e){e.printStackTrace();};
      
    }
}
