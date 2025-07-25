package PayloadBuilder;

import com.github.javafaker.Faker;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.junit.Test;

public class OpenWeatherPayloadBuilder {

    public static JSONObject CreateWeatherStationBody() {
        Faker faker = new Faker();
        JSONObject station = new JSONObject();
        // Generate fake data
// Generate fake data
        // Generate fake data
        String externalId = faker.idNumber().valid();
        String name = faker.weather().description();  // imaginative station name
        //String latitude = faker.address().latitude(); // string format
        //String longitude = faker.address().longitude(); // string format
       // String altitude = String.valueOf(faker.number().numberBetween(100, 4000)); // string format

        // Create JSON payload
        station.put("external_id", externalId);
        station.put("name", name);
        //station.put("latitude", latitude);
       // station.put("longitude", longitude);
        //station.put("altitude", altitude);
        return station;

    }

}
