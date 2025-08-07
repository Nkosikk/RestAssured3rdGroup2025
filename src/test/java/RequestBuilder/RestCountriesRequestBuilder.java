package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static Common.BasePaths.restcountriesBasePath;
import static Common.BasePaths.restcountriesPath;

public class RestCountriesRequestBuilder {
    public static Response getAllCountriesResponse() {

        return RestAssured.given()
                .baseUri(restcountriesBasePath)
                .basePath(restcountriesPath)
                .log().all()
                .get()
                .then()
                .extract().response();
    }
}
