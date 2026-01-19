<%-- 
    Document   : login
    Created on : Jan 19, 2026, 10:12:26 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/> 
            UserName: <input type="text" name="textUsername"/> <br/>
            Password: <input type="password" name="textPassword"/> <br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
