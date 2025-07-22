package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // orders tests alphabetically

public class ResreqAPI {

    private static String createdUserId;

    @Test
    public void a_createUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/";
        String apiKey = "reqres-free-v1";

        String payload = "{ \"name\": \"Refilwe\", \"job\": \"Tester\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .contentType("application/json")
                .body(payload)
                .log().all()
                .post();

        response.then().log().all();
        Assert.assertEquals(201, response.getStatusCode());

        createdUserId = response.jsonPath().getString("id");
        Assert.assertNotNull(createdUserId);

    }

    @Test
    public void b_getUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/" + createdUserId;
        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .log().all()
                .get();

        response.then().log().all();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void c_updateUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/" + createdUserId;
        String apiKey = "reqres-free-v1";
        String payload = "{ \"name\": \"Refilwe\", \"job\": \"Senior Tester\" }";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .contentType("application/json")
                .body(payload)
                .log().all()
                .put();

        response.then().log().all();
        Assert.assertEquals(200, response.getStatusCode());
    }

}



