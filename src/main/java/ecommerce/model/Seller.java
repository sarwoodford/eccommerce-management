package ecommerce.model;


// Class representing a seller.
// Inherits from User and contains specific functionalities for sellers.
public class Seller extends User {
    // Additional methods specific to sellers, e.g., adding and managing products.

       // Constructor
       public Seller(int id, String username, String password, String email) {
        super(id, username, password, email, "seller");
    }

}
