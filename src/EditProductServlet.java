package com.freshtrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProduct")
@MultipartConfig // Enable file upload
public class EditProductServlet extends HttpServlet {
   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int productId = Integer.parseInt(request.getParameter("productId"));
    String productName = request.getParameter("productName");
    String description = request.getParameter("description");
    double price = Double.parseDouble(request.getParameter("price"));
    int categoryId = Integer.parseInt(request.getParameter("categoryId")); // Get category ID

    // Handle image upload
    String imageName = null;
    if (request.getPart("image") != null) {
        imageName = request.getPart("image").getSubmittedFileName(); // Get the image name from the uploaded file
    }

    // Construct the image URL
    String imageUrl = (imageName != null) ? "Images/" + imageName : null;

    // Create a new Product object
    Product product = new Product();
    product.setProductId(productId); // Set the product ID
    product.setProductName(productName);
    product.setDescription(description);
    product.setPrice(price);
    product.setCategoryId(categoryId); // Set the category ID
    if (imageUrl != null) {
        product.setImageUrl(imageUrl); // Set the new image URL if an image was uploaded
    }

    // Update the product in the database
    ProductDAO productDAO = new ProductDAO();
    productDAO.updateProduct(product);

    // Redirect to the product list after updating
    response.sendRedirect("ProductList.jsp");
}
}