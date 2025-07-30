package RequestBuilder;

import PayloadBuilder.OpenWeatherPayloadBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Common.Aurhorization.openWeather_apiKey;
import static Common.BasePaths.OpenWeather_path;
import static Common.BasePaths.openWeather_baseUrl;
import static PayloadBuilder.OpenWeatherPayloadBuilder.*;

public class OpenWeatherRequestBuilder {
    // Get the payload from PayloadBuilder class
    static String weatherStationId;
    public static Response createOpenWeatherResponse(){


        Response response = RestAssured.given()
                .baseUri(openWeather_baseUrl )
                .basePath(OpenWeather_path)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeather_apiKey)
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
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeather_apiKey)
                .log().all()
                .body(createWeatherStationWithoutNameBody())
                .post()
                .then()
                .extract().response();
    }

    public static Response getOpenWeatherResponse(){

        return RestAssured.given()
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path +"/"+ weatherStationId)
                .queryParam("appid",openWeather_apiKey)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response updateOpenWeatherResponse(){
        return RestAssured.given()
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path+"/"+weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeather_apiKey)
                .log().all()
                .body(updateWeatherStationBody())
                .put()
                .then()
                .extract().response();

    }

    public static Response deleteOpenWeatherResponse(){

        return RestAssured.given()
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path +"/"+ weatherStationId)
                .queryParam("appid",openWeather_apiKey)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }

}
