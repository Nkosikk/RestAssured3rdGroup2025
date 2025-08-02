package Stations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.openWeatherBaseUrl;
import static Common.BasePaths.openWeatherPath;
import static io.restassured.RestAssured.given;
import org.junit.Test;

public class WeatherAPI {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String PATH = "/data/3.0/stations";
    private static final String APPID = "a8363d70b69d0049f9bf23ac8a6af96f";
    private static final String PATH_ID = "/data/3.0/stations/weatherStationId";

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
    @Test
    public void getStationTest() {
         RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(PATH_ID)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .get()
                .then()
                .statusCode(200)
                .extract().response();

    }
    @Test

    public void updateStationTest() {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(PATH_ID)
                .queryParam("appid", APPID)
                .contentType(ContentType.JSON)
                .body("{\"external_id\": \"Gauteng\", \"name\": \"POP trust\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 123}")
                .put();
        response.then().statusCode(201);
        System.out.println(response.asString());

    }
    @Test
    public void deleteStationTest() {
        RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(PATH_ID)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .delete()
                .then()
                .statusCode(200)
                .extract().response();

    }
    @Test
    public void negativeStationTest() {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .queryParam("appid", APPID)
                .contentType(ContentType.JSON)
                .body("{\"external_id\": \"Gauteng\", \"name\": \"POP trust\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 123}")
                .post();
        response.then().statusCode(400);
        System.out.println(response.asString());
    }
}
