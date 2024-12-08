package ecommerce.service;

import java.util.List;

import ecommerce.dao.ProductDAO;
import ecommerce.model.Product;

/**
 * ProductService manages products in the ecommerce system by interacting with
 * ProductDAO
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
     * Lists all products in the ecommerce database
     * 
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return productDAO.getProducts();
    }

    /**
     * adds a new product to the database ensuring that stock and price are not
     * a negative number
     * 
     * @param product
     */
    public void addProduct(Product product) {
        if (product.getProductPrice() < 0 || product.getProductStock() < 0) {
            throw new IllegalArgumentException("Price and stock quantity must be greater than ($)0.");
        }
        productDAO.addProduct(product);
    }

    /**
     * updates an existing product in the database ensuring that price and
     * stock are not negative numbers
     * 
     * @param product
     */
    public void updateProduct(Product product) {
        if (product.getProductPrice() < 0 || product.getProductStock() < 0) {
            throw new IllegalArgumentException("Price and stock quantity must be greater than ($)0.");
        }
        productDAO.updateProduct(product);
    }

    /**
     * deletes a product from the database using product id
     * 
     * @param productId
     */
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    /**
     * returns a list of all products affiliated with a specified seller
     * based on seller id
     * 
     * @param productSellerId
     * @return list of seller products
     */
    public List<Product> getSellerProducts(int productSellerId) {
        return productDAO.getProducts().stream().filter(product -> product.getProductSellerId() == productSellerId)
                .toList();
    }
}