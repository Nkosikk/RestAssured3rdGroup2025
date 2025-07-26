package RequestBuilder;

import PayloadBuilder.OpenWeatherPayloadBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Common.Aurhorization.openWeather_apiKey;
import static Common.BasePaths.OpenWeather_path;
import static Common.BasePaths.openWeather_baseUrl;

public class OpenWeatherRequestBuilder {
    // Get the payload from PayloadBuilder class
    static JSONObject station = OpenWeatherPayloadBuilder.CreateWeatherStationBody();
    static String weatherStationId;

    public static Response CreateopenweatherResponse(){
        Response response = RestAssured.given()
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeather_apiKey)
                .log().all()
                .body(OpenWeatherPayloadBuilder.CreateWeatherStationBody().toString())
                .post();

        weatherStationId = response.jsonPath().getString("ID");
// Print response
       // System.out.println("Response Body:\n"..prettyPrint());
        //System.out.println("Response is:"+response.prettyPrint());

        return response;
    }

    public static Response UpdateopenweatherResponse() {
        Response response = RestAssured.given()
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path + "/" + weatherStationId)
                .contentType(ContentType.JSON)
                .queryParam("appid", openWeather_apiKey)
                .log().all()
                .body(OpenWeatherPayloadBuilder.updateWeatherStationBody().toString())
                .put();

        return response;
    }

    public  static Response GetopenweatherResponse() {
        Response response = RestAssured.given()
                .baseUri(openWeather_baseUrl)
                .basePath(OpenWeather_path + "/" + weatherStationId)
                .queryParam("appid", openWeather_apiKey)
                .log().all()
                .get();

        return response;
    }

}
