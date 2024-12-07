package ecommerce.model;
// Class representing an admin user.
// Inherits from User and contains specific functionalities for administrators.
public class Admin extends User {
    // Additional methods specific to admins, e.g., managing users and products.
       // Constructor
       public Admin(int id, String username, String password, String email) {
        super(id, username, password, email, "admin");
    }

}
