<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Login - Fresh Track</title>
	<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
	<div class="container">
        <h1>Admin Login</h1>
        <form action="AdminServlet" method="post"> <!-- Change method to "post" -->
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>

        <p><a href="index.jsp">Back to Home</a></p>
        
        <%-- Display error message if login fails --%>
        <% String error = request.getParameter("error");
            if (error != null) { %>
                <p style="color: red;">Invalid username or password. Please try again.</p>
        <% } %>
        
     </div>
</body>
</html>