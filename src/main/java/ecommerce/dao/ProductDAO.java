package ecommerce.dao;

import ecommerce.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Product DAO handles and manages Product objects in the database
 * 
 * methods are created to perform CURD
 */
public class ProductDAO {
    /**
     * database url, username and password for connection
     */
    private static final String URL = "";
    private static final String USER = "sara";
    private static final String PASSWORD = "sara";

    /**
     * adds a new product to the products table
     * 
     * @param product
     */
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, quantity, seller_id) VALUES (" +
                product.getProductName() + ", " +
                product.getProductPrice() + ", " +
                product.getProductStock() + ", " +
                product.getProductSellerId() + ")";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a list of all products in the products table
     * 
     * @return all products
     */
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("seller_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * updates the fields of an existing product in the products table
     * 
     * @param product
     */
    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = '" + product.getProductName() + "', " +
                "price = " + product.getProductPrice() + ", " +
                "quantity = " + product.getProductStock() + " " +
                "WHERE id = " + product.getProductId();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes a product from the products table by using productId attribute
     * 
     * @param productId
     */
    public void deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id = " + productId;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
