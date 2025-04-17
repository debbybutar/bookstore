package com.HTTP;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.HTTP.model.GetBookStoreV1BooksObject;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import static io.restassured.RestAssured.given;


public class GetBookStoreV1Books {
    String apiPath = "/BookStore/v1/Books";
    Response response;
    String responseBody;
    
    Gson gson = new Gson();
    GetBookStoreV1BooksObject objectResponse;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    public void testGetListBooks() {
        response = given()
                .when()
                .get(apiPath)
                .then()
                .statusCode(200)
                .extract()
                .response();

        //assert
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, GetBookStoreV1BooksObject.class);
        
        assertEquals(response.getStatusCode(), 200, "Succes Code is unmatch");
        assertTrue(objectResponse.getBooks().size() > 0, "List Books should be greater than 0");

        for (GetBookStoreV1BooksObject.Book book : objectResponse.getBooks()) {
            assertNotNull(book.getIsbn(), "ISBN should not be null");
            assertNotNull(book.getTitle(), "ISBN should not be null");
            assertNotNull(book.getSubTitle(), "ISBN should not be null");
            assertNotNull(book.getAuthor(), "ISBN should not be null");
            assertNotNull(book.getPublish_date(), "ISBN should not be null");
            assertNotNull(book.getPublisher(), "ISBN should not be null");
            assertTrue(book.getPages() > 0, "Pages should be greater than 0");
            assertNotNull(book.getDescription(), "ISBN should not be null");
            assertNotNull(book.getWebsite(), "ISBN should not be null");
        }
    }
}
