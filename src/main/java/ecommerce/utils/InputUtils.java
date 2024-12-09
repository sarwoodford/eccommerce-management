package ecommerce.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Utility class for handling user input and validation.
// Provides methods for reading and validating different types of input from the user.

public class InputUtils {

    // Create a Scanner object for reading input
    private static final Scanner scanner = new Scanner(System.in);

    // Read a string input with a prompt
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Read an integer with a prompt
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

    // Read and validate username
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

    // Read and validate email address
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

    // Read a password (password is masked for security)
    public static String readPassword(String prompt) {
        System.out.print(prompt);
        return new String(System.console().readPassword());
    }

    // Read a non-empty string (e.g. username)
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

    // Validate email format
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Calidate password strength (minimum length)
    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8; // Example: Minimum length 8
    }

    // Prompt and read a password with validation
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


