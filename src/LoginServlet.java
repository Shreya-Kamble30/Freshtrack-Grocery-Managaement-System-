package com.freshtrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() 
    {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("Email: "+ email);
        System.out.println("Password: "+ password);

        UserDao userDao = new UserDao();
        try {
            User user = userDao.checkLogin(email, password);
            if (user != null) 
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("index.jsp"); // Redirect to home page
            } else {
                response.sendRedirect("login.jsp?error=1"); // Redirect back to login with error
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            response.sendRedirect("login.jsp?error=2"); // Redirect back to login with error
        }
    }
}

