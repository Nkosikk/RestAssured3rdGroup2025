package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



import static io.restassured.RestAssured.given;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // orders tests alphabetically

public class ReqresAPI {

    static String userId;

    //method to create user info
    @Test
    public void test1CreateUser() {
        RestAssured.baseURI = "https://reqres.in";

        String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        Response responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .header("x-api-key", "reqres-free-v1")
                        .body(payload)
                        .log().all()
                        .when()
                        .post("/api/users")
                        .then()
                        .statusCode(201)
                        .extract().response();
        userId = responseBody.jsonPath().getString( "id");
    }
    //method to list user info
    @Test
    public void test2ListUserInfo() {
        RestAssured.baseURI = "https://reqres.in";

        given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().all()
                .when()
                .get("/api/users"+userId)
                .then()
                .statusCode(200);
    }
    //method to update user info
    @Test
    public void test3UpdateUserInfo() {
        RestAssured.baseURI = "https://reqres.in";

        String payload = "{ \"name\": \"morpheus\", \"surname\": \"moon\", \"job\": \"leader\" }";

        Response responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .header("x-api-key", "reqres-free-v1")
                        .body(payload)
                        .log().all()
                        .when()
                        .put("/api/users"+"/" + userId)
                        .then()
                        .statusCode(200)
                        .extract().response();
        userId = responseBody.jsonPath().getString( "id");
    }
    //method to delete user info
   @Test
    public void test4DeleteUserInfo() {
        RestAssured.baseURI = "https://reqres.in";

        given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().all()
                .when()
                .delete("/api/users"+userId)
                .then()
                .statusCode(200);
    }
}
