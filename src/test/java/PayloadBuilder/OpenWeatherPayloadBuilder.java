package PayloadBuilder;

import Common.TestDataGenerator;
import Utils.DatabaseConnection;
import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {


    public static JSONObject createWeatherStationBody(String name, String externalId) {

            DatabaseConnection db = new DatabaseConnection();

            JSONObject station = new JSONObject();
            station.put("external_id", db.getExternalId());
            station.put("name", db.getName());
            station.put("latitude", TestDataGenerator.latitude);
            station.put("longitude", TestDataGenerator.longitude);
            station.put("altitude", TestDataGenerator.altitude);
            return station;

    }

    public static JSONObject updateWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","Update first station");
        station.put("latitude",TestDataGenerator.latitude);
        station.put("longitude",TestDataGenerator.longitude);
        station.put("altitude",TestDataGenerator.altitude);

        return station;
    }

    public static JSONObject createWeatherStationWithoutNameBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","");
        station.put("latitude",TestDataGenerator.latitude);
        station.put("longitude",TestDataGenerator.longitude);
        station.put("altitude",TestDataGenerator.altitude);

        return station;
    }
}
