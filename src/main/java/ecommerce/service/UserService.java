package ecommerce.service;

import java.util.List;

import ecommerce.dao.UserDAO;
import ecommerce.model.User;

public class UserService {
    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void addUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty. Please try again.");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty. Please try again.");
        }
        userDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUserById(userId);
    }

    public List<User> getUsersByRole(String role) {
        return userDAO.getAllUsers().stream().filter(user -> user.getRole().equalsIgnoreCase(role)).toList();
    }
}
