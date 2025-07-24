package Stations;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.junit.Test;
import org.junit.Assert;

public class WeatherAPI {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String PATH = "/data/3.0/stations";
    private static final String APPID = "a8363d70b69d0049f9bf23ac8a6af96f";

    @Test
    public void getAllStationsTest() {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .queryParam("appid", APPID)
                .get();
        response.then().statusCode(200);
        System.out.println(response.asString());
    }

    @Test
    public void deleteStationTest() {
        String stationId = "68825ae7cbd4230001cc0717"; // Replace with the actual station ID you want to delete
        Response deleteResponse = given()
                .baseUri(BASE_URL)
                .basePath(PATH + "/" + stationId)
                .queryParam("appid", APPID)
                .delete();
        deleteResponse.then().statusCode(204);
        System.out.println(deleteResponse.asString());
    }

    @Test
    public void createStationTest() {
        Response response = given()
                .baseUri(BASE_URL)
                .basePath(PATH)
                .queryParam("appid", APPID)
                .contentType(ContentType.JSON)
                .body("{\"external_id\": \"Gauteng\", \"name\": \"POP trust\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 123}")
                .post();
        int statusCode = response.getStatusCode();
        System.out.println("Create Station Response: " + response.asString());
        System.out.println("Create Station Status: " + (statusCode == 201 ? "PASS" : "FAIL"));
        Assert.assertEquals("Create station should return 201", 201, statusCode);
    }

    @Test
    public void updateStationTest() {
        String stationId = "687661a7cbd4230001cc0465"; // Replace with the actual station ID you want to update
        String newName = "SnakePark"; // Replace with the new station name
        String oldName = "POP trust"; // The previous name
        Response updateResponse = given()
                .baseUri(BASE_URL)
                .basePath(PATH + "/" + stationId)
                .queryParam("appid", APPID)
                .contentType(ContentType.JSON)
                .body("{\"external_id\": \"Gauteng\", \"name\": \"" + newName + "\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 123}")
                .put();
        int statusCode = updateResponse.getStatusCode();
        System.out.println("Update Station Response: " + updateResponse.asString());
        System.out.println("Update Station Status: " + (statusCode == 200 ? "PASS" : "FAIL"));
        System.out.println("Station name updated from '" + oldName + "' to '" + newName + "'.");
        Assert.assertEquals("Update station should return 200", 200, statusCode);
    }
}
