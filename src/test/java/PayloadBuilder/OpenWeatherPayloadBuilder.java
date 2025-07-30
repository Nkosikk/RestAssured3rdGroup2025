package PayloadBuilder;

import com.github.javafaker.Faker;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.testng.util.Strings;

public class OpenWeatherPayloadBuilder {

    public static JSONObject CreateWeatherStationBody() {
        Faker faker = new Faker();
        JSONObject station = new JSONObject();
        // Generate fake data

        String externalId = faker.idNumber().valid();
        String stationname = faker.weather().description();  // imaginative station name
        //Strings representing decimal values
        String latitudeStr = String.format("%.6f", faker.number().randomDouble(6, -90, 90));
        String longitudeStr = String.format("%.6f", faker.number().randomDouble(6, -180, 180));
        String altitudeStr = String.format("%.2f", faker.number().randomDouble(2, 0, 8848)); // up to Everest height

        // Convert to double
        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);
        double altitude = Double.parseDouble(altitudeStr);

        // Create JSON payload
        station.put("external_id", externalId);
        station.put("name", stationname);
        station.put("latitude", latitude);
       station.put("longitude", longitude);
        station.put("altitude", altitude);

        return station;
    }

    // Method to create a simplified payload for updating a weather station
    public static JSONObject updateWeatherStationBody() {
        Faker faker = new Faker();
// New random name and latitude as strings
        String updatedName = faker.name().firstName();
        // String updatedLatitudeStr = String.format("%.6f", faker.number().randomDouble(6, -90, 90));
        //double updatedLatitude = Double.parseDouble(updatedLatitudeStr);

        // Build simplified payload
        JSONObject payload = new JSONObject();
        payload.put("name", updatedName);
        //payload.put("latitude", updatedLatitude);

        return payload;

    }

}
