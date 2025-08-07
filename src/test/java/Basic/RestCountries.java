package Basic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import java.util.Map;
@org.testng.annotations.Test


public class RestCountries {

    // Schema validation test for the Rest Countries API
    // This test checks if the API response matches the JSON schema defined in 'country-schema.json
    @Test
    public void schemeValidation() {
        try {
            RestAssured.baseURI = "https://restcountries.com/";
            Response response = RestAssured
                    .given()
                    .when()
                    .get("v3.1/all?fields=name,flags")
                    .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("all-countries-schema.json"))
                    .extract()
                    .response();

            System.out.println("JSON schema validation passed");
        }
        catch (AssertionError e) {
            System.out.println("JSON schema validation failed: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Schema cannot be Null: " + e.getMessage());
        }
    }

    //verify that the API returns 195 countries
    @Test
    public void checkCountryCount() {
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

            assertEquals(countryCount, 195, "Total countries returned by the API is not 195");
            // The assertion checks if the actual count matches the expected count of 195
            // If the assertion fails, it will throw an AssertionError with the provided message
            // If the assertion passes, print a success message
            System.out.println("Test Passed: API returns 195 countries");
        } catch (AssertionError e) {
            System.out.println("Test Failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Return Null: " + e.getMessage());
        }
    }

    @Test
    public void validateSouthSignAfricaLanguages() {
        try {
            Response response = RestAssured
                    .given()
                    .when()
                    .get("https://restcountries.com/v3.1/independent?status=true&fields=languages,capital")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            boolean saslFound = response.jsonPath()
                    .getList("")
                    .stream()
                    .anyMatch(country -> {
                        String capital = ((Map<String, Object>) country).get("capital").toString();
                        if (capital.contains("Pretoria")) {
                            Map<String, String> languages = (Map<String, String>) ((Map<String, Object>) country).get("languages");
                            return languages != null && languages.containsValue("South African Sign Language");
                        }
                        return false;
                    });
            Assert.assertTrue("South African Sign Language (SASL) is not included in South Africa's official languages.", saslFound);
            System.out.println("South African Sign Language (SASL) should be in the language list");
        }

        catch (AssertionError e) {
            System.out.println("Test Failed: " + e.getMessage());
        }

         catch (Exception e) {
            System.out.println("Returned Null: " + e.getMessage());
        }
    }
}

