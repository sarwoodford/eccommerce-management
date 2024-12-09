package ecommerce.controller;

import java.util.List;

import ecommerce.model.Product;
import ecommerce.service.ProductService;

public class ProductController {
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void addProduct(Product product) {
        productService.addProduct(product);
    }

    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    public void deleteProdyct(int productId) {
        productService.deleteProduct(productId);
    }

    public List<Product> getProductsBySeller(int productSellerId) {
        return productService.getSellerProducts(productSellerId);
    }
}
