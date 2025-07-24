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
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2";
        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .queryParam("page", 2)
                .log().all()
                .get();

        Assert.assertEquals(200, response.getStatusCode());
        userId = response.jsonPath().getString("data[0].id");
        System.out.println("First user ID: " + userId);
    }

    @Test
    public void b_createUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users";
        String apiKey = "reqres-free-v1";

        String payload = "{ \"name\": \"Morpheus\", \"job\": \"Tech Leader\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .body(payload)
                .log().all()
                .post()
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(201, response.getStatusCode());
        // Optionally update userId if needed:
        // userId = response.jsonPath().getString("id");
    }

    @Test
    public void c_updatingUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/" + userId;
        String apiKey = "reqres-free-v1";

        String payload = "{ \"name\": \"Morpheus\", \"job\": \"Delivery Manager\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .body(payload)
                .log().all()
                .put()
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void d_deleting() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/" + userId;
        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .log().all()
                .delete();

        Assert.assertEquals(204, response.getStatusCode());
    }
}
