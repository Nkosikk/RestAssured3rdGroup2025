package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteStation {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://api.openweathermap.org";
        String stationId = "68765f51cbd4230001cc045a";
        Response response = RestAssured.given()
            .queryParam("appid", "a8363d70b69d0049f9bf23ac8a6af96f")
        .when()
            .delete("/data/3.0/stations/" + stationId)
        .then()
            .extract().response();

        int statusCode = response.getStatusCode();
        if (statusCode == 204) {
            System.out.println("Status: PASS - Station deleted successfully");
        } else {
            System.out.println("Status: FAIL - API returned status code: " + statusCode);
            System.out.println("Response: " + response.asString());
        }
    }
}
