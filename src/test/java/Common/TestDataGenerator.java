package Common;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    public static String longitude = Double.parseDouble(Faker.instance().address().longitude());

    public static String latitude = Double.parseDouble(Faker.instance().address().latitude());

    public static int altitude = Faker.instance().number().numberBetween(10,20);

}
