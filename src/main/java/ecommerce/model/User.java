package ecommerce.model;

// Abstract base class representing a user in the system.
// Contains common attributes and methods for all user types.
public abstract class User {
    // Initialize the user attributes
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;

    private String[] valid_roles = { "admin", "buyer", "seller" };

    // Constructor
    public User(int id, String username, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Check if the role is valid
    private boolean isValidRole(String role) {
        for (String valid_role : valid_roles) {
            if (role.equals(valid_role)) {
                return true;
            }
        }
        return false;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
