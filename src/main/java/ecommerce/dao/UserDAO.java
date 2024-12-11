package ecommerce.dao;

import ecommerce.model.Admin;
import ecommerce.model.Buyer;
import ecommerce.model.Seller;
import ecommerce.model.User;
import ecommerce.utils.DatabaseUtils;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

       /**
        * adds a user to the database in the users table
        *
        *@param User
        */
       public String addUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";

        if (DatabaseUtils.executeUpdate(query, user.getUsername(), hashedPassword, user.getEmail(), user.getRole())) {
            return "User added successfully.";
        } else {
            return "Error: Username may already be taken or other database issue.";
        }
    }

    /**
     * returns a list of all users in the users table
     * 
     * @return all users
     */
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

    /**
     * retrieves a user based on their username and returns it
     * 
     * @param username
     * @return user
     */
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

    /**
     * retrieves a seller by their unique id and returns it
     * 
     * @param sellerId
     * @return seller
     */
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
    

      /**
       * updates the fields of a user that already exists in the database 
       * 
       * @param user
       * @return success/error message
       */
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

    /**
     * deletes a user by their unique id 
     * 
     * @param userId
     * @return boolean
     */
    public boolean deleteUserById(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        return DatabaseUtils.executeUpdate(query, userId);
    }

  

    /**
     * authenticates a users login credentials to validate that they exist 
     * in the database 
     * 
     * @param username
     * @param password
     * @return boolean
     */
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
