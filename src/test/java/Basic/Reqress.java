package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Reqress {

    static String userID;

    @Test
    public void a_createUser() {
        RestAssured.baseURI = "https://reqres.in";

        String payload = "{\n" +
                "  \"name\": \"Brenda\",\n" +
                "  \"Surname\": \"Malinga\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";

        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract().response();
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 201);
        System.out.println("POST Response: User is successfully created " + response.asString());
        userID = response.jsonPath().getString("id");
    }

    // Get method for the created user
    @Test
    public void b_getUser() {
        int usedID = 7;
        RestAssured.baseURI = "https://reqres.in";
        // Use the userID from the previous test
        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .when()
                .get("/api/users/" + usedID)
                .then()
                .statusCode(200)
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("GET Response: " + response.asString());
        Assert.assertEquals(200, statusCode); // May fail if user is not found
    }

//Update method for the created user

    @Test
    public void c_updateUser() {
        RestAssured.baseURI = "https://reqres.in";

        String updatedPayload = "{\n" +
                "  \"name\": \"Brenda\",\n" +
                "  \"Surname\": \"Mtungwa\",\n" +
                "  \"job\": \"manager\"\n" +
                "}";

        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .body(updatedPayload)
                .when()
                .put("/api/users/" + userID)
                .then()
                .statusCode(200)
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("PUT Response: " + response.asString());
        Assert.assertEquals(200, statusCode); // 200 is expected for a successful update
    }

    // Delete method for the created user
    @Test
    public void d_deleteUser() {
        RestAssured.baseURI = "https://reqres.in";

        Response response = given()
                .header("x-api-key", "reqres-free-v1")
                .header("Content-Type", "application/json")
                .when()
                .delete("/api/users/" + userID)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("DELETE Response: successfully deleted the User ID " + userID);
        Assert.assertEquals(204, statusCode); // 204 is expected for a successful deletion
    }

}
