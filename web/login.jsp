<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login - Access Your Account</title>
	<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
	<div class="container">
        <h1>Login</h1>
        <form action="LoginServlet" method="post"> <!-- Change method to "post" -->
            <label for="email">E-Mail</label>
            <input type="email" id="email" name="email" required><br>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>

        <p><a href="index.html">Back to Home</a></p>
        
        <%-- Display error message if login fails --%>
        <% String error = request.getParameter("error");
            if (error != null) { %>
                <p style="color: red;">Invalid email or password. Please try again.</p>
        <% } %>
        
     </div>
</body>
</html>