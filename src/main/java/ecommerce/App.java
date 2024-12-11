package ecommerce;

import ecommerce.utils.DatabaseUtils;
import ecommerce.service.ProductService;
import ecommerce.service.UserService;
import ecommerce.dao.UserDAO;
import ecommerce.model.Product;
import ecommerce.model.User;
import ecommerce.utils.InputUtils;

import java.util.List;

/**
 * Main application class serving as the entry point of the program.
 * Initializes and starts the e-commerce platform.
 */
public class App {

    private static final UserDAO userDAO = new UserDAO();
    private static final ProductService productService = new ProductService(userDAO);
    private static final UserService userService = new UserService();

    public static void main(String[] args) {

        // Initialize database connection.
        DatabaseUtils.initializeDatabase();

        // Start the main application loop.
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = InputUtils.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    userService.registerUser();
                    break;
                case 2:
                    if (userService.loginUser()) {
                        String role = userService.getCurrentUserRole();
                        if (role.equalsIgnoreCase("Buyer")) {
                            displayBuyerMenu();
                        } else if (role.equalsIgnoreCase("Seller")) {
                            displaySellerMenu();
                        } else if (role.equalsIgnoreCase("Admin")) {
                            displayAdminMenu();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    running = false;
                    shutdown();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    /**
     * Method to display the main menu of the e-commerce application.
     */
    private static void displayMainMenu() {
        System.out.println("\nWelcome to the E-Commerce platform!");
        System.out.println("-----------------------------------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println();
    }

    private static void displayBuyerMenu() {
        boolean buyerMenu = true;
        while (buyerMenu) {
            System.out.println("\nWelcome Buyer!");
            System.out.println("----------------");
            System.out.println("1. View all Products");
            System.out.println("2. View specific Product (w/ product info)");
            System.out.println("3. Exit");
            System.out.println();

            int choice = InputUtils.readInt("Please enter an option: ");

            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    viewSpecificProduct();
                    break;
                case 3:
                    System.out.println("Exiting Buyer Menu...");
                    buyerMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    private static void viewAllProducts() {
        System.out.println("\nAll Products in the E-Commerce System:");
        System.out.println("---------------------------------------");
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products to show! Come back later!");
        } else {
            products.forEach(product -> System.out.println(
                    "\nid: " + product.getProductId() +
                            "\nName: " + product.getProductName() +
                            "\nAvailable Stock: " + product.getProductStock() +
                            "\nPrice: $" + product.getProductPrice()));
        }
    }

    private static void viewSpecificProduct() {
        int productId = InputUtils.readInt("\nEnter ID of the Product you wish to view: ");
        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println("No product found with that ID. Please try again!");
        } else {
            System.out.println("\nProduct Details:" +
                    "\nID: " + product.getProductId() +
                    "\nName: " + product.getProductName() +
                    "\nDescription: " + product.getProductDescription() +
                    "\nPrice: $" + product.getProductPrice() +
                    "\nStock: " + product.getProductStock());
        }
    }

    private static void displaySellerMenu() {
        boolean sellerMenu = true;
        while (sellerMenu) {
            System.out.println("\nWelcome Seller!");
            System.out.println("----------------");
            System.out.println("1. View all seller Products");
            System.out.println("2. Add a Product");
            System.out.println("3. Update a Product");
            System.out.println("4. Delete a Product");
            System.out.println("5. Exit");
            System.out.println();

            int choice = InputUtils.readInt("Please enter an option: ");

            switch (choice) {
                case 1:
                    displaySellerProducts();
                    break;
                case 2:
                int sellerId = userService.getCurrentUserId();
                    productService.addProduct(sellerId);
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProductById(userService.getCurrentUserId());
                    break;
                case 5:
                    System.out.println("Exiting Seller Menu...");
                    sellerMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }
    }

    private static void displaySellerProducts() {
        int sellerId = userService.getCurrentUserId();
        List<Product> products = productService.getSellerProducts(sellerId);

        if (products.isEmpty()) {
            System.out.println("No products to show for this seller.");
        } else {
            products.forEach(product -> System.out.println(
                    "\nid: " + product.getProductId() +
                            "\nName: " + product.getProductName() +
                            "\nStock: " + product.getProductStock() +
                            "\nPrice: $" + product.getProductPrice()));
        }
    }

    private static void displayAllUsers() {
        System.out.println("\nAll Users in the E-Commerce System:");
        System.out.println("-------------------------------------");
        List<User> users = userService.getAllUsers();

        // Check if users are present
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        // Print each user with their respective info
        for (User user : users) {
            System.out.println("=====================================");
            System.out.println("User ID: " + user.getId());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + user.getRole());
            System.out.println("=====================================");
        }
    }

    private static void displayAdminMenu() {
        boolean adminMenu = true;
        while (adminMenu) {
            System.out.println("\nWelcome Admin!");
            System.out.println("----------------");
            System.out.println("1. View all users");
            System.out.println("2. Delete user from system");
            System.out.println("3. View all products");
            System.out.println("4. Exit");
            System.out.println();

            int choice = InputUtils.readInt("Please enter an option: ");

            switch (choice) {
                case 1:
                    displayAllUsers();
                    break;
                case 2:
                    int userId = InputUtils.readInt("Enter user ID to delete: ");
                    userService.deleteUser(userId);
                    break;
                case 3:
                    productService.adminDisplayAllProducts();
                    break;
                case 4:
                    System.out.println("Exiting Admin Menu...");
                    adminMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }
    }

    /**
     * Method to shut down the application gracefully.
     */
    private static void shutdown() {
        System.out.println("Shutting down...");
        DatabaseUtils.closeDatabase();
    }
}
