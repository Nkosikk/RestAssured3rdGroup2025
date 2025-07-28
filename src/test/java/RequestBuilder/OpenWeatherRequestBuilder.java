package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.*;
import static PayloadBuilder.OpenWeatherPayloadBuilder.createWeatherStationBody;
import static PayloadBuilder.OpenWeatherPayloadBuilder.updateWeatherStationBody;

public class OpenWeatherRequestBuilder {

    static String weatherStationId;
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
    public static Response getWeatherStationById() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    // PUT / update weather station
    public static Response updateWeatherStationById() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(updateWeatherStationBody()) // method to return updated JSON string
                .put()
                .then()
                .extract().response();
    }

    // DELETE weather station
    public static Response deleteWeatherStationById() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }
}
