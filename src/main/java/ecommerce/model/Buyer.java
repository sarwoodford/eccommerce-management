package ecommerce.model;

// Class representing a buyer.
// Inherits from User and contains specific functionalities for buyers.
public class Buyer extends User {
    
    /**
     * Buyer constructor (Uses params from Super class User)
     * role is default Buyer
     * 
     * @param id
     * @param username
     * @param password
     * @param email
     */
    public Buyer(int id, String username, String password, String email) {
        super(id, username, password, email, "buyer");
    }

    // Misc buyer methods to create related to the product service or doa
}
