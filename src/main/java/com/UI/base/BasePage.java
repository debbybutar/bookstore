package com.UI.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("Running @BeforeMethod: setting up ChromeDriver");
        //set up browser
        WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
        driver = new ChromeDriver();
    }
    
    public void openUrl(String url) {
        if (driver != null) {
            driver.get(url);
        } else {
            System.out.println("Driver is not initialized!");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            // driver.quit();
        }
    }
}
