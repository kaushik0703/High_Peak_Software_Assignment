package com.highpeak.qa.tests;

import com.highpeak.qa.pages.HomePage;
import com.highpeak.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(props.getProperty("username"));
        loginPage.enterPassword(props.getProperty("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDashboardDisplayed(), "Dashboard should be displayed after login");
    }

    @Test(priority = 2)
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(props.getProperty("username"));
        loginPage.enterPassword(props.getProperty("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        homePage.logout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Should be redirected to login page after logout");
    }

    @Test(priority = 3)
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("InvalidUser");
        loginPage.enterPassword("InvalidPass");
        loginPage.clickLogin();

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertEquals(errorMsg, "Invalid credentials", "Error message should match");
    }
}
