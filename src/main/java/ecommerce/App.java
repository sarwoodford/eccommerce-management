package ecommerce;

import ecommerce.utils.DatabaseUtils;
import ecommerce.controller.ProductController;
import ecommerce.controller.UserController;
import ecommerce.model.Product;
import ecommerce.utils.InputUtils;

import java.util.Scanner;
import java.util.List;

/**
 * Main application class serving as the entry point of the program.
 * Initializes and starts the e-commerce platform.
 */
public class App {

    /**
     * Main method that starts the application.
     * Responsible for setting up resources, initializing services, and running the
     * program.
     */

    private static final Scanner scanner = new Scanner(System.in);

    // Manage user interactions via controllers and services.
    private static ProductController productController = new ProductController();
    private static UserController userController = new UserController();

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
                    userController.registerUser();
                    break;
                case 2:
                    if (userController.loginUser()){
                        // Proceed with logged in menu for role specific users (Buyer, Seller, Admin)
                    };
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
     * Guides users to different functionalities like login, register, view
     * products, etc.
     */
    private static void displayMainMenu() {
        // Print main menu options.
        System.out.println("Welcome to the E-Commerce platform!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    private static void displayBuyerMenu() {
        while(true){
            // Print main menu options.
            System.out.println("Welcome Buyer!");
            System.out.println("1. View all Products");
            System.out.println("2. View specific Product (w/ product info)");
            System.out.println("3. Exit");

            System.out.print("Please enter an option: ");
            int choice = getUserChoice();

            switch(choice) {
                case 1: 
                    viewAllProducts();
                    break;
                case 2: 
                    viewSpecifcProduct();
                    break;
                case 3: 
                    System.out.println("Thank you for choosing Buyer Menu! Goodbye!");
                    System.exit(0);
                default: 
                    System.out.println("Invalid option selected. Please choose 1, 2 or 3");
            }
        }
    }

    private static int getUserChoice(){
        try{
            return Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }

    private static void viewAllProducts() {
        System.out.println("\nAll Products In Ecommerce System: ");
        List<Product> products = productController.getAllProducts();
    
        if (products.isEmpty()) {
            System.out.println("No products to show! Come back later!");
        } else {
            products.forEach(product -> System.out.println(
                "\nid: " + product.getProductId() + 
                "\nName: " + product.getProductName() + 
                "\nAvailable Stock: " + product.getProductStock() + 
                "\nPrice: $" + product.getProductPrice()
            ));
        }
    }

    private static void viewSpecifcProduct(){
        System.out.print("\nEnter ID of Product you wish to view: ");
        int productId = getUserChoice();

        Product product = productController.getProductById(productId);
        if(product == null) {
            System.out.println("No product with that ID. Please try again!");
        } else {
            System.out.println("\nProduct Details:" +
            "\nID: " + product.getProductId() +
            "\nName: " + product.getProductName() +
            "\nDescription: " + product.getProductDescription() +
            "\nPrice: $" + product.getProductPrice() +
            "\nStock: " + product.getProductStock()
            );
        }
    }

    private static void displaySellerMenu() {
        System.out.println("Welcome Seller!");
        System.out.println("1. View all seller Products");
        System.out.println("2. Add products");
        System.out.println("3. Update products");
        System.out.println("4. Delete products");
        System.out.println("5. Exit");
    }

    private static void displayAdminMenu() {
        // Print main menu options.
        System.out.println("Welcome Admin!");
        System.out.println("1. View all users");
        System.out.println("2. Delete user from system");
        System.out.println("3. View all products");
        System.out.println("4. Exit");
    }



    

    /**
     * Method to shut down the application gracefully.
     * Ensures all resources are released and any cleanup is performed.
     */
    private static void shutdown() {
        // Close database connections and perform cleanup.
        System.out.println("Shutting down...");
        DatabaseUtils.closeDatabase();

    }
}
