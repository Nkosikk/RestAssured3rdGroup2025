package PayloadBuilder;

import Common.TestDataGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OpenWeatherPayloadBuilder {


    public static JSONObject createWeatherStationBody() {
        JSONObject payload = new JSONObject();
        payload.put("external_id", "525585 Test");
        payload.put("name", "Lubabalo Test Station");
        payload.put("latitude", 40.23);
        payload.put("longitude", -74.0060);
        payload.put("altitude", 10);
        return payload;
    }


    public static JSONObject updateWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","Update first station");
        station.put("latitude",40.23);
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

    public static JSONArray createWeatherMeasurementBody(String stationId) {

        JSONObject measurement = new JSONObject();
        measurement.put("station_id", stationId);
        measurement.put("dt", 1479817340);
        measurement.put("temperature", 18.7);
        measurement.put("wind_speed", 1.2);
        measurement.put("wind_gust", 3.4);
        measurement.put("pressure", 1021);
        measurement.put("humidity", 87);
        measurement.put("rain_1h", 2);

        JSONArray cloudsArray = new JSONArray();
        JSONObject cloud = new JSONObject();
        cloud.put("condition", "NSC");
        cloudsArray.add(cloud);

        measurement.put("clouds", cloudsArray);

        JSONArray measurements = new JSONArray();
        measurements.add(measurement);

        return measurements;
    }

}
