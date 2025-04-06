package com.freshtrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
    public AdminServlet() 
    {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("Username: "+ username);
        System.out.println("Password: "+ password);

        UserDao1 userDao = new UserDao1();
        try {
            User user = userDao.checkLogin(username, password);
            if (user != null) 
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("admin.jsp"); // Redirect to home page
            } else {
                response.sendRedirect("adminLogin.jsp?error=1"); // Redirect back to login with error
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            response.sendRedirect("adminLogin.jsp?error=2"); // Redirect back to login with error
        }
    }
}

