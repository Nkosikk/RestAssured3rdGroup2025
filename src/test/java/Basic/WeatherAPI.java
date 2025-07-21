package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.PriorityQueue;


@FixMethodOrder(MethodSorters.NAME_ASCENDING) // orders tests alphabetically
public class WeatherAPI {

    static String weatherStationId;

    @Test
    public void a_createWeatherStation() {
        String baseUrl = "http://api.openweathermap.org";
        String path = "/data/3.0/stations";
        String apiKey = "b1589ee5727295072e2272d60dfc6904";
        String payload = "{\n" +
                "  \"external_id\": \"SF_TEST001\",\n" +
                "  \"name\": \"San Francisco Test Station\",\n" +
                "  \"latitude\": 37.76,\n" +
                "  \"longitude\": -122.43,\n" +
                "  \"altitude\": 150\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .queryParam("appid", apiKey)
                .log().all()
                .body(payload)
                .post();

        int actualStatusCode =response.getStatusCode();
        Assert.assertEquals(actualStatusCode,201);

        weatherStationId = response.jsonPath().getString("ID");
    }

    @Test
    public void b_getWeatherStation() {
        String baseUrl = "http://api.openweathermap.org";
        String path = "/data/3.0/stations/" + weatherStationId;
        String apiKey = "b1589ee5727295072e2272d60dfc6904";

         RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .queryParam("appid", apiKey)
                .log().all()
                .get()
                .then()
                .statusCode(200);


    }

    @Test
    public void c_updateWeatherStation(){
        String baseUrl = "http://api.openweathermap.org";
        String path = "/data/3.0/stations/" + weatherStationId;
        String apiKey = "b1589ee5727295072e2272d60dfc6904";

        String payload = "{\n" +
                "  \"external_id\": \"SF_TEST001\",\n" +
                "  \"name\": \"Updated weather station\",\n" +
                "  \"latitude\": 37.76,\n" +
                "  \"longitude\": -122.43,\n" +
                "  \"altitude\": 150\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .contentType(ContentType.JSON)
                .queryParam("appid", apiKey)
                .log().all()
                .body(payload)
                .put();

        int actualStatusCode =response.getStatusCode();
        Assert.assertEquals(actualStatusCode,200);
    }
    @Test
    public void d_DeleteWeatherStation() {
        String baseUrl = "http://api.openweathermap.org";
        String path = "/data/3.0/stations/" + weatherStationId;
        String apiKey = "b1589ee5727295072e2272d60dfc6904";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .queryParam("appid", apiKey)
                .log().all()
                .delete()
                .then()
                .statusCode(204);


    }
}
