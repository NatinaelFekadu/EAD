<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome Page</title>
</head>
<body>
    <%
        HttpSession sesn = request.getSession(false);
        if (sesn != null) {
            String username = (String) sesn.getAttribute("username");
    %>
    <h1>Welcome, <%= username %>!</h1>
    <%
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login page if session is not available
        }
    %>
</body>
</html>
