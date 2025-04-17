package com.HTTP;

import java.util.Random;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.HTTP.model.PostAccountV1UserObject;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostAccountV1User {
    String existingUsername = "test212";
    String validPassword = "Testuser1!";
    String newUsername = "";
    String invalidPassword = "a";
    String requestBody;
    String apiPath = "/Account/v1/User";
    String responseBody;
    Response response;

    // For generating random test data
    Random rand = new Random();
    int randomInt;

    Gson gson = new Gson();
    PostAccountV1UserObject objectResponse;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test(priority = 1)
    public void testExistingUser() {
        //set payload
        requestBody = String.format(
            "{\n" +
            "  \"userName\": \"%s\",\n" +
            "  \"password\": \"%s\"\n" +
            "}", existingUsername, validPassword);
        //hit and get response from endpoint
       response = given() 
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(apiPath)
                .then()
                .extract().response();

        // Assert response
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, PostAccountV1UserObject.class);

        assertEquals(response.getStatusCode(), 406, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1204", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "User exists!", "Response Message is unmatch");
    }
    
    @Test(priority = 2)
    public void testValidNewUser() {
        //set payload
        randomInt = rand.nextInt(10000);
        newUsername = "test" + randomInt + "a";

        requestBody = String.format(
            "{\n" +
            "  \"userName\": \"%s\",\n" +
            "  \"password\": \"%s\"\n" +
            "}", newUsername, validPassword);
        //hit and get response from endpoint
       response = given() 
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(apiPath)
                .then()
                .extract().response();

        // Assert response
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, PostAccountV1UserObject.class);
        assertEquals(response.getStatusCode(), 201, "Success Code is unmatch");
        assertEquals( objectResponse.getUsername(), newUsername, "NewUsername is unmatch");
    }
    
    @Test(priority = 3)
    public void testInvalidPassword() {
        randomInt = rand.nextInt(10000);
        newUsername = "test" + randomInt + "a";
        //set payload
        requestBody = String.format(
            "{\n" +
            "  \"userName\": \"%s\",\n" +
            "  \"password\": \"%s\"\n" +
            "}", newUsername, invalidPassword);
        //hit and get response from endpoint
       response = given() 
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(apiPath)
                .then()
                .extract().response();

        // Assert response
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, PostAccountV1UserObject.class);

        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1300", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.", "Response Message is unmatch");
    }
    
    @Test(priority = 4)
    public void testMissingPassword() {
        randomInt = rand.nextInt(1000);
        newUsername = "test" + randomInt;
        //set payload
        requestBody = "{\n" +
        "  \"userName\": \"" + newUsername + "\"\n" +
        "}";
        //hit and get response from endpoint
       response = given() 
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(apiPath)
                .then()
                .extract().response();

        // Assert response
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, PostAccountV1UserObject.class);

        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1200", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "UserName and Password required.", "Response Message is unmatch");
    }
    
    @Test(priority = 5)
    public void testMissingUsername() {
        //set payload
        requestBody = "{\n" +
        "  \"password\": \"" + validPassword + "\"\n" +
        "}";
        //hit and get response from endpoint
       response = given() 
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(apiPath)
                .then()
                .extract().response();

        // Assert response
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, PostAccountV1UserObject.class);

        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1200", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "UserName and Password required.", "Response Message is unmatch");
    }
    
    @Test(priority = 5)
    public void testEmptyRequest() {
        //set payload
        requestBody = "{}";
        //hit and get response from endpoint
       response = given() 
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(apiPath)
                .then()
                .extract().response();

        // Assert response
        responseBody = response.getBody().asString();
        // Deserialize response JSON to POJO
        objectResponse = gson.fromJson(responseBody, PostAccountV1UserObject.class);

        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1200", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "UserName and Password required.", "Response Message is unmatch");
    }


    

}
