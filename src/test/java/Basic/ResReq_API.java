package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // orders tests alphabetically

public class ResReq_API {

    static String UserId;

    @Test
    public void a_createAuser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users";
        String apiKey = "reqres-free-v1";
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                //.queryParam("x-api-key", apiKey)
                .log().all()
                .body(payload)
                //.log().all()
                .post()
                .then()
                .log().all()
                .extract().response();


        int actualStatusCode =response.getStatusCode();
        Assert.assertEquals(actualStatusCode,201);

        UserId = response.jsonPath().getString("ID");
    }

    @Test
    public void b_getAcreatedUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users" + UserId;
        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey)
                .log().all()
                .get()
                //.statusCode(200)
                .then()
                .log().all()
                .extract().response();

        int actualStatusCode =response.getStatusCode();
        Assert.assertEquals(actualStatusCode,200);
    }

    @Test
    public void c_updateAUser(){
        String baseUrl = "https://reqres.in";
        String path = "/api/users/" + UserId;
        String apiKey = "reqres-free-v1";

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .log().all()
                .body(payload)
                .put()
                .then()
                .log().all()
                .extract().response();

        int actualStatusCode =response.getStatusCode();
        Assert.assertEquals(actualStatusCode,200);
    }

    @Test
    public void d_deleteAUser(){
        String baseUrl = "https://reqres.in";
        String path = "/api/users" + UserId;
        String apiKey = "reqres-free-v1";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .header("x-api-key", apiKey)
                .log().all()
                .delete();

        int actualStatusCode =response.getStatusCode();
        Assert.assertEquals(actualStatusCode,204);
    }
}
