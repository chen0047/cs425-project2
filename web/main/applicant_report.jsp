<%-- 
    Document   : applicant_report
    Created on : 18-十一月-19, 下午 02:32
    Author     : 陈宸
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="applicant" class="edu.jsu.mcis.cs425.project2.BeanApplicant" scope="session"/>
<jsp:setProperty name="applicant" property="*"/>
    <%
        if(applicant.getJobs() != null){
            applicant.setJobsList();
        }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Job Report</title>
    </head>
    <body>
        <%
            response.sendRedirect(request.getContextPath() + "/jobreport");
        %>
    </body>
</html>
