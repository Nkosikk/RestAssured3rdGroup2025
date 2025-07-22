package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WeatherAPI {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://api.openweathermap.org";
        String payload = "{\"external_id\": \"Gauteng\", \"name\": \"Molepo\", \"latitude\": 35.56, \"longitude\": -122.43, \"altitude\": 150}";

        Response response = given()
            .header("Content-Type", "application/json")
            .queryParam("appid", "a8363d70b69d0049f9bf23ac8a6af96f")
            .body(payload)
        .when()
            .post("/data/3.0/stations")
        .then()
            .statusCode(anyOf(is(201), is(200))) // Accepts 201 or 200
            .extract().response();

        int statusCode = response.getStatusCode();
        if (statusCode == 201 || statusCode == 200) {
            System.out.println("Status: PASS - Station created successfully");
        } else {
            System.out.println("Status: FAIL - API returned status code: " + statusCode);
            System.out.println("Response: " + response.asString());
        }
    }
}
