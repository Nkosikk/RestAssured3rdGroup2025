package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class RestCountries {
    @Test
    public void testGetIndependentCountries() {
        RestAssured.baseURI = "https://restcountries.com";
        Response response = given()
                .when()
                .get("/v3.1/independent?status=true")
                .then()
                .extract().response();

        // Assert status code
        assertEquals(200, response.getStatusCode(), "Status code should be 200");

        // Print the response in JSON format
        System.out.println("Response JSON: " + response.asPrettyString());

        // Assert response is a non-empty array
        var countries = response.jsonPath().getList("$");
        assertNotNull(countries, "Countries list should not be null");
        assertFalse(countries.isEmpty(), "Countries list should not be empty");

        // Check that all countries are independent
        for (Object countryObj : countries) {
            var country = (java.util.Map<?, ?>) countryObj;
            assertTrue(country.containsKey("independent"), "Country should have an 'independent' field");
            assertEquals(Boolean.TRUE, country.get("independent"), "Country should be independent");
        }
    }

    @Test
    public void testGetNonIndependentCountries() {
        RestAssured.baseURI = "https://restcountries.com";
        Response response = given()
                .when()
                .get("/v3.1/independent?status=false")
                .then()
                .extract().response();

        // Assert status code
        assertEquals(200, response.getStatusCode(), "Status code should be 200");

        // Print the response in JSON format
        System.out.println("Response JSON: " + response.asPrettyString());

        // Assert response is a non-empty array
        var countries = response.jsonPath().getList("$");
        assertNotNull(countries, "Countries list should not be null");
        assertFalse(countries.isEmpty(), "Countries list should not be empty");

        // Check that all countries are not independent
        for (Object countryObj : countries) {
            var country = (java.util.Map<?, ?>) countryObj;
            assertTrue(country.containsKey("independent"), "Country should have an 'independent' field");
            assertEquals(Boolean.FALSE, country.get("independent"), "Country should NOT be independent");
        }
    }

    @Test
    public void testGetIndependentCountriesWithFields() {
        RestAssured.baseURI = "https://restcountries.com";
        Response response = given()
                .when()
                .get("/v3.1/independent?fields=languages,capital")
                .then()
                .extract().response();

        // Assert status code
        assertEquals(200, response.getStatusCode(), "Status code should be 200");

        // Print the response in JSON format
        System.out.println("Response JSON: " + response.asPrettyString());

        // Assert response is a non-empty array
        var countries = response.jsonPath().getList("$");
        assertNotNull(countries, "Countries list should not be null");
        assertFalse(countries.isEmpty(), "Countries list should not be empty");

        // Check that each country has only 'languages' and 'capital' fields
        for (Object countryObj : countries) {
            var country = (java.util.Map<?, ?>) countryObj;
            assertTrue(country.containsKey("languages"), "Country should have a 'languages' field");
            assertTrue(country.containsKey("capital"), "Country should have a 'capital' field");
            assertEquals(2, country.size(), "Country should only have 'languages' and 'capital' fields");
        }
    }

    @Test
    public void testGetCountryByName() {
        String countryName = "eesti"; // You can change this to test other names
        RestAssured.baseURI = "https://restcountries.com";
        Response response = given()
                .when()
                .get("/v3.1/name/" + countryName)
                .then()
                .extract().response();

        // Assert status code
        assertEquals(200, response.getStatusCode(), "Status code should be 200");

        // Print the response in JSON format
        System.out.println("Response JSON: " + response.asPrettyString());

        // Assert response is a non-empty array
        var countries = response.jsonPath().getList("$");
        assertNotNull(countries, "Countries list should not be null");
        assertFalse(countries.isEmpty(), "Countries list should not be empty");

        // Optionally, check that the returned country matches the search
        boolean found = false;
        for (Object countryObj : countries) {
            var country = (java.util.Map<?, ?>) countryObj;
            if (country.containsKey("name")) {
                var nameMap = (java.util.Map<?, ?>) country.get("name");
                if (nameMap.containsValue("Eesti") || nameMap.containsValue("Estonia")) {
                    found = true;
                    break;
                }
            }
        }
        assertTrue(found, "Returned data should contain the searched country name");
    }
}
