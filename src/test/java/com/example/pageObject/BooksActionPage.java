package com.example.pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BooksActionPage {

    private WebDriver driver;

    public BooksActionPage(WebDriver driver) {
        this.driver = driver;
    }
    //OBJECT
    private By txtTitleBookFirstRow = By.xpath("(//*[@class='action-buttons'])[1]//a");
    private By txtAuthorBookFirstRow = By.xpath("(//*[@role='gridcell'])[3]");
    private By txtPublisherBookFirstRow = By.xpath("(//*[@role='gridcell'])[4]");
    private By iptSearchField = By.id("searchBox");
    private By btnRowPerPage = By.xpath("//*[@aria-label='rows per page']");
    private By btnFivePerPage = By.xpath("(//*[@aria-label='rows per page']//option)[1]");
    private By btnNextPagination = By.xpath("//*[@type='button' and text()='Next']");
    private By btnPreviousPagination = By.xpath("//*[@type='button' and text()='Previous']");

    
    //ACTION
    public String getTitleBookFromFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(txtTitleBookFirstRow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);
        return title.getText();
    }

    public String getAuthorBookFromFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement author = wait.until(ExpectedConditions.visibilityOfElementLocated(txtAuthorBookFirstRow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", author);
        return author.getText();
    }

    public String getPublisherBookFromFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement publisher = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPublisherBookFirstRow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", publisher);
        return publisher.getText();
    }

    public void searchBook(String expText) {
        driver.findElement(iptSearchField).sendKeys(expText); 
    }

    public void assertBookTitle(String expText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(txtTitleBookFirstRow));
        String currTitle =  title.getText();
        Assert.assertEquals(currTitle, expText, "Title did not match!");
    }

    public void assertBookAuthor(String expText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement author = wait.until(ExpectedConditions.visibilityOfElementLocated(txtAuthorBookFirstRow));
        String currAuthor =  author.getText();
        Assert.assertEquals(currAuthor, expText, "Author did not match!");
    }

    public void assertBookPublisher(String expText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement publisher = wait.until(ExpectedConditions.visibilityOfElementLocated(txtPublisherBookFirstRow));
        String currPublisher =  publisher.getText();
        Assert.assertEquals(currPublisher, expText, "Publisher did not match!");
    }

    public void divideListBook(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement rowPerPage = wait.until(ExpectedConditions.visibilityOfElementLocated(btnRowPerPage));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rowPerPage);
        rowPerPage.click();
        driver.findElement(btnFivePerPage).click();
    }

    public void openNextPage(){
        driver.findElement(btnNextPagination).click();
    }

    public void openPreviousPage(){
        driver.findElement(btnPreviousPagination).click();
    }

    public void assertBookTitleNotSame(String expText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(txtTitleBookFirstRow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", title);

        String currTitle =  title.getText();
        Assert.assertNotEquals(currTitle, expText, "Title book should be different!");
    }
    
}
