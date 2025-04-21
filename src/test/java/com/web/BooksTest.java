package com.web;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.web.base.BasePage;
import com.web.pageObject.BooksActionPage;
import com.web.pageObject.LoginActionPage;
import com.web.pageObject.ProfileActionPage;

public class BooksTest extends BasePage {
    static String bookPageUrl = "https://demoqa.com/books";
    static String loginPageUrl = "https://demoqa.com/login";
    static String profilePageUrl = "https://demoqa.com/profile";
    static String existUsername = "test212";
    static String validPassword = "TestUser1!";

    BooksActionPage bookPage;
    LoginActionPage loginPage;
    ProfileActionPage profilePage;
    
    @BeforeClass
    public void setUp() {
        super.setUp(); 
        bookPage = new BooksActionPage(driver);
        loginPage = new LoginActionPage(driver);
        profilePage = new ProfileActionPage(driver);

        //user login
        openUrl(loginPageUrl);
        //fill login form
        loginPage.setUsername(existUsername);
        loginPage.setPassword(validPassword);
        loginPage.clickLoginButton();
        //assert login
        profilePage.assertLoginSuccess(existUsername, profilePageUrl);
    }

    @BeforeMethod
    public void openBookPage() {
        openUrl(bookPageUrl);
    }

    @Test(priority = 1)
    public void testSearchBookBasedOnTitle() {
        //search book by title
        //get existing title book
        //input title to search fielad
        String title = bookPage.getTitleBookFromFirstRow();
        bookPage.searchBook(title);
        //assert search based on title
        bookPage.assertBookTitle(title);
    }
    @Test(priority = 2)
    public void testSearchBookBasedOnAuthor() {
        //search book by author
        //get existing author book
        //input author to search fielad
        String author = bookPage.getAuthorBookFromFirstRow();
        bookPage.searchBook(author);
        //assert search based on title
        bookPage.assertBookAuthor(author);
    }
    @Test(priority = 3)
    public void testSearchBookBasedOnPublisher() {
        //search book by publisher
        //get existing publisher book
        //input publisher to search fielad
        String publisher = bookPage.getPublisherBookFromFirstRow();
        bookPage.searchBook(publisher);
        //assert search based on title
        bookPage.assertBookPublisher(publisher);
    }
    @Test(priority = 4)
    public void testPaginationBookList() {
        //get existing title book
        String title = bookPage.getTitleBookFromFirstRow();
        //divide list book to some page
        bookPage.divideListBook();
        //checkNextPage
        bookPage.openNextPage();
        //assert book
        bookPage.assertBookTitleNotSame(title);
        //check previous page
        bookPage.openPreviousPage();
        //assert title previous page
        bookPage.assertBookTitle(title);
    }

    /**
     * NOTE:
     * Test case for opening book detail is not implemented
     * due to a known bug on the web (detail page cannot be opened)
     */
}
