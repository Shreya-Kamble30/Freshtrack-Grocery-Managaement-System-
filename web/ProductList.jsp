<%@page import="com.freshtrack.Product"%>
<%@page import="com.freshtrack.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<a href="addProduct.jsp">Add New Product</a>
<table>
    <tr>
        <th>Product Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <%
        ProductDAO productDAO = new ProductDAO();
 List<Product> productList = productDAO.getAllProducts();
        for (Product product : productList) {
    %>
    <tr>
        <td><%= product.getProductName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getPrice() %></td>
        <td>
            <a href="editProduct.jsp?id=<%= product.getProductId() %>">Edit</a>
            <a href="deleteProduct?id=<%= product.getProductId() %>" onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<a href="admin.jsp">Back to Admin Dashboard</a>
</body>
</html>