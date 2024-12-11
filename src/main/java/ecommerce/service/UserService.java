package ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import ecommerce.dao.UserDAO;
import ecommerce.model.Admin;
import ecommerce.model.Buyer;
import ecommerce.model.Seller;
import ecommerce.model.User;
import ecommerce.utils.InputUtils;

/**
 * UserService class handles CRUD operations and user interactions.
 */
public class UserService {
    private final UserDAO userDAO;
    private static User currentUser;

    /**
     * User Service Constructor
     */
    public UserService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Handles the registration of a new user with input validation.
     */
    public void registerUser() {
        System.out.println("Register a new user:");
        String username = InputUtils.readString("Enter username: ");
        String password = InputUtils.readPassword("Enter password: ");
        String email = InputUtils.readString("Enter email: ");
        String role = InputUtils.readString("Enter role (Buyer, Seller, Admin): ");

        try {
            User user;
            switch (role.toLowerCase()) {
                case "admin":
                    user = new Admin(0, username, password, email);
                    break;
                case "seller":
                    user = new Seller(0, username, password, email);
                    break;
                case "buyer":
                    user = new Buyer(0, username, password, email);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role. Valid user roles include Buyer, Seller, and Admin.");
            }
            addUser(user);
            System.out.println("User registered successfully!"+ user);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage()); 
        }
    }

    /**
     * Adds a user after validating required fields.
     * 
     * @param user User object to be added.
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
     * Handles user login by validating credentials.
     * 
     * @return true if login is successful; false otherwise.
     */
    public boolean loginUser() {
        System.out.println("User Login:");
        String username = InputUtils.readString("Enter username: ");
        String password = InputUtils.readPassword("Enter password: ");

        if (userDAO.authenticateUser(username, password)) {
            // After authentication, set the currentUser
            currentUser = userDAO.getUserByUsername(username);
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Login failed. Please check your credentials.");
            return false;
        }
    }

    

    /**
     * Returns a list of all users in the database.
     * 
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Retrieves a user by their username.
     * 
     * @param username Username of the user.
     * @return User object if found; null otherwise.
     */
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

        /**
     * Retrieves the current logged-in user (if any).
     * 
     * @return The current logged-in user.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

     /**
     * Retrieves the current logged-in user's ID.
     * 
     * @return ID of the currently logged-in user.
     */
    public int getCurrentUserId() {
        return currentUser != null ? currentUser.getId() : -1; // Return -1 if no user is logged in
    }

     /**
     * Retrieves the current logged-in user's role.
     * 
     * @return Role of the currently logged-in user.
     */
    public String getCurrentUserRole() {
        return currentUser != null ? currentUser.getRole() : "Guest"; // Return "Guest" if no user is logged in
    }



    /**
     * Updates an existing user with role validation.
     * 
     * @param id User ID.
     * @param username Username.
     * @param password Password.
     * @param email Email.
     * @param role User role.
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
                throw new IllegalArgumentException("Invalid role. Valid roles include Seller, Buyer, and Admin.");
        }
        userDAO.updateUser(user);
    }

    /**
     * Deletes a user by their unique ID.
     * 
     * @param userId User ID.
     */
    public void deleteUser(int userId) {
        try {
            boolean isDeleted = userDAO.deleteUserById(userId); // Assuming deleteUserById returns a boolean.
            if (isDeleted) {
                System.out.println("\nUser with ID " + userId + " was successfully deleted.\n");
            } else {
                System.out.println("\nNo user found with ID " + userId + ". Please check the ID and try again.\n");
            }
        } catch (Exception e) {
            System.out.println("\nError deleting user: " + e.getMessage() + "\n");
        }
    }    




    /**
     * Returns a list of users based on their role.
     * 
     * @param role Role of the users.
     * @return List of users matching the specified role.
     */
    public List<User> getUsersByRole(String role) {
        return userDAO.getAllUsers().stream()
                .filter(user -> user.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }
}




