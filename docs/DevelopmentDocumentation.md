# Development Documentation

# Table of Contents

- [Source Code Structure](#source-code-structure)
- [Build Process](#build-process)
  - [Requirements](#requirements)
  - [Compilation and Execution](#compilation-and-execution)
- [Compiler Dependencies](#compiler-dependencies)
- [Setting Up A Database For Development](#setting-up-a-database-for-development)
- [Retrieving and Contributing to the Repository](#retrieving-and-contributing-to-the-repository)

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

## Build Process

### Requirements for Java:
- **Java Development Kit (JDK) version 8 or higher**
    - Required for Java compilation.
- **Git**
    - Required for cloning repository and contributing to project.
- **PostgreSQL Database**
    - This System uses a PostgreSQL database to interact with data; ensure a database is accessible for interaction
- **Maven**
    - Maven is required to download pom dependencies and access the menu from the terminal

### Compilation:
_(in terminal)_
1. `git clone https://github.com/sarwoodford/Java-FINAL.git`
2. `cd Java-FINAL`
3. `mvn clean`
4. `mvn compile`

### Run E-Commerce CLI Application:
_(in terminal)_
1. `mvn exec:java -Dexec.mainClass="ecommerce.App"`
2. Choose one of the initial options:
    - Register 
    - Login
    - Exit
3. Follow displayed instructions, input required values, and hit enter.
   - If an error occurs with the entered value, a message will be displayed informing the user that an invalid value was entered, and they will be prompted to re-enter.
4. Choose a menu to display: 
    - Admin Menu
    - Seller Menu
    - Buyer Menu
5. Follow displayed instructions, input required values, and hit enter.
   - If an error occurs with the entered value, a message will be displayed informing the user that an invalid value was entered, and they will be prompted to re-enter.

## Compiler Dependencies

### Class Relationships
- Classes within the system depend on each other.
  - Example: `ProductService` needs access to `ProductDAO` and `Product`.
- Import statements are used to manage this.
  - Example: `import ecommerce.model.Product`
- Subclasses are dependent on main classes:
  - `Admin`, `Seller` and `Buyer` depend on `User`.

### Java Library Dependencies
- **Data Structure**: `java.util.ArrayList` and `java.util.List` are utilized within the system and must be imported.
- **Utilities**: `java.util.Scanner` is utilized within the system and must be imported.

### Pom Dependencies 
- **Maven**: included in pom.xml file, maven will automatically download to manage required dependencies
- **jBcrypt**: used to store passwords securely, converting them into irreversible hashes to be stores and utilized in the 'login' option of the user menu.
- **PostgreSQL**: The PostgreSQL JDBC driver for connecting to and interacting with a PostgreSQL database.

## Setting Up A Database For Development 

1. **Create a Database in a postgreSQL environment (ex: pgAmin):
    - Database Name: choose a name that reflects your databases purpose (ex: eccomerce)
    - Database Host: localhost 
    - Port: 5432
    - Username: Defines who has access to the database, insert your own username
    - Password: Your password for chosen postgreSQL environemnt
3. **Configure Database In Code:
    - In the code, there are placeholders for URL, USER and PASSWORD. 
    - Replace placeholders with your own information to connect to your database 
    - **tip:** don't push the database connection information to github (this allows anyone on github to acess your data), simply commit it locally so you can access it.
                

### Retrieve Source Code from GitHub Repository

**To get a local copy of the project running:**

_(In terminal)_

`` git clone https://github.com/sarwoodford/Java-FINAL.git ``

`cd Java-FINAL`

Instructions for Contributing:

**1. Create a new branch for your feature.**

_(In terminal)_

`git checkout -b Feature/feature-name`

**2. Add thorough comments as you add features in your branch.**

Comments inform other contributors and other people viewing your repository

About why you made the updates that you did.

**3. Use consistent naming conventions and clear names.**

Note that the program consistently uses camelCase for variables and PascalCase for classes, other contributors should keep that consistency. Variable and method names being clear are also important (ex: authorName rather than name.)

**4. Commit changes in increments when you finish each step, including a detailed commit message.**

_(In terminal)_

`git commit -m <commit message>`

**5. Create a pull request.**

Navigate to a GitHub browser and create a pull request. This notifies other contributors of your committed changes and allows them to review them before merging your branch to the main branch.


