package Tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static Common.BasePaths.countriesBaseUrl;
import static Common.BasePaths.countriesPath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CountriesTest {

    @Test
    public void listCountriesTest() {
        /**This test is listing all countries */
// It uses the RestCountries API to fetch country names
        RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name")
                .contentType("application/json")
                .header("Accept", "application/json")
                .get()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void confirmingNumberOfCountriesTest() {
        /**This test is confirming the number of countries */
        RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name")
                .contentType("application/json")
                .header("Accept", "application/json")
                .get()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.equalTo(250) /* Adjust the number based on the actual count of countries */);
    }
    @Test
    public void verifyLanguageForSouthAfricaTest() {
        /**This test is verifying the language for South Africa */
        RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name,languages")
                .contentType("application/json")
                .header("Accept", "application/json")
                .get()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("find { it.name.common == 'South Africa' }.languages.sals", org.hamcrest.Matchers.equalTo("South African Sign Language"));
    }
    @Test
    public void validateCountrySchemaTest() {
        /**This test is validating the country schema */
        RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name")
                .contentType("application/json")
                .header("Accept", "application/json")
                .get()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/countries-schema.json"));
    }


}
