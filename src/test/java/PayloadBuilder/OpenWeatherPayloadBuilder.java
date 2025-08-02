package PayloadBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static Common.Authorisations.openWeatherApiKey;
import static Common.BasePaths.openWeatherBaseUrl;
import static Common.BasePaths.openWeatherPath;
import static PayloadBuilder.OpenWeatherPayloadBuilder.createWeatherStationWithoutNameBody;
import static PayloadBuilder.OpenWeatherPayloadBuilder.updateWeatherStationBody;

public class OpenWeatherPayloadBuilder {

    public static JSONObject createWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","First station");
        station.put("latitude",37.76);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
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
