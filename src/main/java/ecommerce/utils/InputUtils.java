package ecommerce.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Utility class for handling user input and validation.
// Provides methods for reading and validating different types of input from the user.

public class InputUtils {

    // Create a Scanner object for reading input
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * method to read a string
     * 
     * @param prompt
     * @return scanner
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * method to read an integer
     * 
     * @param prompt
     * @return parsed int
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }

    /**
     * method to read a double
     * 
     * @param prompt
     * @return parsed double
     */
    public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid decimal number.");
            }
        }
    }

    /**
     * method to read and validate username 
     * 
     * @param prompt
     * @return username / error message
     */
    public static String readUsername(String prompt) {
        while (true) {
            String username = readString(prompt);
            if (username.matches("^[a-zA-Z0-9_]+$")) {
                return username;
            } else {
                System.out.println("Invalid username format. Please try again.");
            }
        }
    }

    /**
     * method to read and validate email
     * 
     * @param prompt
     * @return email / error message
     */
    public static String readEmail(String prompt) {
        while (true) {
            String email = readString(prompt);
            if (isValidEmail(email)) {
                return email;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
    }

    /**
     * method to read a password (masked for security)
     * 
     * @param prompt
     * @return String
     */
    public static String readPassword(String prompt) {
        System.out.print(prompt);
        return new String(System.console().readPassword());
    }

    /**
     * method to read a non-empty string (ex: username)
     * 
     * @param prompt
     * @return input
     */
    public static String readNonEmptyString(String prompt) {
        while (true) {
            String input = readString(prompt);
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty! Please try again.");
            } else {
                return input;
            }
        }
    }

    /**
     * method to validate email
     * 
     * @param email
     * @return boolean
     */
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * method to validate password 
     * 
     * @param password
     * @return boolean
     */
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8; // Example: Minimum length 8
    }

    /**
     * method to read a validated password
     * 
     * @param prompt
     * @return password / error message 
     */
    public static String readValidPassword(String prompt) {
        while (true) {
            String password = readPassword(prompt);
            if (isValidPassword(password)) {
                return password;
            } else {
                System.out.println("Password must be at least 8 characters long. Please try again.");
            }
        }
    }
}


