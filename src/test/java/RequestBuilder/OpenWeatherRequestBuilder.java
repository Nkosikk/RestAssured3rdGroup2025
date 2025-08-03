package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.*;
import static PayloadBuilder.OpenWeatherPayloadBuilder.createWeatherStationBody;

public class OpenWeatherRequestBuilder {

    static String weatherStationId;
    public static Response createOpenWeatherResponse(String externalId){


        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(createWeatherStationBody(externalId))
                .post()
                .then()
                .extract().response();

        weatherStationId = response.jsonPath().getString("ID");

        return response;

    }
    public static Response getOpenWeatherResponse(){

        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .get()
                .then()
                .extract().response();

    }
}
