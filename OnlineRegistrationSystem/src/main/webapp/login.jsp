<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Don't have account?<a href="registration.jsp">register</a></h3>
	<h2>Login</h2>
	<form action="LoginServlet" method="post">
		Email: <input type="text" name="email"><br>
		Password: <input type="password"
		name="password"><br>
		<input type="submit" value="Login">
	</form>
</body>
</html>