package ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import ecommerce.dao.ProductDAO;
import ecommerce.model.Product;
import ecommerce.utils.InputUtils;

/**
 * ProductService manages products in the ecommerce system and
 * handles both business logic and user interactions.
 */
public class ProductService {
    /**
     * ProductDAO instance assigned
     */
    private final ProductDAO productDAO;

    /**
     * Constructor initializes the ProductDAO
     */
    public ProductService() {
        this.productDAO = new ProductDAO();
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
        System.out.println("All Products:");
        for (Product product : products) {
            System.out.println(product);
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
    public void addProduct() {
        System.out.println("Add a New Product:");
        String name = InputUtils.readString("Enter product name: ");
        String description = InputUtils.readString("Enter product description: ");
        double price = InputUtils.readDouble("Enter product price: ");
        int stock = InputUtils.readInt("Enter product stock quantity: ");
        int sellerId = InputUtils.readInt("Enter seller ID: ");

        Product product = new Product(0, name, price, stock, sellerId, description);
        productDAO.addProduct(product);
        System.out.println("Product added successfully!");
    }

   

    /**
     * Handles updating an existing product.
     */
    public void updateProduct() {
        int productId = InputUtils.readInt("Enter product ID to update: ");
        Product product = getProductById(productId);

        if (product == null) {
            System.out.println("Error: Product not found.");
            return;
        }

        System.out.println("Updating Product: " + product.getProductName());
        String name = InputUtils.readString("Enter new product name: ");
        String description = InputUtils.readString("Enter new product description: ");
        double price = InputUtils.readDouble("Enter new product price: ");
        int stock = InputUtils.readInt("Enter new product stock quantity: ");
        int sellerId = product.getProductSellerId(); // Assuming seller ID doesn't change.

        if (price < 0 || stock < 0) {
            System.out.println("Error: Price and stock quantity must be non-negative.");
            return;
        }

        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductPrice(price);
        product.setProductStock(stock);
        product.setProductSellerId(sellerId);

        productDAO.updateProduct(product);
        System.out.println("Product updated successfully!");
    }

    

    /**
     * Handles deleting a product by its unique ID.
     */
    public void deleteProductById() {
        int productId = InputUtils.readInt("Enter product ID to delete: ");
        productDAO.deleteProduct(productId);
        System.out.println("Product deleted successfully!");
    }

    
}
