package Tests;

import RequestBuilder.CountriesRequestBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CountriesTest {

    @Test
    public void validateNameSchema() {

        CountriesRequestBuilder.getAllCountries()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("Schema/Schema.json"));

    }

    @Test
    public void verifycountryNumber() {

        var response = CountriesRequestBuilder.getAllCountries();
        int countryCount = response.jsonPath().getList("$").size();
        System.out.println("Number of countries: " + countryCount);

        response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.equalTo(195));

    }
    @Test
    public void verifycountrylanguage() {
        Response response = RestAssured
                .given()
                .baseUri("https://restcountries.com")
                .basePath("/v3.1/name/south Africa")
                .when()
                .get();

        response.then()
                .statusCode(200)
                .contentType("application/json");

        Map<String, String> languages = response.jsonPath().getMap("[0].languages");
        System.out.println("Languages: " + languages);

        Collection<String> languageValues = languages.values();
        boolean hasSASL = languageValues.contains("SASL") || languageValues.contains("South African Sign Language");

        Assert.assertTrue("SASL or South African Sign Language should be present", hasSASL);
    }


}