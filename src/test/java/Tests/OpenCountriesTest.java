package Tests;

import RequestBuilder.OpenCountriesRequestBuilder;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class OpenCountriesTest {

    @Test
    public void getCountriesTest() {
        /**This test is getting all countries from the API */

        OpenCountriesRequestBuilder.getOpenCountries()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        String jsonResponse = OpenCountriesRequestBuilder.getOpenCountries().asString();
        System.out.println("API Response in JSON format:\n" + jsonResponse);
    }

    @Test(dependsOnMethods = "getCountriesTest")
    public void verifyNumberOfCountries() {
        OpenCountriesRequestBuilder.getOpenCountries()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        List<?> countries = OpenCountriesRequestBuilder.getOpenCountries().jsonPath().getList("countries.json");
        int countryCount = Math.toIntExact(countries.size());

        System.out.println("Number of countries returned: " + countryCount);
        Assert.assertEquals(countryCount, 195, "Expected 195 countries in the response.");
        System.out.println("Country count validation result: " + (countryCount == 195 ? "PASSED" : "FAILED"));
    }
    @Test(dependsOnMethods = "getCountriesTest")
    public void getSouthAfricanLanguages() {
        /**This test is getting south african languages from the API */

        OpenCountriesRequestBuilder.getOpensouthAficanCounty()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        String jsonResponse = OpenCountriesRequestBuilder.getOpensouthAficanCounty().asString();
        System.out.println("API Response in JSON format:\n" + jsonResponse);
    }
}
