package PayloadBuilder;

import Common.TestDataGenerator;
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

    static double latitude = TestDataGenerator.latidude;
    public static JSONObject createWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","First station");
        station.put("latitude",latitude);
        station.put("longitude", TestDataGenerator.longitude);
        station.put("altitude",TestDataGenerator.altidude);

        return station;
    }

    public static JSONObject updateWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","Update first station");
        station.put("latitude",latitude);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
    }

    public static JSONObject createWeatherStationWithoutNameBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","");
        station.put("latitude",37.76);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
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
