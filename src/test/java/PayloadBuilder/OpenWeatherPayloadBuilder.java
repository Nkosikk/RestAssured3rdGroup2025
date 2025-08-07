package PayloadBuilder;

import org.json.simple.JSONObject;


public class OpenWeatherPayloadBuilder {

    /**
     * This method creates a JSON payload for creating a weather station.
     * @return JSONObject representing the weather station details.
     */
    public static JSONObject  createWeatherStationBody(){
        JSONObject station = new JSONObject();

        station.put("name", "San Francisco Test Station");
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 150);
        station.put("external_id", "SF_TEST001124");

        return station;
    }

    /**
     * This method creates a JSON payload for updating a weather station.
     * @return JSONObject representing the updated weather station details.
     */
    public static JSONObject createUpdatedWeatherStationBody() {
        JSONObject station = new JSONObject();
        station.put("name", "Updated weather station");
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 150);
        station.put("external_id", "SF_TEST001124");

        return station;
    }


}
