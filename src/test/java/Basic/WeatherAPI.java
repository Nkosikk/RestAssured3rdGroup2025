package Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

public class WeatherAPI {
    @Test
    public void createWeatherStation() {
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
                .body(payload)
                .post();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Test
    public void getWeatherStation() {
        String baseUrl = "http://api.openweathermap.org";
        String path = "/data/3.0/stations/6879279bcbd4230001cc0564";
        String apiKey = "b1589ee5727295072e2272d60dfc6904";

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath(path)
                .queryParam("appid", apiKey)
                .get();

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
