<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.freshtrack.CategoryDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.freshtrack.Category"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Product.css">
    <title>Add Product</title>
</head>
<body>
    <div class="container">
        <h1>Add Product</h1>
        <form action="addProduct" method="post" enctype="multipart/form-data">
            <label>Product Name:</label><br>
            <input type="text" name="productName" required><br>
            <label>Description:</label><br>
            <input type="text" name="description" required><br>
            <label>Price:</label><br>
            <input type="number" name="price" step="0.01" required><br>
            <label>Image:</label><br>
            <input type="file" id="image" name="image" required><br>
            <label>Category:</label><br>
            <select name="categoryId" required>
                <option value="">Select a category</option> <!-- Placeholder option -->
                <%
                    // Create an instance of CategoryDAO
                    CategoryDAO categoryDAO = new CategoryDAO();

                    // Fetch categories from the database
                    System.out.println("Fetching categories...");
                    List<Category> categoryList = categoryDAO.getAllCategories();

                    // Log the number of categories retrieved
                    System.out.println("Categories retrieved: " + (categoryList != null ? categoryList.size() : 0));

                    // Check if the category list is not null and not empty
                    if (categoryList != null && !categoryList.isEmpty()) {
                        for (Category category : categoryList) {
                %>
                    <option value="<%= category.getCategoryId() %>"><%= category.getCategoryName() %></option>
                <%
                        }
                    } else {
                %>
                    <option value="">No categories available</option> <!-- Message if no categories -->
                <%
                    }
                %>
            </select><br>
            <button type="submit">Add Product</button>
        </form>
        <a href="ProductList.jsp">Back to Product List</a>
    </div>
</body>
</html>