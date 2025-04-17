package com.UI;

import com.UI.base.BasePage;
import com.UI.pageObject.LoginActionPage;
import com.UI.pageObject.ProfileActionPage;
import com.UI.pageObject.RegisterActionPage;

import java.util.Random;
import org.testng.annotations.*;

/**
 * Test class for user registration and login functionality
 */
public class LoginTest extends BasePage {
    
    static String loginPageUrl = "https://demoqa.com/login";
    static String profilePageUrl = "https://demoqa.com/profile";
    static String firstName = "";
    static String lastName = "";
    static String username = "";
    static String password = "";
    static String existUsername = "test212";
    static String validPassword = "TestUser1!";
    static String invalidPassword = "a";
    static String expErrorMessage = "";
    
    // For generating random test data
    Random rand = new Random();
    int randomInt;

    LoginActionPage loginPage;
    RegisterActionPage registerPage;
    ProfileActionPage profilePage;
    
    @BeforeClass
    @Override
    public void setUp() {
        super.setUp(); 
        loginPage = new LoginActionPage(driver);
        registerPage = new RegisterActionPage(driver);
        profilePage = new ProfileActionPage(driver);
    }
    
    // disable cause can't handle captcha
    // @Test
    public void testRegistrationForNewAccount() {
        // Generate random number for unique user data
        randomInt = rand.nextInt(1000);

        // Set valid data for registration with random values to avoid duplicates
        firstName = "test" + randomInt;
        lastName = "user" + randomInt;
        username = "testuser" + randomInt;
        password = "TestUser1!";

        // Step 1: Open login page
        openUrl(loginPageUrl);
        
        // Step 2: Navigate to registration page
        loginPage.openRegisterPage();
        
        // Step 3: Fill in the registration form
        registerPage.setFirstname(firstName);
        registerPage.setLastname(lastName);
        registerPage.setUsername(username);
        registerPage.setPassword(password);
        registerPage.sendRegisterForm();
    }

    @Test(priority = 1)
    public void testLoginUsingInvalidPassword() {
        // Open login page
        openUrl(loginPageUrl);
        //fill login form
        loginPage.setUsername(existUsername);
        loginPage.setPassword(invalidPassword);
        loginPage.clickLoginButton();
        //assert login
        expErrorMessage = "Invalid username or password!";
        loginPage.assertErrorMessage(expErrorMessage);
    }
    @Test(priority = 2)
    public void testLoginUsingValidAccount() {
        // Open login page
        openUrl(loginPageUrl);
        //fill login form
        loginPage.setUsername(existUsername);
        loginPage.setPassword(validPassword);
        loginPage.clickLoginButton();
        //assert login
        profilePage.assertLoginSuccess(existUsername, profilePageUrl);
    }
    @Test(priority = 3)
    public void testSuccessLogout() {
        //fill login form
        profilePage.logout();
        //assert login
        loginPage.assertLoginPage(loginPageUrl);
    }
}
