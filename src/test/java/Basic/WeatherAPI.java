package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WeatherAPI {
    public static void createWeatherStation() {
        String baseUrl = "http://api.openweathermap.org";
        String path = "/data/3.0/stations";
        String appid = "a8363d70b69d0049f9bf23ac8a6af96f";
        String payload = "{\"external_id\": \"Gauteng\", \"name\": \"POP trust\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 123}";

        Response response = given()
            .baseUri(baseUrl)
            .header("Content-Type", "application/json")
            .queryParam("appid", appid)
            .body(payload)
        .when()
            .post(path)
        .then()
            .statusCode(anyOf(is(201), is(200))) // Accepts 201 or 200
            .extract().response();

        System.out.println("Response: " + response.asString());
    }
}
