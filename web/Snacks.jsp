<%@page import="com.freshtrack.Product"%>
<%@page import="com.freshtrack.ProductDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Explore our wide range of fresh dairy products, including milk, cheese, and yogurt.</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <!-- Message to pop when Product is added to cart -->
    <div id="toast" class="toast"></div>
    
    <nav class="navbar">
        <div class="logo">
            <h2 class="fresh">Fresh</h2>
            <h2 class="track">Track</h2>
        </div>
        <div class="search-container">
            <i class="fas fa-search"></i>
            <input type="text" placeholder="Search Here" class="search-input" id="searchInput">
        </div>
        <a href="account.jsp">Account</a>
        <a href="adminLogin.jsp">Admin</a>
        <button class="cart-button" onclick="window.location.href='Cart.jsp';">
            <i class="fa-solid fa-cart-shopping"></i>
            My Cart
            <span id="cart-badge" class="badge">0</span>
        </button>
    </nav>
    <section class="items">
        <%
            ProductDAO productDAO = new ProductDAO();
            List<Product> dairyProducts = productDAO.getProductsByCategory(5); // Assuming 1 is the category ID for Dairy
            for (Product product : dairyProducts) {
        %>
        <div class="product-card" data-name="<%= product.getProductName() %>" data-price="<%= product.getPrice() %>">
            <img src="<%= product.getImageUrl() %>" alt="<%= product.getProductName() %>">
            <p><strong><%= product.getProductName() %></strong></p>
            <p><%= product.getDescription() %></p>
            <div class="price">
                <p>₹<%= product.getPrice() %></p>
                <form action="AddToCartServlet" method="post">
                    <input type="hidden" name="currentPage" value="Snacks.jsp">
                    <input type="hidden" name="productName" value="<%= product.getProductName () %>">
                    <input type="hidden" name="productPrice" value="<%= product.getPrice() %>">
                    <button type="submit" class="add-to-cart">ADD</button>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </section>
    <script src="index.js"></script>
</body>
</html>