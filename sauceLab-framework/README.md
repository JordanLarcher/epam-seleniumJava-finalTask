# epam-seleniumJava-finalTask

# Swag Labs - Test Automation

This project contains automated tests for the Swag Labs demo e-commerce website. 
It uses Selenium WebDriver to automate browser interactions and is built with Maven.

## Technologies Used

- **Test Automation:** Selenium WebDriver
- **Build Tool:** Maven
- **Test Runner:** JUnit 5
- **Assertion Library:** Hamcrest
- **Logging:** SLF4J with Simple Logger
- **Browsers:** Firefox, Edge

## Project Structure


.
├── pom.xml
├── README.md
└── src
└── test
└── java
└── com
└── swaglabs
├── pages
│   ├── LoginPage.java
│   └── ProductsPage.java
├── tests
│   ├── BaseTest.java
│   └── LoginTest.java
└── utils
└── WebDriverFactory.java


## How to Run Tests

1. **Prerequisites:**
   - Java Development Kit (JDK) 24 installed.
   - Apache Maven installed.
   - Mozilla Firefox and Microsoft Edge browsers installed.

2. **Execution:**
   - Open a terminal or command prompt.
   - Navigate to the project's root directory.
   - Run the following command:
     ```bash
     mvn clean test
     ```
   - This command will compile the project, download dependencies, and run the tests in parallel using both Firefox and Edge.

## Test Cases

The following test cases are automated in this project:

- **UC-1: Test Login with Empty Credentials**
  - **Description:** Verifies that an appropriate error message is displayed when attempting to log in with empty username and password fields.
  - **Steps:**
    1. Navigate to the login page.
    2. Leave the username and password fields empty.
    3. Click the "Login" button.
    4. **Expected Result:** An error message "Epic sadface: Username is required" is displayed."

- **UC-2: Test Login with a Valid Username and Empty Password**
  - **Description:** Ensures that an error message is shown when the password field is empty.
  - **Steps:**
    1. Navigate to the login page.
    2. Enter a valid username (`standard_user`).
    3. Leave the password field empty.
    4. Click the "Login" button.
    5. **Expected Result:** An error message "Epic sadface: Password is required" is displayed."

- **UC-3: Test Login with a Locked User**
  - **Description:** Ensures that an error message is shown when the user is locked.
  - **Steps:**
    1. Navigate to the login page.
    2. Enter a locked username (`locked_out_user`).
    3. Enter password.
    4. Click the "Login" button.
    5. **Expected Result:** An error message "Epic sadface: Sorry, this user has been locked out."

- **UC-4: Test Successful Login with Valid Credentials**
  - **Description:** Validates that a user can successfully log in with correct credentials and is redirected to the products page.
  - **Steps:**
    1. Navigate to the login page.
    2. Enter a valid username (`standard_user`).
    3. Enter a valid password (`secret_sauce`).
    4. Click the "Login" button.
    5. **Expected Result:** The user is redirected to the products page, and the title "Swag Labs" is visible.

- **UC-5: Test Add products to Cart and count them**
    - **Description:** Validate that the user can add products to the cart and that the cart counter is incremented.
    - **Steps:**
        1. Navigate to the login page.
        2. Enter a valid username (`standard_user`).
        3. Enter a valid password (`secret_sauce`).
        4. Click the "Login" button.
        5. Click on the buttons Add to cart
        6. Check the cart icon
        7. **Expected Result:** The user must be able to add products to the cart and see the cart counter incremented.