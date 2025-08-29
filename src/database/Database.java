package database;

import models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    // ✅ Updated JDBC URL with timezone & SSL parameters (important for MySQL 8+)
    private static final String URL = "jdbc:mysql://localhost:3306/javacartdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Root@123";

    // ✅ Safe connection method with explicit driver loading
    public static Connection getConnection() throws SQLException {
        try {
            // Explicitly load the MySQL driver (safer in some environments)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // ✅ Fetch all products from DB
    public static List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productList.add(new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        return productList;
    }
}