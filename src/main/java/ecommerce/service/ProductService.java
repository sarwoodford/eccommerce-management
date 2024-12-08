package ecommerce.service;

import java.util.List;

import ecommerce.dao.ProductDAO;
import ecommerce.model.Product;

// Service class for managing product-related business logic.
// This class interacts with ProductDAO to perform operations.
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public List<Product> getAllProducts() {
        return productDAO.getProducts();
    }

    public void addProduct(Product product) {
        if (product.getProductPrice() < 0 || product.getProductStock() < 0) {
            throw new IllegalArgumentException("Price and stock quantity must be greater than ($)0.");
        }
        productDAO.addProduct(product);
    }

    public void updateProduct(Product product) {
        if (product.getProductPrice() < 0 || product.getProductStock() < 0) {
            throw new IllegalArgumentException("Price and stock quantity must be greater than ($)0.");
        }
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    public List<Product> getSellerProducts(int productSellerId) {
        return productDAO.getProducts().stream().filter(product -> product.getProductSellerId() == productSellerId)
                .toList();
    }
}