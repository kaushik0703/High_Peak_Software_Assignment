# OrangeHRM Automation Test Suite

This project is a Selenium-based automated test suite designed to test the functionalities of the [OrangeHRM Open Source demo site](https://opensource-demo.orangehrmlive.com/). It uses the **Page Object Model (POM)** design pattern for better maintainability and readability.

## 🚀 Tech Stack

- **Language:** Java 11
- **Automation Tool:** Selenium WebDriver (v4.27.0)
- **Test Framework:** TestNG (v7.10.2)
- **Build Tool:** Maven
- **Browser Management:** WebDriverManager (v5.9.2)

## 📁 Project Structure

```text
OrangeHRMAutomation/
├── src/
│   ├── main/java/com/highpeak/qa/pages       # Page Objects
│   ├── test/java/com/highpeak/qa/tests       # Test Classes & Base Test
│   ├── test/java/com/highpeak/qa/utils       # Utility Classes (Screenshots, Waits)
│   └── test/resources/
│       ├── config.properties                 # Configuration settings
│       └── testng.xml                        # TestNG Suite Execution file
├── pom.xml                                   # Project Dependencies & Plugins
└── README.md                                 # Project Documentation
```

## 🛠 Prerequisites

Ensure you have the following installed on your system:
- **Java Development Kit (JDK) 11** or higher.
- **Apache Maven** (for dependency management and running tests).
- **Chrome** or **Firefox** browser installed.

## ⚙️ Configuration

You can configure the test environment in `src/test/resources/config.properties`:
- `url`: The application URL.
- `username`: Login username.
- `password`: Login password.
- `browser`: Choose between `chrome` or `firefox`.
- `implicit.wait`: Implicit wait time in seconds.

## 🛠 Tools Used

- **Java JDK 11**: For core logic and test scripts.
- **Selenium WebDriver**: For browser automation.
- **TestNG**: For test execution and assertions.
- **Maven**: For dependency management and build life cycle.
- **WebDriverManager**: For automated driver binary management.
- **Apache Commons IO**: For handling file operations and screenshots.

## 🚀 Challenges Faced

1.  **Dynamic UI Elements**: OrangeHRM is a modern Single Page Application (SPA). Handling dynamic elements and varying page load times required robust integration of **Explicit Waits** to avoid flaky tests.
2.  **Session Management**: Testing logout and subsequent redirection required careful synchronization to ensure the session was fully terminated before attempting to verify the login page redirection.
3.  **Cross-Browser Setup**: Ensuring the `config.properties` could seamlessly switch between Chrome and Firefox while maintaining consistent wait times and window behavior.
4.  **Error Handling**: Implementing robust exception handling in `BaseTest` to ensure the driver is always closed propery, even if the test crashes or environment fails.

## 🏃 How to Run Tests

### Run from Command Line
To execute all tests defined in the `testng.xml` file, run the following command from the project root:
```bash
mvn clean test
```

### Run from IDE
1. Import the project as a **Maven** project.
2. Right-click on `src/test/resources/testng.xml`.
3. Select **Run '.../testng.xml'**.

## 📊 Reports & Screenshots
- **TestNG Results:** After execution, you can find the detailed reports in the `target/surefire-reports` directory.
- **Screenshots:** On test failure, screenshots are automatically captured and saved in the `/screenshots/` directory.
