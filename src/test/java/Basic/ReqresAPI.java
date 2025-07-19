package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class ReqresAPI {



    @Test
    public void a_createUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/";
        String apiKey = "reqres-free-v1";

        String payload = "{ \"name\": \"Liz\", \"job\": \"leader\" }";

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

    }

    @Test
    public void b_getUser() {

        String baseUrl = "https://reqres.in";
        String path = "/api/users/2";
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







}