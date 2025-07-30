package Stations;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class WeatherAPI {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String PATH = "/data/3.0/stations";
    private static final String APPID = "a8363d70b69d0049f9bf23ac8a6af96f";

    @Test
    public void createStationTest() {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .queryParam("appid", APPID)
                .contentType(ContentType.JSON)
                .body("{\"external_id\": \"Gauteng\", \"name\": \"POP trust\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 123}")
                .post();
        response.then().statusCode(201);
        System.out.println(response.asString());
    }
}
