<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Skills</title>
        <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/public/style/skill.css">
        <!-- the path can also be "../public/style/skill.css", but the reuqest can provide the absolute path-->
            
            
    </head>
    <body>
        <form id="skillsform" name="skillsform" method="post" action="applicant_jobs.jsp">
            <fieldset>
                <legend>Select Your Skills:</legend>
                <jsp:getProperty name="applicant" property="skillsList" /></br>
                <input type="submit" value="Submit" />
                <p></p>
                <a href="applicant_main.jsp">Return to User Home Page</a> </br><br/>
                <a href="<%= request.getContextPath() %>/public/logout.jsp" target="_self">Log Out</a><br/>
                
            </fieldset>    
        </form>
       
    </body>
</html>
