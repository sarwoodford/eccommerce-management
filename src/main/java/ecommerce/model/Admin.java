package ecommerce.model;
// Class representing an admin user.
// Inherits from User and contains specific functionalities for administrators.
public class Admin extends User {
    /**
     * Admin constructor (uses params from Super class User) 
     * role is default Admin
     * 
     * @param id
     * @param username
     * @param password
     * @param email
     */
       public Admin(int id, String username, String password, String email) {
        super(id, username, password, email, "admin");
    }

}
