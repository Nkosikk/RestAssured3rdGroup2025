package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.*;
import static PayloadBuilder.OpenWeatherPayloadBuilder.createWeatherStationBody;

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

    public static Response getWeatherStationId() {

        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .get()
                .then()
                .extract().response();
        return response;
    }

    public static Response UpdateWeatherStation() {

        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(createWeatherStationBody())
                .put()
                .then()
                .extract().response();
        return response;
    }

    public static Response deleteWeatherStation() {

        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath+ "/" + weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .delete()
                .then()
                .extract().response();
        return response;
    }
}
