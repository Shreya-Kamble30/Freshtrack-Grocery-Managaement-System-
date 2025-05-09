package com.freshtrack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public Product getProductById(int id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE product_id = ?"; // Adjust the table name and column names as necessary

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getInt("product_id")); // Adjust column names as necessary
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategoryId(resultSet.getInt("category_id"));
                product.setImageUrl(resultSet.getString("image_url")); // Assuming you have an image URL field
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        }

        return product;
    }
    // Method to get products by category from the database
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?"; // Use a parameterized query to prevent SQL injection

        try (Connection connection = DBConnect.getConnection(); // Ensure you have a Database class to manage connections
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, categoryId); // Set the category ID parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setCategoryId(resultSet.getInt("category_id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return productList;
    }

    // Method to get all products from the database
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products"; // Adjust the query if necessary

        try (Connection connection = DBConnect.getConnection(); // Ensure you have a Database class to manage connections
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("product_id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImageUrl(resultSet.getString("image_url"));
                product.setCategoryId(resultSet.getInt("category_id"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return productList;
    }
    public void addProduct(Product product) {
    String query = "INSERT INTO products (product_name, description, price, image_url, category_id) VALUES (?, ?, ?, ?, ?)";
    try (Connection connection = DBConnect.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        
        statement.setString(1, product.getProductName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        statement.setString(4, product.getImageUrl());
        statement.setInt(5, product.getCategoryId());
        
        statement.executeUpdate(); // Execute the insert
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }
}
    public void updateProduct(Product product) {
    String sql = "UPDATE products SET product_name = ?, description = ?, price = ?, category_id = ?, image_url = ? WHERE product_id = ?";
    try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, product.getProductName());
        stmt.setString(2, product.getDescription());
        stmt.setDouble(3, product.getPrice());
        stmt.setInt(4, product.getCategoryId());
        stmt.setString(5, product.getImageUrl());
        stmt.setInt(6, product.getProductId());
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setInt(1, productId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Return true if a product was deleted
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            return false; // Return false if there was an error
        }
    }
}