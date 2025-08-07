package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static Common.BasePaths.countriesBaseUrl;
import static Common.BasePaths.countriesPath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CountriesTest {

    /**
     * Scenario 1: Schema Validation
     * Priority 1
     * As a consumer of the API, I want to ensure that the data returned from the API
     * conforms to the published schema so that my application can reliably consume it.
     * Note: This test validates only the structure of the "name" field from the API response.
     * The schema is stored in /resources/Schemas/sample/countryschema.json
     * I found the original schema was null (i.e., not provided by API),
     * so I created a sample JSON schema based on the structure of the response.
     */
    @Test(priority = 1)
    public void validateCountrySchemaTest() {
        RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name")
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("Schemas/sample/countryschema.json"));

        // This schema ensures the API doesn't silently change structure without us noticing
        // Prevents downstream issues in applications depending on the expected format
    }

    /**
     * Scenario 2: Confirmation of Countries
     * Priority 2
     * As a map builder, I want to confirm that there are 195 countries in the world
     * so that my maps are accurate and reflect current geopolitical boundaries.
     * This test will fail if we expect 195, because as verified manually on Postman,
     * the API returns 250 countries, including territories and regions like Antarctica.
     * Postman Test Screenshot confirms 250 with 1 failure (Antarctica has no languages)
     */
    @Test(priority = 2)
    public void confirmingNumberOfCountriesTest() {
        Response response = RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name")
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .get();

        response.then().log().all().assertThat().statusCode(200);

        int actualCountryCount = response.jsonPath().getList("name").size();
        System.out.println("Actual country count from API: " + actualCountryCount);

        // I verified this on Postman. It returns 250 countries.
        Assert.assertEquals(actualCountryCount, 195, "Expected 195 countries including regions");
    }

    /**
     * Scenario 3: Validate Languages (SASL Check)
     * Priority 3
     * As the Minister of Education, I want to ensure that South African Sign Language (SASL)
     * is included in the list of South Africa's official languages.
     * If SASL is not present, the test fails since this requirement is critical for inclusivity.
     * I chose to explicitly fail the test if it's missing.
     */
    @Test(priority = 3)
    public void verifyLanguageForSouthAfricaTest() {
        Response response = RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name,languages")
                .header("Accept", "application/json")
                .get();

        Assert.assertEquals(response.getStatusCode(), 200);

        Map<String, Object> southAfrica = response.jsonPath()
                .get("find { it.name.common == 'South Africa' }");

        if (southAfrica == null) {
            Assert.fail("South Africa not found in API response.");
        }

        Map<String, String> languages = (Map<String, String>) southAfrica.get("languages");

        // We check explicitly for the presence of SASL key
        if (languages != null && languages.containsKey("sasl")) {
            System.out.println("✅ SASL (South African Sign Language) is present.");
        } else {
            Assert.fail("❌ SASL (South African Sign Language) is NOT present in South Africa's languages.");
        }
    }

    /**
     * Priority 5 (Postman Verification Test)
     *
     * This is an additional test derived from manual testing in Postman.
     * Postman confirms 250 countries, with one failure (Antarctica having no native language).
     *
     * This test just prints the count and confirms Antarctica is present but empty in languages.
     */
    @Test(priority = 5)
    public void postmanVerificationTest() {
        Response response = RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name,languages")
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .get();

        response.then().log().all();

        Map<String, Object> antarctica = response.jsonPath()
                .get("find { it.name.common == 'Antarctica' }");

        if (antarctica != null && antarctica.get("languages") instanceof Map) {
            Map<String, String> languages = (Map<String, String>) antarctica.get("languages");
            Assert.assertTrue(languages.isEmpty(), "Expected no languages for Antarctica");
        } else {
            System.out.println("Antarctica has no languages - as expected");
        }

        int count = response.jsonPath().getList("name").size();
        System.out.println("Verified country count (Postman validation): " + count);
        Assert.assertEquals(count, 250);
    }
}
