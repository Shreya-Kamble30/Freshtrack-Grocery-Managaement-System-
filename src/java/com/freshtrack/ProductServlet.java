package com.freshtrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@MultipartConfig // Enable multipart/form-data support
@WebServlet("/addProduct")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        // Get the uploaded file part
        Part filePart = request.getPart("image"); // Assuming the input field name is "image"
        
        // Check if the file part is null or has no content
        if (filePart == null || filePart.getSize() == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File upload failed: No file uploaded.");
            return;
        }

        String imageName = filePart.getSubmittedFileName(); // Get the file name
        System.out.println("Uploaded file name: " + imageName);

        // Construct the image URL
        String imageUrl = "./Images/" + imageName; // Save image in Images folder

        

        // Create a new Product object
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        product.setCategoryId(categoryId);

        // Add the product to the database
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProduct(product); // Assuming you have a method to add the product

        // Redirect to the product list after adding
        response.sendRedirect("ProductList.jsp");
    }
}