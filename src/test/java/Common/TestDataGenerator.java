package Common;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    public static double longitude = Double.parseDouble(Faker.instance().address().longitude());
    public static double latidude = Double.parseDouble(Faker.instance().address().latitude());
    public static int altidude = Faker.instance().number().numberBetween(10,20);

}
