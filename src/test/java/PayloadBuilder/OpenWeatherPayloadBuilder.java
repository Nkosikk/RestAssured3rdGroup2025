package PayloadBuilder;

import Common.TestDataGenerator;
import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {

    public static JSONObject createWeatherStationBody(String externalId){

        JSONObject station = new JSONObject();
        station.put("external_id", externalId);
        station.put("name","First station");
        station.put("latitude",37.76);
        station.put("longitude",TestDataGenerator.longitude);
        station.put("altitude",230);

        return station;
    }

    public static JSONObject updateWeatherStationBody(String externalId, String name){

        JSONObject station = new JSONObject();
        station.put("external_id", externalId);
        station.put("name", name);
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 230);

        return station;
    }

    public static JSONObject deleteWeatherStationBody(String externalId){

        JSONObject station = new JSONObject();
        station.put("external_id", externalId);
        station.put("name", "Deleted Station");
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 230);

        return station;
    }
}
