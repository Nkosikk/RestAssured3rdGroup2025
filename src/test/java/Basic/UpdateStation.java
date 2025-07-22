package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateStation {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://api.openweathermap.org";
        String stationId = "68765f51cbd4230001cc045a";
        String payload = "{\"external_id\": \"Gauteng\", \"name\": \"Brakpan zone 2\", \"latitude\": 37.76, \"longitude\": -122.43, \"altitude\": 1206}";

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .queryParam("appid", "a8363d70b69d0049f9bf23ac8a6af96f")
            .body(payload)
        .when()
            .put("/data/3.0/stations/" + stationId)
        .then()
            .extract().response();

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Status: PASS - Station updated successfully");
            System.out.println("Response: " + response.asString());
        } else {
            System.out.println("Status: FAIL - API returned status code: " + statusCode);
            System.out.println("Response: " + response.asString());
        }
    }
}
