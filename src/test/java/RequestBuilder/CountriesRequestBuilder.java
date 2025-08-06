package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static Common.CountriesBasePath.*;


public class CountriesRequestBuilder {
    public static Response getAllCountries(){

        return RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(countriesPath)
                .queryParam("fields", "name")
                .log().all()
                .get()
                .then()
                .extract().response();
    }
    public static Response validateLanguage(){

        return RestAssured.given()
                .baseUri(countriesBaseUrl)
                .basePath(currencyPath)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

}
