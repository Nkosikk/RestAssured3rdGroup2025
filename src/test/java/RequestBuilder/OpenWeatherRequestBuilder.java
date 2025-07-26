package RequestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static Common.Authorisation.openWeatherApiKey;
import static Common.BasePaths.openWeatherBaseUrl;
import static Common.BasePaths.openWeatherPath;

public class OpenWeatherRequestBuilder {

    static String weatherStationId;
    public static Response createOpenWeatherResponse(){

        Response response = RestAssured.given()
                .baseUri(openWeatherBaseUrl)
                .basePath(openWeatherPath)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeatherApiKey)
                .log().all()
//              .body(PayloadBuilder.OpenWeatherPayloadBuilder.createWeatherStationBody())
                .body(createOpenWeatherResponse())
                .post()
                .then()
                .extract().response();

        weatherStationId = response.jsonPath().getString("ID");
        return response;


    }
}
