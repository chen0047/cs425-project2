<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Skills</title>
    </head>
    <body>
        <form id="skillsform" name="skillsform" method="post" action="applicant_jobs.jsp">
            <fieldset>
                <legend>Select Your Skills:</legend>
                <jsp:getProperty name="applicant" property="skillsList" /></br>
                <input type="submit" value="Submit" />
                <p></p>
                <a href="applicant_main.jsp">Return to User Home Page</a> 
                <a href="<%= request.getContextPath() %>/public/logout.jsp" target="_self">Log Out</a>
                
            </fieldset>    
        </form>
       
    </body>
</html>
