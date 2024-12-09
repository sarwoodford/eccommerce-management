package ecommerce.controller;

import java.util.List;

import ecommerce.model.User;
import ecommerce.service.UserService;
import ecommerce.model.Admin;
import ecommerce.model.Buyer;
import ecommerce.model.Seller;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void addUser(int id, String username, String password, String email, String role) {
        User user;

        switch (role.toLowerCase()) {
            case "admin":
                user = new Admin(id, username, password, email);
                break;
            case "seller":
                user = new Seller(id, username, password, email);
                break;
            case "buyer":
                user = new Buyer(id, username, password, email);
                break;
            default:
                throw new IllegalArgumentException("Invalid role. Valid user roles include Buyer, Seller and Admin.");
        }
        userService.addUser(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public void updateUser(int id, String username, String password, String email, String role) {
        User user;

        switch (role.toLowerCase()) {
            case "admin":
                user = new Admin(id, username, password, email);
                break;
            case "seller":
                user = new Seller(id, username, password, email);
                break;
            case "buyer":
                user = new Buyer(id, username, password, email);
                break;
            default:
                throw new IllegalArgumentException("Invalid role. Valid roles include seller, buyer and admin.");
        }
        userService.updateUser(user);
    }

    public void deleteUser(int userId) {
        userService.deleteUser(userId);
    }

    public List<User> getUsersByRole(String role) {
        return userService.getUsersByRole(role);
    }
}
