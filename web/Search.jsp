<%@page import="com.freshtrack.Product"%>
<%@ page import="java.util.List" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<link rel="stylesheet" type="text/css" href="style.css">
<nav class="navbar">
    <div class="logo">
            <h2 class="fresh">Fresh</h2>
            <h2 class="track">Track</h2>
    </div>
</nav>
<section class="items">
    <%
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
    %>
                <div class="product-card" data-name="<%= product.getProductName() %>" data-price="<%= product.getPrice() %>">
                    <img src="<%= product.getImageUrl() %>" alt="<%= product.getProductName() %>">
                    <p><strong><%= product.getProductName() %></strong></p>
                    <p><%= product.getDescription() %></p>
                    <div class="price">
                        <p><%= product.getPrice() %></p>
                        <button class="add-to-cart">ADD</button>
                    </div>
                </div>
    <%
            }
        } else {
    %>
            <p>No products found.</p>
    <%
        }
    %>
</section>