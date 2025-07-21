package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ReqresAPI {

    @Test
    public void createUser() {
        RestAssured.baseURI = "https://reqres.in";

        String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(payload)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201);
    }
}
