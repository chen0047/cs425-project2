<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session" />
<jsp:setProperty name="applicant" property="username" value="<%= request.getRemoteUser() %>" />
<%applicant.setUserInfo(); %>

<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/public/style/main.css"> 
        <!-- the path can also be "../public/style/main.css", but the reuqest can provide the absolute path-->
        <title>Applicant Welcome Page</title>
    </head>
    
    <body>
        <h1>Hello, <jsp:getProperty name="applicant" property="displayname" />!</h1>  
        <!-- change property from 'username' to 'displayname' -->
        <div>
            <a href="<%= request.getContextPath() %>/main/applicant_skills.jsp" target="_self"class="skill">Select Skills</a></br><br/>
            <a href="<%= request.getContextPath() %>/main/applicant_jobs.jsp" target="_self"class="job">Select Jobs</a></br><br/>
            <a href="<%= request.getContextPath() %>/main/applicant_report.jsp" target="_self"class="report">Create Report</a></br><br/>
            <a href="<%= request.getContextPath() %>/public/logout.jsp" target="_self"class="logo">Log Out</a><br/>
        </div>

    </body>
    
</html>