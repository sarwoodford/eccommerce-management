# User Documentation
 
## Table of Contents
1. [Overview](#1-Overview)
2. [How to Start and Access the Application](#2-How-to-Start-and-Access-the-Application)
3. [Class Diagram](#3-Class-Diagram)
4. [Class Overview and Explanation](#4-Class-Overview-and-Explanation)
   - [App.java](#41-App.java)
   - [User (Abstract Class)](#42-User-Abstract-Class)
   - [Admin (Subclass of User)](#43-Admin-Subclass-of-User)
   - [Seller (Subclass of User)](#44-Seller-Subclass-of-User)
   - [Buyer (Subclass of User)](#45-Buyer-Subclass-of-User)
   - [Product](#46-Product)
   - [ProductDAO (Utilizes Product)](#47-ProductDAO-Utilizes-Product)
   - [UserDAO (Utilizes User)](#48-UserDAO-Utilizes-User)
   - [ProductService (Utilizes ProductDAO)](#49-ProductService-Utilizes-ProductDAO)
   - [UserService](#5-UserService-Utilizes-UserDAO)

 
---
 
## 1. Overview
 
This application is a **Ecommerce System** developed to streamline the management of **User Objects**, **Admins**, **Sellers** and **Buyers**, as well as **Products**. The system enables users to **add, edit, and remove** products and users, as well as **view** users and products within the database.
 
### Key Features:

-**Main Menu**
    - **Users** are prompted with 3 options initally:
        -**Register**: Users are prompted to enter a username, password, email and role. If the credentials are valid, the new user is added to the database.
        -**Login**: Users are prompted to enter their username and password, if their credentials are valid they are then prompted to choose a menu to explore
            1. Admin Menu
            2. Buyer Menu
            3. Seller Menu
 
- **Admin Menu**:
  - **Admins** have several menu options: 
      - **view all users**: admins can view each user (admin, seller, buyer) within the database
      - **delete a user**: admins can delete a user from the database by entering their unique id.
      - **view all products**: admins can view all products within the database.
      - **exit**
 
- **Buyer Menu**:
  - **Buyers** have several menu options:
      - **view all products**: buyers can browse products within the database, including a product description
      - **view specific product**: buyers can view a specified product by entering the products unique id.
      - **exit**
 
- **Seller Menu**:
  - **Sellers** have several menu options:
    - **view all seller products**: sellers can view all products associated with a specific seller by entering the sellers unique id.
    - **add a product**: sellers can add a new product to the database
    - **update a product**: sellers can update fields related to an existing product within the database
    - **delete a product**: sellers can delete a product from the database by entering the products unique id
    - **exit**
 

---
## 2. How to Start and Access the Application
 
1. **Setup**: 
   - Ensure you have Java installed.
   - Place all Java files in an organized directory structure according to their package names (e.g., `ecommerce.model`, `ecommerce.service`, etc.).
 
2. **Running the Application**:
   - Clone the repository, then compile all Java using Maven in the command line:
     ```bash
1. `git clone https://github.com/sarwoodford/Java-FINAL.git`
2. `cd Java-FINAL`
3. `mvn clean`
4. `mvn compile`
     ```
   - Run the main menu:
     ```bash
     `mvn exec:java -Dexec.mainClass="ecommerce.App"`
     ```
   - You may also run the application in your favourite IDE using the built-in runtime environment. **Ensure you have all neccesary packages and extensions downloaded.**
   - The program's menu will now be displayed in the command line interface. Input the number that corresponds to the part of the program you wish to access (1= Register, 2= Login, 3= Exit)
   - You will now be prompted to choose a specific menu (Admin, Buyer, Seller)
   - After Choosing which menu you wish to access, follow the displayed prompted to choose a menu option, and then input required fields to complete desired action
   - Once you are done using the application, return to the main menu, and type option "3" to exit.


## 3. Class Diagram
 
The class diagram below depicts the relationships between classes, including associations between `User` and `Admin`, `Buyer` and `Seller`
[Ecommerce Database.pdf](https://github.com/user-attachments/files/18101459/Ecommerce.Database.pdf)


## 4. Class Overview and Explanation
 
### 4.1 `App.java`
- **Purpose**: Provides a console-based menu for interacting with the ecommerce system.
- **Responsibilities**: Allows users to interact with the ecommerce system to add, edit, and delete a product or user, It also provides methods which adhere to actions each user may wish to preform (ex: seller adds a new product) and methods to display all users or products within the database 
**Key Methods**:
  - `displayMainMenu()`: Displays options to register, login or exit menu
  - `displayAdminMenu()`: prompts user to **view all users**, **delete a user** and **view all products**
  - `displayBuyerMenu()`: prompts user to **display all products** and **display a specific product**
  - `displaySellerMenu()`: prompts user to **add a product**, **update a product**, **delete a product** and **display all products by seller**.

### 4.2 `User (Abstract Class)`
- **Purpose**: Base class for all Users.
- **Subclasses**:
    - `Admin`: represents admins within the ecommerce system
    - `Seller`: represents sellers within the ecommerce system
    - `Buyer`: represents customers within the ecommerce system
- **Key Method**:
    `isValidRole()`: verifies that a user inputs one of 3 valid roles (admin, seller, buyer)
 
### 4.3 `Admin` (Subclass of `User`)
- **Purpose**: Extends from User - Promptes modularity as User objects are divided into specific classes.
- **Attributes**: Contains common properties like `id`, `username`, `password`, `email` and `role`.
 
### 4.4 `Seller` (Subclass of `User`)
- **Purpose**: Extends from User - Promptes modularity as User objects are divided into specific classes.
- **Attributes**: Contains common properties like `id`, `username`, `password`, `email` and `role`.
 
### 4.5 `Buyer` (Subclass of `User`)
- **Purpose**: Extends from User - Promptes modularity as User objects are divided into specific classes.
- **Attributes**: Contains common properties like `id`, `username`, `password`, `email` and `role`.
 
### 4.6 `Product`
- **Purpose**: Represents a product within the ecommerce system.
- **Attributes**: `id`, `name`, `price`, `stock`, `sellerId`, `description`
 
### 4.7 `ProductDAO` (Utilizes `Product`)
- **Purpose**: Isolates database access logic for Products and acts as an intermediate between models and the main entry point (App)
- **Key Methods**:
    - `addProduct()`, `updateProduct()`, `deleteProduct()`, `getProductById()`: methods promote ease when performing CRUD operations (create, read, update, delete)
    - `getProduct()`, `getSellerproducts()`: display a list of products
  
### 4.8 `UserDAO` (Utilizes `User`)
- **Purpose**: Isolates database access logic for Users and acts as an intermediate between models and the main entry point (App)
- **Key Methods**:
    - `addUser()`, `getUserByUsername()`, `updateUser()`, `deleteUserById()`: methods promote ease when performing CRUD operations (create, read, update, delete)
    - `getAllUsers()`: display a list of users
    - `authenticateUser()`: ensures that a users login credentials are valid and that there is an association with the entered credentials and the users that are stored within the connected database 
 
### 4.9 `ProductService` (Utilizes `ProductDAO`)
- **Purpose**: Encapsulates business logic within the system - promotes modularity by separating business logic from the database organization in the DAOs.

### 5 `UserService` (Utilizes `UserDAO`)
- **Purpose**: Encapsulates business logic within the system - promotes modularity by separating business logic from the database organization in the DAOs.
 

 

---
