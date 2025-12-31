package com.highpeak.qa.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties props;

    @BeforeMethod
    public void setUp() {
        try {
            props = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Test setup failed due to configuration issues.");
        }

        String browser = props.getProperty("browser", "chrome");

        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            int implicitWait = Integer.parseInt(props.getProperty("implicit.wait", "10"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.get(props.getProperty("url"));
        } catch (Exception e) {
            System.err.println("Driver initialization failed: " + e.getMessage());
            if (driver != null) {
                driver.quit();
            }
            throw e;
        }
    }

    @AfterMethod
    public void tearDown(org.testng.ITestResult result) {
        try {
            if (result.getStatus() == org.testng.ITestResult.FAILURE) {
                String path = com.highpeak.qa.utils.TestUtils.captureScreenshot(driver, result.getName());
                System.out.println("Screenshot captured on failure: " + path);
            }
        } catch (Exception e) {
            System.err.println("Error during teardown/screenshot: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
