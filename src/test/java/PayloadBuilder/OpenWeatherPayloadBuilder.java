package PayloadBuilder;

import com.github.javafaker.Faker;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.testng.util.Strings;

import java.util.HashMap;
import java.util.Map;

import static Common.TestDataGenerator.*;

public class OpenWeatherPayloadBuilder {

    public static JSONObject createWeatherStationBody() {
        JSONObject stations = new JSONObject();
        stations.put("external_id", external_id);
        stations.put("name", stationName);
        stations.put("latitude", latitude);
        stations.put("longitude", longitude);
        stations.put("altitude", altitude);
       return new JSONObject(stations);

    }

    public static JSONObject updateWeatherStationBody() {

        JSONObject station = new JSONObject();
        station.put("external_id", "ext station id");
        station.put("name", "Update first station");
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 230);

        return station;
    }

    public static JSONObject createWeatherStationWithoutNameBody() {

        JSONObject station = new JSONObject();
        station.put("external_id", "ext station id");
        station.put("name", "");
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 230);

        return station;
    }
}

