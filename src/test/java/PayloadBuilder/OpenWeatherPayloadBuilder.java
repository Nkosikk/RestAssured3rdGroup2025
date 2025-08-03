package PayloadBuilder;

import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {

    public static JSONObject createWeatherStationBody(String externalId){

        JSONObject station = new JSONObject();
        station.put("external_id", externalId);
        station.put("name","First station");
        station.put("latitude",37.76);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
    }
}
