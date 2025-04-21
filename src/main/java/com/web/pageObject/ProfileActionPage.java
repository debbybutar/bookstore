package com.web.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProfileActionPage {

    private WebDriver driver;

    public ProfileActionPage(WebDriver driver) {
        this.driver = driver;
    }
    
    //Object
    private By txtUserName = By.id("userName-value");
    private By btnLogout = By.id("submit");
    private By tabBooks = By.xpath("//*[text()='Book Store']");
    
    // ACTION
    public void assertLoginSuccess(String expUserName, String expUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(txtUserName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", username);
        String currUserName =  username.getText();
        Assert.assertEquals(currUserName, expUserName, "Username did not match!");

        // get current url
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.equals(expUrl) : "Expected URL: " + expUrl + ", but got: " + currentUrl;
    }

    public void logout() {
        driver.findElement(btnLogout).click(); 
    }

    public void openBookPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement books = wait.until(ExpectedConditions.visibilityOfElementLocated(tabBooks));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", books);
        
        books.click(); 
    }

}
