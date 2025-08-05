package PayloadBuilder;

import Common.TestDataGenerator;
import Utils.DatabaseConnection;
import org.json.simple.JSONObject;


public class OpenWeatherPayloadBuilder {
    DatabaseConnection db = new DatabaseConnection();
//    static double latitude = TestDataGenerator.latidude;
    public static JSONObject createWeatherStationBody(){

        DatabaseConnection db = new DatabaseConnection();
        System.out.print("The user connected id:"+ db.externalid +" and the password is: "+ db.St_name);
        JSONObject station = new JSONObject();
        station.put("external_id", db.externalid);
        station.put("name", db.St_name);
        station.put("latitude",TestDataGenerator.latitude);
        station.put("longitude", TestDataGenerator.longitude);
        station.put("altitude",TestDataGenerator.altidude);

        return station;
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
