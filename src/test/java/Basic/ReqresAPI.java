package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.nio.file.Path;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // orders tests alphabetically
public class ReqresAPI {
    static String userId; // Stores the user ID extracted from the response

    @Test
    public void a_getUsers() {
        // Set base URL, path, and API key
        String baseUrl = "https://reqres.in";
        String path = "/api/users";
        String apiKey = "reqres-free-v1";

        // Send GET request to fetch user list
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .queryParam("page", 2)
                .queryParam("api_key", apiKey)
                .log().all()
                .get();

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode); // Assert response status code is 200

        // Extract first user's ID from response and store in static variable
        userId = response.jsonPath().getString("data[0].id");
        System.out.println("First user ID: " + userId); // Print the user ID
    }

    @Test
    public void b_createUser() {
        // Set base URL, path (with userId), and API key
        String baseUrl = "https://reqres.in";
        String path = "/api/users" + userId; // Appends userId to path
        String apiKey = "reqres-free-v1";

        // JSON payload for user creation
        String payload = "{ \"name\": \"Morpheus\", \"job\": \"Tech Leader\" }";

        // Send POST request to create user
        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .queryParam("api_key", apiKey)
                .body(payload)
                .log().all()
                .post()
                .then()
                .statusCode(201); // Assert response status code is 201 (Created)
    }

    @Test
    public void c_updatingUser() {
        // Set base URL, path (with userId), and API key
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2" + userId; // Appends userId to path
        String apiKey = "reqres-free-v1";

        // JSON payload for updating user
        String payload = "{ \"name\": \"Morpheus\", " +
                "\"job\": \"Delivery Manager\" }";

        // Send POST request to update user
        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .queryParam("api_key", apiKey)
                .body(payload)
                .log().all()
                .post()
                .then()
                .statusCode(201); // Assert response status code is 201 (Created)

    }

    @Test
    public void d_deleting() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2" + userId;
        String apiKey = "reqres-free-v1";

        //DELETE request to remove the weather station by ID
        RestAssured.given()
                .baseUri(baseUrl) // Set base URI
                .basePath(path) // Set base path
                .queryParam("appid", apiKey) // Add API key as query parameter
                .log().all() // Log request details
                .delete(userId) // Pass station ID as path parameter
                .then()
                .statusCode(401); // Assert that status code is 200 (OK)
    }
}
