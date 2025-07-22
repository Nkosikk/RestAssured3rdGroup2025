package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllStations {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://api.openweathermap.org";
        Response response = RestAssured.given()
            .queryParam("appid", "a8363d70b69d0049f9bf23ac8a6af96f")
        .when()
            .get("/data/3.0/stations")
        .then()
            .extract().response();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Status: PASS - Stations retrieved successfully");
            System.out.println("Response: " + response.asString());
        } else {
            System.out.println("Status: FAIL - API returned status code: " + statusCode);
            System.out.println("Response: " + response.asString());
        }
    }
}
