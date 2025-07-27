package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReqResAPI {

    static String userId;


    @Test
    public void a_createUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users";
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("x-api-key", apiKey)
                .body(payload)
                .post()
                .then()
                .log().all()
                .extract().response();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());

        // Extract and save the userId for later tests
        userId = response.jsonPath().getString("id");
        Assert.assertNotNull("User ID should not be null", userId);
        Assert.assertEquals(201, response.getStatusCode());
    }




    @Test
    public void b_updateUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/" + userId;
        String apiKey = "reqres-free-v1";
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .contentType(ContentType.JSON)
                .body(payload)
                .log().all()
                .put();

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
    }

    @Test
    public void c_updateUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2" + userId;
        String apiKey = "reqres-free-v1";
        String payload = "{ \"name\": \"Sfisile Mabuza\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .contentType(ContentType.JSON)
                .body(payload)
                .log().all()
                .patch();

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
    }

    @Test
    public void d_deleteUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2" + userId;
        String apiKey = "reqres-free-v1";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .log().all()
                .delete()
                .then()
                .statusCode(204);
    }}