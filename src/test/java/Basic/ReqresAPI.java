package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.equalTo;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // orders tests alphabetically
public class ReqresAPI {
    static String userId;

    @Test
    public void a_createUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users";
        String apiKey = "reqres-free-v1";
        String payload = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"job\": \"Developer\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey) // Replace with actual API key if needed
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .post();

        int actualStatusCode = response.getStatusCode();
        System.out.println("Create User Status Code: " + actualStatusCode);
        userId = response.jsonPath().getString("id");
        System.out.println("the Id " + userId);
    }

    @Test
    public void b_getUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2";
        String apiKey = "reqres-free-v1";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey) // Replace with actual API key if needed
                .log().all()
                .get()
                .then()
                .statusCode(200);


    }
    @Test
    public void c_putUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2";
        String apiKey = "reqres-free-v1";
        String payload = "{\n" +
                "  \"name\": \"Amy\",\n" +
                "  \"job\": \"Developer\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey) // Replace with actual API key if needed
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .put();
    }
    @Test
    public void da_patchUser() {
        String baseUrl = "https://reqres.in";
        String path = "/api/users/2";
        String apiKey = "reqres-free-v1";
        String payload = "{\n" +
                "  \"name\": \"Amy2\",\n" +
                "  \"job\": \"Developer\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey) // Replace with actual API key if needed
                .contentType(ContentType.JSON)
                .log().all()
                .body(payload)
                .patch();
    }

     @Test
    public void deleteUser() {
        String baseUrl = "https://reqres.in/api";
         String path = "/api/users/2";
         String apiKey = "reqres-free-v1";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .header("x-api-key", apiKey) // Replace with actual API key if needed
                .delete()
                .then()
                .statusCode(204);
    }

}
