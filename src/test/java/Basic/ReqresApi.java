package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.Assert;
import org.testng.annotations.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReqresApi {

static String userID;

    // This test creates a user and stores the userID for subsequent tests

@Test
public void a_createUser() {
    String baseUrl = "https://reqres.in";
    String pathUrl = "/api/users";
    String apiKey = "reqres-free-v1";
    String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

    Response response = RestAssured.given()
            .baseUri(baseUrl).basePath(pathUrl)
            .contentType(ContentType.JSON)
            .header("x-api-key", apiKey)
            .body(payload)
            .post();


    // Assert that the status code is 201 (Created)
    int actualStatusCode = response.getStatusCode();
    Assert.assertEquals( actualStatusCode, 201);

    // Optionally, print the response
    System.out.println("Response boby:" + response.asString());
    System.out.println("Status code:" + actualStatusCode);

    /** Extract UserID from the response*/
    userID = response.jsonPath().getString("id");
    System.out.println("User ID created: " + userID);
    }

    @Test
    public void b_getUser() {
        String baseUrl = "https://reqres.in";
        String pathUrl = "/api/users/" + userID; // Assuming UserID is set from the previous test
        String apiKey = "reqres-free-v1";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(pathUrl)
                .header("x-api-key", apiKey)
                .log().all()
                .get();

    }
    @Test
    public void c_updateUser() {
        String baseUrl = "https://reqres.in";
        String pathUrl = "/api/users/" + userID;
        String apiKey = "reqres-free-v1";
        String updatedPayload = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(pathUrl)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .body(updatedPayload)
                .put();

        // Assert that the status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(),200);

        // Print the response
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Updated Response Body: " + response.getBody().asString());
    }
    @Test
    public void d_patchUser() {
        String baseUrl = "https://reqres.in";
        String pathUrl = "/api/users/" + userID;
        String apiKey = "reqres-free-v1";
        String updatedPayload = "{ \"name\": \"zulumega\", \"job\": \"ekuphakameni\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(pathUrl)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .body(updatedPayload)
                .patch();

        // Assert that the status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200);

        // Print the response
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Updated Response Body: " + response.getBody().asString());
    }
    @Test
    public void e_deleteUser() {
        String baseUrl = "https://reqres.in";
        String pathUrl = "/api/users/" + userID; // Use the userID from the createUser test
        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(pathUrl)
                .header("x-api-key", apiKey)
                .delete();

        // Assert that the status code is 204 (No Content)
        Assert.assertEquals( response.getStatusCode(),204);

        // Print the status code to confirm deletion
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("User with ID " + userID + " deleted successfully.");
    }


}

