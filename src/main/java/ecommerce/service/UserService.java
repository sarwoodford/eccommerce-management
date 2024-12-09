package ecommerce.service;

import java.util.List;

import ecommerce.dao.UserDAO;
import ecommerce.model.User;

/**
 * UserService class utilized User and UserDAO classes for CURD operations
 */
public class UserService {
    private final UserDAO userDAO;

    /**
     * User Service Constructor
     */
    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * adds a user which validating empty fields that are required
     * 
     * @param user
     */
    public void addUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty. Please try again.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty. Please try again.");
        }
        userDAO.addUser(user);
    }

    /**
     * returns a list of all users in database
     * 
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * returns a user by identifying them by their username
     * 
     * @param username
     * @return user based on their username
     */
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    /**
     * updates an existing user in the database
     * 
     * @param user
     */
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    /**
     * deletes a user by their unique id
     * 
     * @param userId
     */
    public void deleteUser(int userId) {
        userDAO.deleteUserById(userId);
    }

    /**
     * returns a list of users given specified role
     * 
     * @param role
     * @return list of all users by role
     */
    public List<User> getUsersByRole(String role) {
        return userDAO.getAllUsers().stream().filter(user -> user.getRole().equalsIgnoreCase(role)).toList();
    }
}
