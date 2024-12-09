package ecommerce.controller;

import java.util.List;

import ecommerce.model.User;
import ecommerce.service.UserService;
import ecommerce.model.Admin;
import ecommerce.model.Buyer;
import ecommerce.model.Seller;

/**
 * UserController class acts as an intermediate between User and UserService
 * classes, and the main menu entry point in App.java to manage CURD operations
 * for users.
 * 
 */
public class UserController {
    private final UserService userService;

    /**
     * Constructor for UserController
     */
    public UserController() {
        this.userService = new UserService();
    }

    /**
     * adds a new user, including a switch case which only accepts valid roles
     * and adds a new User object based on which role is entered
     * 
     * @param id
     * @param username
     * @param password
     * @param email
     * @param role
     */
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

    /**
     * returns list of all users in database
     * 
     * @return List of users
     */
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * retrieves user based on their username
     * 
     * @param username
     * @return user
     */
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * updates an existing user in the database, including a switch case which
     * updates the user
     * depending on which role is entered
     * 
     * @param id
     * @param username
     * @param password
     * @param email
     * @param role
     */
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

    /**
     * deletes a user based on their unique id
     * 
     * @param userId
     */
    public void deleteUser(int userId) {
        userService.deleteUser(userId);
    }

    /**
     * returns a list of users under a specified role
     * 
     * @return list of users by role
     */
    public List<User> getUsersByRole(String role) {
        return userService.getUsersByRole(role);
    }
}
