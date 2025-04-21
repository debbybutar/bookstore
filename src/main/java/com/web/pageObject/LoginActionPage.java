package com.web.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginActionPage {

    private WebDriver driver;

    public LoginActionPage(WebDriver driver) {
        this.driver = driver;
    }

    //OBJECT
    private By iptUsernameField = By.id("userName");
    private By iptPasswordField = By.id("password");
    private By btnLogin = By.id("login");
    private By btnRegister = By.id("newUser");
    private By txtErrMessage = By.id("name");

    // ACTION
    public void setUsername(String username) {
        driver.findElement(iptUsernameField).sendKeys(username); 
    }

    public void setPassword(String password) {
        driver.findElement(iptPasswordField).sendKeys(password); 
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement buttonLoggin = wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonLoggin);
        buttonLoggin.click(); 
    }

    public void openRegisterPage() {
        WebElement registerBtn = driver.findElement(btnRegister);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerBtn);
        registerBtn.click();
    }

    public void assertErrorMessage(String expMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(txtErrMessage));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errMessage);
        String actMessage = errMessage.getText();
        Assert.assertEquals(actMessage, expMessage, "Error message did not match!");
    }

    public void assertLoginPage(String expUrl){
        // get current url
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals(expUrl) : "Expected URL: " + expUrl + ", but got: " + currentUrl;
        //assert login form
        Assert.assertTrue( driver.findElement(iptUsernameField).isDisplayed(), "UserName Field should be visible");
        Assert.assertTrue( driver.findElement(iptPasswordField).isDisplayed(), "Password Field should be visible");
        Assert.assertTrue( driver.findElement(btnLogin).isDisplayed(), "Login Button should be visible");
        Assert.assertTrue( driver.findElement(btnRegister).isDisplayed(), "New User Button should be visible");
    }
    
}
