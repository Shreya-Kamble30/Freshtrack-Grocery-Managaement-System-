package com.freshtrack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> getAllCategories() {
    List<Category> categoryList = new ArrayList<>();
    String query = "SELECT * FROM category"; // Ensure this matches your table name

    try (Connection connection = DBConnect.getConnection();
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {

        while (resultSet.next()) {
            Category category = new Category();
            category.setCategoryId(resultSet.getInt("category_id")); // Ensure this matches your column name
            category.setCategoryName(resultSet.getString("category_name")); // Ensure this matches your column name
            categoryList.add(category);
        }
        System.out.println("Categories retrieved: " + categoryList.size()); // Debugging output
    } catch (SQLException e) {
        // Handle exceptions appropriately
        
    }
    return categoryList; // Return the list of categories
}
}