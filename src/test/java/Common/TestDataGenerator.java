package Common;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static JSONObject generateStationPayload() {
        JSONObject station = new JSONObject();
        station.put("name", faker.name().firstName() + " Weather Station");
        station.put("latitude", Double.parseDouble(faker.address().latitude()));
        station.put("longitude", Double.parseDouble(faker.address().longitude()));
        station.put("altitude", faker.number().numberBetween(10, 500));
        station.put("external_id", faker.internet().uuid());
        return station;
    }



}

