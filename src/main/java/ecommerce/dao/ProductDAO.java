package ecommerce.dao;

import ecommerce.model.Product;
import ecommerce.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Product DAO handles and manages Product objects in the database
 *
 * Methods are created to perform CRUD operations.
 */
public class ProductDAO {

    /**
     * Adds a new product to the products table
     *
     * @param product the product to add
     */
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, quantity, seller_id, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getProductPrice());
            statement.setInt(3, product.getProductStock());
            statement.setInt(4, product.getProductSellerId());
            statement.setString(5, product.getProductDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of all products in the products table
     *
     * @return list of all products
     */
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DatabaseUtils.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("seller_id"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Returns a list of products for a specific seller
     *
     * @param sellerId the seller's userId
     * @return list of products belonging to the seller
     */
    public List<Product> getSellerProducts(int sellerId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE seller_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sellerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("seller_id"),
                            resultSet.getString("description")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Updates the fields of an existing product in the products table
     *
     * @param product the product with updated values
     */
    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ?, description = ? WHERE id = ? AND seller_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getProductPrice());
            statement.setInt(3, product.getProductStock());
            statement.setString(4, product.getProductDescription());
            statement.setInt(5, product.getProductId());
            statement.setInt(6, product.getProductSellerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a product from the products table by its ID, ensuring the seller owns
     * the product
     *
     * @param productId the product's ID
     * @param sellerId  the seller's userId
     */
    public void deleteProduct(int productId, int sellerId) {
        String query = "DELETE FROM products WHERE id = ? AND seller_id = ?";

        try (Connection connection = DatabaseUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.setInt(2, sellerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a product by its ID
     *
     * @param productId the product's ID
     * @return the product, or null if not found
     */
    public Product getProductById(int productId) {
        String query = "SELECT * FROM products WHERE id = ?";
        Product product = null;

        try (Connection connection = DatabaseUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("seller_id"),
                            resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}