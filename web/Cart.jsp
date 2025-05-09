<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart - Fresh Track</title>
    <link rel="stylesheet" type="text/css" href="Cart.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <nav class="navbar">
        <div class="logo">
            <h2 class="fresh">Fresh</h2>
            <h2 class="track">Track</h2>
        </div>
        <a href="index.jsp">Home</a>
        <a href="account.jsp">Account</a>
        <button class="cart-button" onclick="window.location.href='Cart.jsp';">
            <i class="fa-solid fa-cart-shopping"></i>
            My Cart
            <span id="cart-badge" class="badge">0</span>
        </button>
    </nav>
    <h3>Your Cart</h3>
    <div id="cart-items">
        <%
            if (session != null) {
                List<HashMap<String, Object>> cart = (List<HashMap<String, Object>>) session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
                    double totalPrice = 0;
                    for (HashMap<String, Object> item : cart) {
                        String productName = (String) item.get("name");
                        double productPrice = (double) item.get("price");
                        totalPrice += productPrice;
        %>
        <div class="cart-item">
            <p><%= productName %> - ₹<%= productPrice %></p>
            <form action="CartServlet" method="post">
                <input type="hidden" name="productName" value="<%= productName %>">
                <input type="hidden" name="action" value="remove">
                <button type="submit" class="remove">Remove</button>
            </form>
        </div>
        <%
                    }
        %>
        <div class="total">
            <h2>Total: ₹<%= totalPrice %></h2>
        </div>
        <%
                } else {
        %>
        <p>Your cart is empty.</p>
        <%
                }
            } else {
        %>
        <p>Please log in to view your cart.</p>
        <%
            }
        %>
    </div>
    <script src="Cart.js"></script>
</body>
</html>