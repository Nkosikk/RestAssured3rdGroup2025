package Common;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    public static String longitude = Faker.instance().address().longitude();
    public static String latitude = Faker.instance().address().latitude();
    public static int altitude = Faker.instance().number().numberBetween(10,20);
    public  static String external_id = Faker.instance().idNumber().valid();
    public  static String stationName = Faker.instance().name().firstName() + " Weather Station";
}

