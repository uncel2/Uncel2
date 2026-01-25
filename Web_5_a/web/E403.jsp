<%-- 
    Document   : E403
    Created on : Jan 19, 2026, 10:40:16 AM
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
        <h1>HTTP Status 403 - Access is denied</h1>
        <p>Tài khoản của bạn đã bị khóa hoặc không có quyền truy cập.</p>
        
        <h2 style = "color: red" > 
            ${requestScope.ERROR_DETAILS}
        </h2>
        <form> <input type="submit" value="Login"/> </form>
    </body>
</html>
