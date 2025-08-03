package Common;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    public static double longitude = Double.parseDouble(faker.address().longitude());
    public static double latitude = Double.parseDouble(faker.address().latitude());
    public static int altidude = faker.number().numberBetween(10, 20);
}
