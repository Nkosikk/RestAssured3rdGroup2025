package Common;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

public class TestDataGenerator {
    public  static String externalId = Faker.instance().idNumber().valid();
    public static String stationName = Faker.instance().name().firstName() + " Weather Station";
    public static String longitude = Faker.instance().address().longitude();
    public static String latitude = Faker.instance().address().latitude();
    public static int altitude = Faker.instance().number().numberBetween(10,20);

}






