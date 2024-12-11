package ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import ecommerce.dao.ProductDAO;
import ecommerce.dao.UserDAO;
import ecommerce.model.Product;
import ecommerce.model.Seller;
import ecommerce.utils.InputUtils;

/**
 * ProductService manages products in the ecommerce system and
 * handles both business logic and user interactions.
 */
public class ProductService {
    private final ProductDAO productDAO;
    private final UserDAO userDAO;

    // Constructor initializes the ProductDAO and accepts an existing UserDAO
    // instance
    public ProductService(UserDAO userDAO) {
        this.productDAO = new ProductDAO();
        this.userDAO = userDAO; 
    }

    /**
     * Lists all products in the ecommerce database.
     * 
     * @return list of products.
     */
    public List<Product> getAllProducts() {
        return productDAO.getProducts();
    }

    /**
     * Displays all products in the database.
     */
    public void displayAllProducts() {
        List<Product> products = getAllProducts();
        System.out.println("\nAll Products:\n");
        for (Product product : products) {
            System.out.println(product + "\n");
        }
    }

    /**
     * Displays all products in the database, including seller details.
     */
    public void adminDisplayAllProducts() {
        List<Product> products = getAllProducts();
        System.out.println("\nAll Products with Seller Info:\n");

        for (Product product : products) {
            // Fetch the seller using the seller ID from the product
            Seller seller = (Seller) userDAO.getSellerById(product.getProductSellerId());

            // Display product details
            System.out.println("=====================================");
            System.out.println("Product: " + product.getProductName());
            System.out.println("Price: " + product.getProductPrice());
            System.out.println("Stock: " + product.getProductStock());
            System.out.println("Description: " + product.getProductDescription());

            // Display seller details
            if (seller != null) {
                System.out.println("----------- Seller Info -----------");
                System.out.println("Seller: " + seller.getUsername());
                System.out.println("Seller Email: " + seller.getEmail());
            } else {
                System.out.println("Seller not found.");
            }

            System.out.println("=====================================\n"); // Separator between products
        }
    }

    /**
     * Returns a list of all products affiliated with a specified seller based
     * on seller ID.
     * 
     * @param productSellerId Seller ID.
     * @return List of products.
     */
    public List<Product> getSellerProducts(int productSellerId) {
        return productDAO.getProducts().stream()
                .filter(product -> product.getProductSellerId() == productSellerId)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId Product ID.
     * @return Product object if found; null otherwise.
     */
    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    /**
     * Handles adding a new product with input prompts and validations.
     */
    public void addProduct(int sellerId) {
        System.out.println("\nAdd a New Product:\n");
        String name = InputUtils.readString("Enter product name: ");
        String description = InputUtils.readString("Enter product description: ");
        double price = InputUtils.readDouble("Enter product price: ");
        int stock = InputUtils.readInt("Enter product stock quantity: ");
        Product product = new Product(0, name, price, stock, sellerId, description);
        productDAO.addProduct(product);
        System.out.println("\nProduct added successfully!\n");
    }

    /**
     * Handles updating an existing product.
     */
    public void updateProduct() {
        System.out.println("\nUpdate a Product:");
        int productId = InputUtils.readInt("Enter product ID to update: ");
        Product product = getProductById(productId);

        if (product == null) {
            System.out.println("\nError: Product not found.\n");
            return;
        }

        System.out.println("\nUpdating Product: " + product.getProductName() + "\n");
        String name = InputUtils.readString("Enter new product name: ");
        String description = InputUtils.readString("Enter new product description: ");
        double price = InputUtils.readDouble("Enter new product price: ");
        int stock = InputUtils.readInt("Enter new product stock quantity: ");
        int sellerId = product.getProductSellerId(); // Assuming seller ID doesn't change.

        if (price < 0 || stock < 0) {
            System.out.println("\nError: Price and stock quantity must be non-negative.\n");
            return;
        }

        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductPrice(price);
        product.setProductStock(stock);
        product.setProductSellerId(sellerId);

        productDAO.updateProduct(product);
        System.out.println("\nProduct updated successfully!\n");
    }

    /**
     * Handles deleting a product by its unique ID.
     */
    public void deleteProductById(int sellerId) {
        System.out.println("\nDelete a Product:\n");
        int productId = InputUtils.readInt("Enter product ID to delete: ");
        productDAO.deleteProduct(productId, sellerId);
        System.out.println("\nProduct deleted successfully!\n");
    }
}
