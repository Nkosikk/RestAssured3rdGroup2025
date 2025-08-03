package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

@org.testng.annotations.Test
public class Verifycountries {
//verify that the API returns 195 countries
    @org.testng.annotations.Test
    public void verifyNumberOfCountriesIs195() {
        try {
            RestAssured.baseURI = "https://restcountries.com/";
            Response response = RestAssured
                    .given()
                    .when()
                    .get("v3.1/all?fields=name,flags")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            int countryCount = response.jsonPath().getList("").size();

            System.out.println("Test Report:");
            System.out.println(" - Expected count: 195");
            System.out.println(" - Actual count: " + countryCount);

            assertEquals(countryCount, 195, "Number of countries should be 195");
            System.out.println("✅ Test Passed: API returns 195 countries");
        } catch (AssertionError e) {
            System.out.println("❌ Test Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }

// validate schema of countries API response
    @org.testng.annotations.Test
    public void verifyCountriesSchema() {
        try {
            RestAssured.baseURI = "https://restcountries.com/";
            Response response = RestAssured
                    .given()
                    .when()
                    .get("v3.1/all?fields=name,flags")
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("country-schema.json"))
                    .extract()
                    .response();

            System.out.println("✅ Schema validation passed for countries API response.");
        } catch (AssertionError e) {
            System.out.println("❌ Schema validation failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        }
    }







}















