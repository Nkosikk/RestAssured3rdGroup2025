package Basic;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class ListCountries  {

    String countriesBaseUrl = "https://restcountries.com";
    String countriesPath = "/v3.1/all?fields=name";

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




}
