package Tests;


import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import RequestBuilder.RestCountriesRequestBuilder;


public class RestCountriesTest {

    @Test
    public void displayAllCountries() {
        List<?> countries = RestCountriesRequestBuilder.getAllCountriesResponse()
                .then()
                .extract()
                .jsonPath()
                .getList("$");

        countries.forEach(System.out::println);
    }

    @Test(dependsOnMethods = "displayAllCountries")
    public void verifyAllCountriesCount() {
        RequestBuilder.RestCountriesRequestBuilder.getAndVerifyAllCountries();

    }

    @Test(dependsOnMethods = "displayAllCountries")
    public void printZuluLanguageForSouthAfrica() {

        RestAssured.given()
                .baseUri("https://restcountries.com")
                .basePath("/v3.1/all")
                .queryParam("fields", "name,languages=='eng'")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .contentType("application/json")
                .body("find {it.name.common == 'South Africa'}.languages.sals", org.hamcrest.Matchers.equalTo("South African sign language");
    }

}
