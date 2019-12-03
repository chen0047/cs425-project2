<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <title>Log In</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/public/style/login.css">
        <!-- the path can also be "../public/style/login.css", but the reuqest can provide the absolute path-->
    </head>

    <body >

        <form id="loginform" name="loginform" method="POST" action="j_security_check" accept-charset="UTF-8">
         <!-- Insert your form elements here -->
                <fieldset>
                    <legend>Log In</legend>
                        <label for="j_username">Username:</label> 
                        <input type="text" id="j_username" name="j_username" tabindex=1></br>
                        <p></p>
                        <label for="j_password">Password:</label> 
                        <input type="password" id="j_password" name="j_password" tabindex=2></br>
                        <p></p>
                        <input type="submit" value="Submit">

                </fieldset>
        </form>
        
        <p></p>
        <b>How to Log In</b>

        <ul>
            <li>Enter the Username and Password that you were provided.</li>                    
        </ul>

        <%

            String result = request.getParameter("error");

            if (result != null) {

        %>

        <div id="loginerror" style="color: red;">

            <b>There was a problem processing your login request.</b>

            <ul>
                <li>Please try entering your Username and Password again.</li>
            </ul>

        </div>

        <%

            }

        %>
        

    </body>

</html>