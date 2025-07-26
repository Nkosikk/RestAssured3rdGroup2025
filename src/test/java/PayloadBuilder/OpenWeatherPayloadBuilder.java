package PayloadBuilder;

import org.json.simple.JSONObject;
import org.junit.Test;


public class OpenWeatherPayloadBuilder {

    /**
     * This method creates a JSON payload for creating a weather station.
     * @return JSONObject representing the weather station details.
     */
    public static JSONObject  createWeatherStationBody(){
        JSONObject station = new JSONObject();
        station.put("external_id", "SF_TEST001124");
        station.put("name", "San Francisco Test Station");
        station.put("latitude", 37.76);
        station.put("longitude", -122.43);
        station.put("altitude", 150);

        return station;
    }


}
