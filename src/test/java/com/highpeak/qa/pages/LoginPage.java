package com.highpeak.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//div[@role='alert']");
    private By forgotPasswordLink = By.xpath("//p[contains(@class, 'login-forgot-header')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}
