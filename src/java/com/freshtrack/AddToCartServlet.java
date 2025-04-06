package com.freshtrack;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Get product details from the request
        String productName = request.getParameter("productName");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));

        // Create cart if it doesn't exist
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList<HashMap<String, Object>>());
        }
        
        // Add product to cart
        ArrayList<HashMap<String, Object>> cart = (ArrayList<HashMap<String, Object>>) session.getAttribute("cart");
        HashMap<String, Object> cartItem = new HashMap<>();
        cartItem.put("name", productName);
        cartItem.put("price", productPrice);
        cart.add(cartItem);
        
        String currentPage = request.getParameter("currentPage");
        // Redirect back to the Dairy page or Cart page
        response.sendRedirect(currentPage); // Redirect to the Dairy page
    }
}