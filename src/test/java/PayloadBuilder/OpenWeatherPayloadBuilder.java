package PayloadBuilder;

import Common.TestDataGenerator;
import org.json.simple.JSONObject;
import Utils.DatabaseConnection;

public class OpenWeatherPayloadBuilder {

    static double latitude = TestDataGenerator.latidude;

    static String psExId = DatabaseConnection.extid;
    static String stationNameID = DatabaseConnection.wstationId;

    public static JSONObject createWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id",psExId);
        station.put("name",stationNameID);
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
}
