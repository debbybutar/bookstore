package com.UI.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterActionPage {

    private WebDriver driver;

    public RegisterActionPage(WebDriver driver) {
        this.driver = driver;
    }
    
    //Object
    private By iptFirstnameField = By.id("firstname");
    private By iptLastnameField = By.id("lastname");
    private By iptUsernameField = By.id("userName");
    private By iptPasswordField = By.id("password");
    private By iptCaptchaField = By.xpath("//*[@class='recaptcha-checkbox-border']");
    private By btnRegister = By.id("register");
    private By btnBackToLogin = By.id("gotologin");

    // ACTION
    public void setFirstname(String username) {
        WebElement firstNameField = driver.findElement(iptFirstnameField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstNameField);
        firstNameField.sendKeys(username); 
    }

    public void setLastname(String username) {
        driver.findElement(iptLastnameField).sendKeys(username); 
    }

    public void setUsername(String username) {
        driver.findElement(iptUsernameField).sendKeys(username); 
    }

    public void setPassword(String username) {
        driver.findElement(iptPasswordField).sendKeys(username); 
    }

    public void sendRegisterForm(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
    
        // Wait until the captcha checkbox is clickable
        wait.until(ExpectedConditions.elementToBeClickable(iptCaptchaField));

        WebElement captchaCheckbox = driver.findElement(iptCaptchaField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", captchaCheckbox); 

        // Now click the register button
        driver.findElement(btnRegister).click();
    }

    public void clickBackToLogin() {
        driver.findElement(btnBackToLogin).click(); 
    }

}
