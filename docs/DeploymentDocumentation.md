# Deployment Documentation

This document provides instructions for installing and running the **E-Commerce CLI Application**. Follow these steps to set up and deploy the application on your local environment.

## Table of Contents

- [Source Code Structure](#source-code-structure)
- [1. Prerequisites](#1-prerequisites)
- [2. Installation Steps](#2-installation-steps)
  - [Download the Source Code](#download-the-source-code)
  - [Set Up Directory Structure](#set-up-directory-structure)
  - [Compilation _(in terminal)_](#compilation-in-terminal)
  - [Run the Application](#run-the-application)
- [3. Usage Instructions](#3-usage-instructions)
- [4. Troubleshooting](#4-troubleshooting)

---

## Source Code Structure
```
src/
├── main/
│ ├── dao/
│ │ └── ProductDAO.java
│ │ └── UserDAO.java
│ ├── model/
│ │ ├── Admin.java
│ │ ├── Buyer.java
│ │ └── User.java
│ │ └── Seller.java
│ │ └── Product.java
│ ├── service/
│ │ ├── ProductService.java
│ │ └── UserService.java
│ ├── utils/
│ │ ├── InputUtils.java 
│ │ ├── App.java #main entry point for user 
```

---

## 1. Prerequisites

Before deploying the application, ensure that you have the following prerequisites installed:

- **Java Development Kit (JDK)** - Version 8 or higher
- **Java Runtime Environment (JRE)** - Version 8 or higher (if not included in the JDK)
- **Command Line Interface (CLI)** - To compile and run the application (or use an IDE like VSCode or IntelliJ)
- **PostgreSQL Database** - To interact with the data (ensure a database is accessible for interaction)
- **Maven** - For managing dependencies and building the project from the terminal

---

## 2. Installation Steps

### 1. Download the Source Code
   - Clone or download the repository containing the source code for the E-Commerce CLI Application.
   - You can download the code from GitHub or clone it using the following command:
     ```bash
     git clone https://github.com/sarwoodford/Java-FINAL.git
     ```

   - Place the downloaded files in a suitable directory on your local machine.

### 2. Set Up Directory Structure
   - Ensure the Java files are organized into the correct package structure as shown in the "Source Code Structure" section above.
   - The directory should include `src/`, `dao/`, `model/`, `service/`, and `utils/` subfolders.

### 3. Compilation _(in terminal)_:
   - Open a terminal and navigate to the directory where you have downloaded the source code:
     ```bash
     cd Java-FINAL
     ```

   - Run the following commands to clean and compile the project:
     ```bash
     mvn clean
     mvn compile
     ```

### 4. Run the Application
   - After compiling the project, you can run the application with the following command:
     ```bash
     mvn exec:java -Dexec.mainClass="ecommerce.App"
     ```

   - The main menu will appear, allowing you to interact with the E-Commerce application.

---

## 3. Usage Instructions

Once the application is running, you will see the main menu. Follow these steps to interact with the application:

1. **Input Menu Number**: Simply input the number that corresponds to the section of the application you would like to use. For example:
   - To register, enter `1`
   - To login, enter `2`
   - To exit, enter `3`

2. **Follow On-Screen Instructions**: After selecting an option, follow the instructions to complete the desired action, whether you want to view a list of all products or details about a specific product.

3. **Exit the Application**: To exit the application at any point, you can select the exit option (when available) from the main menu or press `Ctrl+C` in your terminal.

---

## 4. Troubleshooting

If you encounter issues during installation or while running the application, here are some common problems and solutions:

### **Problem: Java Not Found**
   - **Cause**: The Java Development Kit (JDK) is not installed, or the `java` command is not available in your system's PATH.
   - **Solution**: Install JDK 8 or later and ensure that the `java` command is accessible from the terminal.
     - On **Windows**, you can download the JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
     - On **Linux** or **macOS**, use a package manager (e.g., `apt-get` on Ubuntu, `brew` on macOS).

### **Problem: Maven Not Found**
   - **Cause**: Maven is not installed, or the `mvn` command is not available in your system's PATH.
   - **Solution**: Install Maven from [Maven's official website](https://maven.apache.org/install.html) and add it to your PATH.

### **Problem: Database Connection Issues**
   - **Cause**: The application requires a PostgreSQL database, and there may be a connection issue.
   - **Solution**: Ensure that PostgreSQL is installed and running. Create a database for the application and update the database connection settings in the code if needed (e.g., username, password, URL).

### **Problem: Application Crashes Upon Running**
   - **Cause**: This can happen for various reasons, such as missing dependencies, incorrect setup, or database issues.
   - **Solution**: Check the stack trace for error details and resolve the issue based on the error messages. Common solutions include:
     - Ensure all dependencies are correctly installed via Maven (`mvn clean install`).
     - Check the database connection details in the configuration files.
     - Ensure your terminal/CLI environment is set up correctly to run Java applications.

### **Problem: Application Does Not Respond After Menu Selection**
   - **Cause**: This may happen if there's an issue in the logic flow or missing data in the database.
   - **Solution**: Ensure that the database is populated with the necessary data (e.g., products, users). Debug the application by adding print statements or checking the logs to identify where the execution is halting.

If the issue persists, consult the project's issues section on GitHub, or contact any of the repository contributers for further assistance.
