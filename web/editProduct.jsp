<%@page import="com.freshtrack.Product"%>
<%@page import="com.freshtrack.ProductDAO"%>
<%@page import="com.freshtrack.CategoryDAO"%>
<%@page import="com.freshtrack.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int productId = Integer.parseInt(request.getParameter("id"));
    ProductDAO productDAO = new ProductDAO();
    Product product = productDAO.getProductById(productId);
    
    // Fetch categories for the dropdown
    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> categories = categoryDAO.getAllCategories(); // Assuming this method exists
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Product.css">
    <title>Edit Product</title>
</head>
<body>
    <div class="container">
        <h1>Edit Product</h1>
        <form action="editProduct" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="<%= product.getProductId() %>">
            
            <label>Product Name:</label><br>
            <input type="text" name="productName" value="<%= product.getProductName() %>" required><br>
            
            <label>Description:</label><br>
            <input type="text" name="description" value="<%= product.getDescription() %>" required><br>
            
            <label>Price:</label><br>
            <input type="number" name="price" value="<%= product.getPrice() %>" step="0.01" required><br>
            
            <label>Category:</label><br>
            <select name="categoryId" required>
                <option value="">Select a category</option>
                <%
                    for (Category category : categories) {
                %>
                    <option value="<%= category.getCategoryId() %>" <%= (category.getCategoryId() == product.getCategoryId()) ? "selected" : "" %> >
                        <%= category.getCategoryName() %>
                    </option>
                <%
                    }
                %>
            </select><br>
            
            <label>Image:</label><br>
            <input type="file" name="image"><br> <!-- Optional image upload -->
            
            <button type="submit">Update Product</button>
        </form>
        <a href="ProductList.jsp">Back to Product List</a>
    </div>
</body>
</html>