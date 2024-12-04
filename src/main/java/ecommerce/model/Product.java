package ecommerce.model;

/**
 * Product class represents a product in the e-commerce system.
 */
public class Product {
    /**
     * Product class contains attributes including the products id,
     * name, price, stock and seller.
     */
    private int productId;
    private String productName;
    private double productPrice;
    private int productStock;
    private String productSeller;

    /**
     * Parametrized constructor constructs a new Product object including the
     * following params
     * 
     * @param productId
     * @param productName
     * @param productPrice
     * @param productStock
     * @param productSeller
     */

    public Product(int productId, String productName, double productPrice, int productStock,
            String productSeller) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productSeller = productSeller;
    }

    /**
     * returns identifier for product
     * 
     * @return product id
     */

    public int getProductId() {
        return productId;
    }

    /**
     * sets identifier for product
     * 
     * @param productId
     */

    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * returns name of product
     * 
     * @return product name
     */

    public String getProductName() {
        return productName;
    }

    /**
     * sets product name
     * 
     * @param productName
     */

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * returns price of product
     * 
     * @return product price
     */

    public double getProductPrice() {
        return productPrice;
    }

    /**
     * sets price of product
     * 
     * @param productPrice
     */

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * returns stock of product
     * 
     * @return product stock
     */

    public int getProductStock() {
        return productStock;
    }

    /**
     * sets product stock
     * 
     * @param productStock
     */

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * returns seller of product
     * 
     * @return product seller
     */

    public String getProductSeller() {
        return productSeller;
    }

    /**
     * sets seller of product
     * 
     * @param productSeller
     */
    public void setProductSeller(String productSeller) {
        this.productSeller = productSeller;
    }

    /**
     * returns a string representing the product and all of it's details
     * 
     * @return a string representing the product
     */

    @Override
    public String toString() {
        return "Product{" +
                "id: " + productId +
                ", name: " + productName +
                ", price: " + productPrice +
                ", stock: " + productStock +
                ", seller: " + productSeller +
                "}";
    }
}
