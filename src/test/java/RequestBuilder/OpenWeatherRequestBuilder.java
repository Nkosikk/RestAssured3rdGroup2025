package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.*;
import static PayloadBuilder.OpenWeatherPayloadBuilder.*;

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
    public static Response createOpenWeatherWithoutNameResponse(){

        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(createWeatherStationWithoutNameBody())
                .post()
                .then()
                .extract().response();
    }

    public static Response getOpenWeatherResponse(){

        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath +"/"+ weatherStationId)
                .queryParam("appid",openWeatherApiKey)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response updateOpenWeatherResponse(){
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath+"/"+weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
                .body(updateWeatherStationBody())
                .put()
                .then()
                .extract().response();

    }

    public static Response deleteOpenWeatherResponse(){

        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath +"/"+ weatherStationId)
                .queryParam("appid",openWeatherApiKey)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }
}
