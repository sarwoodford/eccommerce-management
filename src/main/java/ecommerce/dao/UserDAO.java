package ecommerce.dao;

import ecommerce.model.Admin;
import ecommerce.model.Buyer;
import ecommerce.model.Seller;
import ecommerce.model.User;
import ecommerce.utils.DatabaseUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.postgresql.util.PSQLException;
import java.sql.SQLException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

       // Create a new user with password hashing using BCrypt
       public String addUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";

        if (DatabaseUtils.executeUpdate(query, user.getUsername(), hashedPassword, user.getEmail(), user.getRole())) {
            return "User added successfully.";
        } else {
            return "Error: Username may already be taken or other database issue.";
        }
    }

    // Read all users 
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String role = rs.getString("role");
                User user = null;
                if ("admin".equalsIgnoreCase(role)) {
                    user = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                            rs.getString("email"));
                } else if ("seller".equalsIgnoreCase(role)) {
                    user = new Seller(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                            rs.getString("email"));
                } else if ("buyer".equalsIgnoreCase(role)) {
                    user = new Buyer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                            rs.getString("email"));
                }
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Read a user by username 
    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs != null && rs.next()) {
                    String role = rs.getString("role");
                    if ("admin".equalsIgnoreCase(role)) {
                        return new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                                rs.getString("email"));
                    } else if ("seller".equalsIgnoreCase(role)) {
                        return new Seller(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                                rs.getString("email"));
                    } else if ("buyer".equalsIgnoreCase(role)) {
                        return new Buyer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                                rs.getString("email"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Seller getSellerById(int sellerId) {
        String query = "SELECT * FROM users WHERE id = ? AND role = 'seller'";
    
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs != null && rs.next()) {
                    // Creating a Seller object with the data from the result set
                    return new Seller(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                            rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no seller is found
    }
    

      // Update a user (password should ideally be hashed here too)
public String updateUser(User user) {
    // Check if the username is already taken
    String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ? AND id != ?";
    
    try (ResultSet rs = DatabaseUtils.executeQuery(checkQuery, user.getUsername(), user.getId())) {
        if (rs != null && rs.next()) {
            int count = rs.getInt(1);
            if (count > 0) {
                return "Error: Username is already taken.";
            }
        }
    } catch (Exception e) {
        System.err.println("Error checking username: " + e.getMessage());
        return "Error: Database issue.";
    }

    // Hash the password
    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    String query = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
    
    if (DatabaseUtils.executeUpdate(query, user.getUsername(), hashedPassword, user.getEmail(), user.getRole(), user.getId())) {
        return "User updated successfully.";
    } else {
        return "Error updating user: Database issue.";
    }
}

    // Delete a user by ID
    public boolean deleteUserById(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        return DatabaseUtils.executeUpdate(query, userId);
    }

  

    // Authenticate user by username and password
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs != null && rs.next()) {
                    String hashedPassword = rs.getString("password");
                    return BCrypt.checkpw(password, hashedPassword); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
