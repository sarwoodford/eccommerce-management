package ecommerce.controller;

import java.util.List;

import ecommerce.model.Product;
import ecommerce.service.ProductService;

/**
 * product controller acts as an intermediate between Product
 * and ProductService classes, and App.java - main entry point for
 * ecommerce menu
 */
public class ProductController {
    private final ProductService productService;

    /**
     * ProductController constructor
     */
    public ProductController() {
        this.productService = new ProductService();
    }

    /**
     * returns a list of all products in database
     * 
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * adds a new product to database
     * 
     * @param product
     */
    public void addProduct(Product product) {
        productService.addProduct(product);
    }

    /**
     * updates an existing product in database
     * 
     * @param product
     */
    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    /**
     * deletes a product by its unique id
     * 
     * @param productId
     */
    public void deleteProdyct(int productId) {
        productService.deleteProduct(productId);
    }

    /**
     * returns a list of products sold by specified seller
     * 
     * @param productSellerId
     * @return list of seller products
     */
    public List<Product> getProductsBySeller(int productSellerId) {
        return productService.getSellerProducts(productSellerId);
    }
}
