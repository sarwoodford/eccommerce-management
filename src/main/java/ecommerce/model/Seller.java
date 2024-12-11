package ecommerce.model;


// Class representing a seller.
// Inherits from User and contains specific functionalities for sellers.
public class Seller extends User {
        /**
         * Seller constructor (Uses params from Super class User)
         * role is default seller
         * 
         * @param id
         * @param username
         * @param password
         * @param email
         */
       public Seller(int id, String username, String password, String email) {
        super(id, username, password, email, "seller");
    }

}