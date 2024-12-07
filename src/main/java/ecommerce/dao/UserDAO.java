package ecommerce.dao;

import ecommerce.model.Admin;
import ecommerce.model.Buyer;
import ecommerce.model.Seller;
import ecommerce.model.User;
import ecommerce.utils.DatabaseUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Create a new user with password hashing using BCrypt
    public boolean addUser(User user) {
        // Hash the password before storing it
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        return DatabaseUtils.executeUpdate(query, user.getUsername(), hashedPassword, user.getEmail(), user.getRole());
    }

    // Read all users (no pagination, just fetch all users)
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

    // Read a user by username (for simplicity, no password check here)
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

      // Update a user (password should ideally be hashed here too)
      public boolean updateUser(User user) {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
        return DatabaseUtils.executeUpdate(query, user.getUsername(), user.getPassword(), user.getEmail(),
                user.getRole(), user.getId());
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
                    return BCrypt.checkpw(password, hashedPassword);  // Validate password
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
