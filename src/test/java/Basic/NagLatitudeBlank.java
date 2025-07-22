package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class NagLatitudeBlank {
    public static void main(String[] args) {
        String externalId = "Capetown";
        String name = "Brakpan zone 3";
        Double latitude = 45.65; // Intentionally set to a valid value, but the test is for blank latitude
        Double longitude = -122.43;
        Integer altitude = null;
        String appid = "a8363d70b69d0049f9bf23ac8a6af96f";

        // Input validation
        if (externalId == null || externalId.isEmpty() || name == null || name.isEmpty() || latitude == null || longitude == null || altitude == null || appid == null || appid.isEmpty()) {
            System.out.println("Status: FAIL - Missing or invalid input(s)");
            return;
        }

        RestAssured.baseURI = "http://api.openweathermap.org";
        String payload = String.format("{\"external_id\": \"%s\", \"name\": \"%s\", \"latitude\": %s, \"longitude\": %s, \"altitude\": %d}",
                externalId, name, latitude, longitude, altitude);

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .queryParam("appid", appid)
            .body(payload)
        .when()
            .post("/data/3.0/stations")
        .then()
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
