package edu.jsu.mcis.cs425.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class databaseAttempt1 {
    /*public String _getResultSetTable(ResultSet resultset) {
       
       String table = "";
       String tablerow = "";
       
       String skillsList = "";
       
       try{
           
           table += "<table >";
           
            while(resultset.next()){
               
                tablerow = "<tr>";

                int skillsId = resultset.getInt("id");
                String description = resultset.getString("description");
                
                skillsList = "<input type=\"checkbox\" name=\"skills\" value=\""
                        + skillsId + "\" id=\"skills_\"" + skillsId + "\">" 
                        + "<label for=\"skills_\"" + skillsId + "\">"+ description + "</label><br/>";

                tablerow += "<td>" + skillsList + "</td>";

                tablerow += "</tr>";

                table += tablerow;
                   
            }
           
           table += "</table><br />";
           
           
       }catch (Exception e){ e.printStackTrace(); }
       
       return table;
       
    }    
     public String _getSkillsListAsHTML(int id){
        
        String skillsResult = "";
        
        Connection conn = getConnection();
        PreparedStatement pstatement = null;
        ResultSet resultset = null;
        
        String query = "";
        
        boolean hasresult;
        
        try{
            
            query = "SELECT * FROM skills";
            
            pstatement = conn.prepareStatement(query);
            //pstatement.setInt(1, id);
            
            hasresult = pstatement.execute();

            if(hasresult) {
                
                resultset = pstatement.getResultSet();
                //skillsResult = getResultSetTable(resultset);
                resultset.close();
              
            }
            
            pstatement.close();            
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return skillsResult;
     
    }*/
    
}
