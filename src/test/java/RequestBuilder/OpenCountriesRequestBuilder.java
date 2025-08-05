package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static Common.BasePaths.*;

public class OpenCountriesRequestBuilder {
    public static Response getOpenCountries() {

        return RestAssured.given()
                .baseUri(openCountriesBaseUrl)
                .basePath(openCountriesPath)
                .queryParam("fields", "name")
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response getOpensouthAficanCounty() {

        return RestAssured.given()
                .baseUri(openCountriesBaseUrl)
                .basePath(openSouthAfricaPath)
                .queryParam("name", "south%20africa")
                .log().all()
                .get()
                .then()
                .extract().response();
    }
}
