package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.Assert;
import org.testng.annotations.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReqresAPIPositiveCase {

static String UserID;

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
    UserID = response.jsonPath().getString("id");
    System.out.println("User ID created: " + UserID);
    }

    @Test
    public void b_getUser() {
        String baseUrl = "https://reqres.in";
        String pathUrl = "/api/users/" + UserID; // Assuming UserID is set from the previous test
        String apiKey = "reqres-free-v1";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(pathUrl)
                .header("x-api-key", apiKey)
                .log().all()
                .get()
                .then()
                .statusCode(200);
    }

}

