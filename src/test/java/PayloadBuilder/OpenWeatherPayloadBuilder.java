package PayloadBuilder;

import Common.TestDataGenerator;
import Utils.Station;
import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {

//    static double latitude = TestDataGenerator.latidude;



    public static JSONObject createWeatherStationBody(Station station) {
        JSONObject payload = new JSONObject();
        payload.put("external_id", station.externalId);
        payload.put("name", station.name);
        payload.put("latitude", station.latitude);
        payload.put("longitude", station.longitude);
        payload.put("altitude", station.altitude);
        return payload;
    }


    public static JSONObject updateWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","Update first station");
        station.put("latitude",TestDataGenerator.latitude);
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
}
