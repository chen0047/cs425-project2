package edu.jsu.mcis.cs425.project2;

import java.util.HashMap;

public class BeanApplicant {
    
    private String username;
    private String displayname;
    private int userid;
    
    private String[] skills;
    private String[] jobs;
    
    public String getSkillsList(){
    
        Database db = new Database();
        return (db.getSkillsListAsHTML(userid));
    }
    
    public void setSkillsList(){   //visible at jsp
        
        Database db = new Database();
        db.setSkillsList(userid, skills);
        
    }
    
    public String getJobsList(){
        Database db = new Database();
        return (db.getJobsListAsHTML(userid));
    }
    
    public void setJobsList(){
        Database db = new Database();
        db.setJobsList(userid, jobs);
    }

    public void setUserInfo(){ //no return on caller
        
        Database db = new Database();
        HashMap<String,String> userinfo = db.getUserInfo(username);
        userid = Integer.parseInt(userinfo.get("id"));
        displayname = userinfo.get("displayname");
       
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }
    
     public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getJobs() {
        return jobs;
    }

    public void setJobs(String[] jobs) {
        this.jobs = jobs;
    }
    
    
}