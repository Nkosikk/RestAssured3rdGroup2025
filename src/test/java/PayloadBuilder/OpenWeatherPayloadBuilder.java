package PayloadBuilder;

import org.json.simple.JSONObject;

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
    public static JSONObject updateWeatherStationBody() {
        JSONObject station = new JSONObject();
        station.put("external_id", "ext station id");
        station.put("name", "Updated station");
        station.put("latitude", 38.00);
        station.put("altitude", 235);

        return station;
    }
}
