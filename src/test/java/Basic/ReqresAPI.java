package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqresAPI {

static String UserID;

@Test
public void createUser() {
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
    UserID = response.jsonPath().getString("id");
    System.out.println("User ID created: " + UserID);
    }
}

