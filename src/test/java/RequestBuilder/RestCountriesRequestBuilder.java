package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static Common.BasePaths.restcountriesBasePath;
import static Common.BasePaths.restcountriesPath;
import static org.testng.Assert.assertEquals;

public class RestCountriesRequestBuilder {

    public static Response getAllCountriesResponse() {
        return RestAssured.given()
                .baseUri(restcountriesBasePath)
                .basePath(restcountriesPath)
                .queryParam("fields", "name")
                .contentType("application/json")
                .header("Accept", "application/json")
                .get()
                .then()
                .log().all()
                .extract().response();
    }
    public static List<?> getAndVerifyAllCountries() {
        List<?> countries = getAllCountriesResponse()
                .then()
                .extract()
                .jsonPath()
                .getList("$");

        assertEquals(countries.size(), 195, "Expected 195 countries");
        return countries;
    }

}
