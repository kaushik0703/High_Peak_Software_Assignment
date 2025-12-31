package com.highpeak.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By userDropdown = By.className("oxd-userdropdown-name");
    private By logoutLink = By.xpath("//a[text()='Logout']");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isDashboardDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}
