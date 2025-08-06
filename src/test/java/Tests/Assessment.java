package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Assessment {


    @Test
    public void validateRestCountriesResponseSchema() {
        Response response = RestAssured.given()
                .baseUri("https://restcountries.com")
                .basePath("/v3.1/all")
                .log().all()
                .get();

        try {
            response.then().assertThat()
                    .body(matchesJsonSchemaInClasspath("schema/restcountries_schema.json"));
            System.out.println("Schema validation PASSED.");
        } catch (AssertionError e) {
            System.err.println("Schema validation FAILED: " + e.getMessage());
            Assert.fail("Schema validation failed: " + e.getMessage());
        }
        public class CountryCountTest {

            @Test
            public void validateNumberOfCountries() {
                Response response = RestAssured.given()
                        .baseUri("https://restcountries.com")
                        .basePath("/v3.1/all")
                        .log().all()
                        .get();

                int countryCount = response.jsonPath().getList("$").size();

                if (countryCount == 195) {
                    System.out.println("Country count validation PASSED. Total countries: " + countryCount);
                } else {
                    System.err.println("Country count validation FAILED. Expected: 195, Actual: " + countryCount);
                    Assert.fail("Country count validation failed. Expected: 195, Actual: " + countryCount);
                }
                public class SouthAfricaLanguageTest {

                    @Test
                    public void validateSASLIsOfficialLanguage() {
                        Response response = RestAssured.given()
                                .baseUri("https://restcountries.com")
                                .basePath("/v3.1/name/south%20africa")
                                .log().all()
                                .get();

                        // Extract all language values from the response
                        var languages = response.jsonPath().getList("[0].languages.values()");

                        boolean containsSASL = languages.stream()
                                .anyMatch(lang -> lang.toString().equalsIgnoreCase("South African Sign Language"));

                        if (containsSASL) {
                            System.out.println("SASL validation PASSED. 'South African Sign Language' is an official language.");
                        } else {
                            System.err.println("SASL validation FAILED. 'South African Sign Language' is NOT listed as an official language.");
                            Assert.fail("SASL validation failed. 'South African Sign Language' is NOT listed as an official language.");
                        }
                    }
                }
            }
        }
    }