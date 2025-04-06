package com.freshtrack;

import java.sql.*;

public class UserDao 
{
	public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DBConnect.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password); // Use hashed password in production
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password")); // Store hashed password
            }
        }
        return user;
    }
}
