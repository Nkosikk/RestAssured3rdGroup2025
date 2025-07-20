package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Reqress {


    @Test
    public void createUser() {
        String baseURL = "https://reqres.in";
        String path = " /api/users";
        String apiKey = "reqres-free-v1";
        String payload = "{\n" +
                "  \"name\": \"Brenda\",\n" +
                "  \"Surname\": \"Malinga\",\n" +
                "  \"job\": \"leader\"\n" +
                "}";

        Response response = given()
                .baseUri(baseURL)
                .basePath(path)
                .contentType(ContentType.JSON)
                .queryParam("x-api-key", apiKey)
                .log().all()
                .body(payload)
                .post();

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 201);
        System.out.println(response.asString());

    }
}