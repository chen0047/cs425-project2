<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session" />
<jsp:setProperty name="applicant" property="*" />

<%
    if(applicant.getSkills() != null){
        applicant.setSkillsList();
    }
%>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Jobs</title>
        <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/public/style/job.css">
        <!-- the path can also be "../public/style/job.css", but the reuqest can provide the absolute path-->
    </head>
    <body>
        <form id="jobsform" name="jobsform" method="post" action="applicant_report.jsp">
            <fieldset>
                <legend>Select Your Job(s):</legend>
                <jsp:getProperty name="applicant" property="jobsList" />
                <input type="submit" value="Submit" />
                </br>
                </br>
                <a href="applicant_skills.jsp">Return to Skill Page</a> </br><br/>
                <a href="applicant_main.jsp">Return to User Home Page</a> </br><br/>
                <a href="<%= request.getContextPath() %>/public/logout.jsp" target="_self">Log Out</a><br/>
            </fieldset>
            
        </form>    
    </body>
</html>
