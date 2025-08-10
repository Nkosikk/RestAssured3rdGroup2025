package PayloadBuilder;

import Common.TestDataGenerator;
import Utils.DatabaseConnection;
import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {

    static double latitude = TestDataGenerator.latidude;

    public static JSONObject createWeatherStationBody() {
        String[] credentials = DatabaseConnection.testConnection();


        JSONObject station = new JSONObject();
        station.put("external_id", credentials != null ? credentials[0] : "default_external_id");
        station.put("name", credentials != null ? credentials[1] : "default_name");
        station.put("latitude", latitude);
        station.put("longitude", TestDataGenerator.longitude);
        station.put("altitude", TestDataGenerator.altidude);

        return station;
    }

    public static JSONObject updateWeatherStationBody() {

        JSONObject station = new JSONObject();
        station.put("external_id", "ST001" );
        station.put("name", "Updated Station Name");
        station.put("latitude", latitude);
        station.put("longitude", -122.43);
        station.put("altitude", 230);

        return station;
    }
}

