// Database Utility Class
package ecommerce.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce-final-test";
    private static final String USER = "myuser";
    private static final String PASSWORD = "password";

    // Establishes a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Initializes the database (optional schema creation)
    public static void initializeDatabase() {
        try (Connection connection = getConnection()) {
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY," +
                    "username VARCHAR(50) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "role VARCHAR(10) NOT NULL);";

            String createProductsTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(100) NOT NULL," +
                    "price NUMERIC(10, 2) NOT NULL," +
                    "quantity INT NOT NULL," +
                    "seller_id INT NOT NULL," +
                    "description TEXT," +
                    "FOREIGN KEY (seller_id) REFERENCES users(id) ON DELETE CASCADE);";

            connection.createStatement().execute(createUsersTable);
            connection.createStatement().execute(createProductsTable);

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Executes a query with parameters
    public static boolean executeUpdate(String query, Object... params) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Executes a query and returns a ResultSet
    public static ResultSet executeQuery(String query, Object... params) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeDatabase() {
        try {
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            System.err.println("Error while shutting down: " + e.getMessage());
        }
    }
    

}
