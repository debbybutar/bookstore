package com.HTTP;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.HTTP.model.PostAccountV1AuthorizedObject;
import com.google.gson.Gson;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostAccountV1Authorized {
    String validUsername = "test212";
    String validPassword = "Testuser1!";
    String invalidUsername = "Test837467476464664647";
    String invalidPassword = "a";
    String requestBody;
    String apiPath = "/Account/v1/Authorized";
    String responseBody;
    Response response;

    Gson gson = new Gson();
    PostAccountV1AuthorizedObject objectResponse;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    public void testValidUsernameAndPassword() {
        //set payload
        requestBody = String.format(
            "{\n" +
            "  \"userName\": \"%s\",\n" +
            "  \"password\": \"%s\"\n" +
            "}", validUsername, validPassword);
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
        assertEquals(response.getStatusCode(), 200, "Succes Code is unmatch");
        assertEquals( response.getBody().asString(), "true", "Response Body is unmatch");
    }

    @Test
    public void testInvalidPassword() {
        //set payload
        requestBody = String.format(
            "{\n" +
            "  \"userName\": \"%s\",\n" +
            "  \"password\": \"%s\"\n" +
            "}", validUsername, invalidPassword);
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
        objectResponse = gson.fromJson(responseBody, PostAccountV1AuthorizedObject.class);

        assertEquals(response.getStatusCode(), 404, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1207", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "User not found!", "Response Message is unmatch");
    }

    @Test
    public void testInvalidUsername() {
        //set payload
        requestBody = String.format(
            "{\n" +
            "  \"userName\": \"%s\",\n" +
            "  \"password\": \"%s\"\n" +
            "}", invalidUsername, validPassword);
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
        objectResponse = gson.fromJson(responseBody, PostAccountV1AuthorizedObject.class);

        assertEquals(response.getStatusCode(), 404, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1207", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "User not found!", "Response Message is unmatch");
    }

    @Test
    public void testMissingPassword() {
        //set payload
        requestBody = "{\n" +
                "  \"userName\": \"" + validUsername + "\"\n" +
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
        objectResponse = gson.fromJson(responseBody, PostAccountV1AuthorizedObject.class);

        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1200", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "UserName and Password required.", "Response Message is unmatch");
    }

    @Test
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
        objectResponse = gson.fromJson(responseBody, PostAccountV1AuthorizedObject.class);

        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1200", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "UserName and Password required.", "Response Message is unmatch");
    }

    @Test
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
        objectResponse = gson.fromJson(responseBody, PostAccountV1AuthorizedObject.class);
        assertEquals(response.getStatusCode(), 400, "Error Code is unmatch");
        assertEquals( objectResponse.getCode(), "1200", "Response Code is unmatch");
        assertEquals( objectResponse.getMessage(), "UserName and Password required.", "Response Message is unmatch");
    }

}
