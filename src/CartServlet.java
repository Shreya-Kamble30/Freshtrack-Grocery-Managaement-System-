package com.freshtrack;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if ("remove".equals(action)) {
            String productName = request.getParameter("productName");
            List<HashMap<String, Object>> cart = (List<HashMap<String, Object>>) session.getAttribute("cart");
            if (cart != null) {
                Iterator<HashMap<String, Object>> iterator = cart.iterator();
                while (iterator.hasNext()) {
                    HashMap<String, Object> item = iterator.next();
                    if (item.get("name").equals(productName)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        response.sendRedirect("Cart.jsp");
    }
}