package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

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


    public static Response getCreatedWeatherStationResponse() {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();
    }


    public static Response updateWeatherStationResponse(JSONObject updatedBody) {
        return RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath + "/" + weatherStationId)
                .queryParam("appid", openWeatherApiKey)
                .contentType(ContentType.JSON)
                .log().all()
                .body(updatedBody)
                .put()
                .then()
                .extract().response();
    }

}
