package ecommerce.model;

/**
 * User is an abstarct class for other User objects - Admin, Seller and Buyer
 */
public abstract class User {
    /**
     * Initialize User attributes
     */
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    private String[] valid_roles = { "admin", "buyer", "seller" };

    /**
     * User constructor
     * 
     * @param id
     * @param username
     * @param password
     * @param email
     * @param role
     */
    public User(int id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * validates an entered role to ensure that it is one of 
     * 3 valid options (admin, seller, buyer)
     * 
     * @param role
     * @return boolean
     */
    private boolean isValidRole(String role) {
        for (String valid_role : valid_roles) {
            if (role.equals(valid_role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get unique id for user
     * 
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * get unique username for user
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * get password for user
     * 
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * get email for user
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * get role for user
     * 
     * @return role
     */
    public String getRole() {
        return role;
    }

    // Setter methods

    /**
     * set unique id for user
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * set username for user
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set password for user
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * set email for user
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set role for user
     * 
     * @param role
     */
    public void setRole(String role) {
        if (isValidRole(role)) {
            this.role = role;
        }
    }

    // toString method to display user information
    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "', role='" + role + "'}";
    }

}
