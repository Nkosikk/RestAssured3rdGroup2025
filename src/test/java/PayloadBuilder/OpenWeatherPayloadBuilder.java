package PayloadBuilder;

import Common.TestDataGenerator;
import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {

    static double longitude = TestDataGenerator.longitude;
    static double latitude = TestDataGenerator.latitude;
    static double altitude = TestDataGenerator.altitude;


    public static JSONObject createWeatherStationBody(String externalId){

        JSONObject station = new JSONObject();
        station.put("external_id", externalId);
        station.put("name","First station");
        station.put("latitude", latitude);
        station.put("longitude", longitude);
        station.put("altitude", altitude);

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
