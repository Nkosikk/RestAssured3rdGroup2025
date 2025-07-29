package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.*;
import static PayloadBuilder.OpenWeatherPayloadBuilder.createWeatherStationBody;
import static PayloadBuilder.OpenWeatherPayloadBuilder.updateWeatherStationBody;

public class OpenWeatherRequestBuilder {

    public static String weatherStationId;
    public static Response createOpenWeatherResponse(){


        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(createWeatherStationBody())
                .post()
                .then()
                .extract().response();

        weatherStationId = response.jsonPath().getString("ID");

        return response;

    }

    // GET weather station
    public static Response getWeatherStationById(String weatherStationId) {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + OpenWeatherRequestBuilder.weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    // PUT / update weather station
    public static Response updateWeatherStationById(String weatherStationId) {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + OpenWeatherRequestBuilder.weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(updateWeatherStationBody()) // method to return updated JSON string
                .put()
                .then()
                .extract().response();
    }

    // DELETE weather station
    public static Response deleteWeatherStationById(String weatherStationId) {
        OpenWeatherRequestBuilder.weatherStationId = weatherStationId;
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + OpenWeatherRequestBuilder.weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }
}
