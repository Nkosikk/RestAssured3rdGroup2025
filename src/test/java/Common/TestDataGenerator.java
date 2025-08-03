package Common;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataGenerator {

    private static final Faker faker = new Faker(new Locale("en-US"));

    public static double longitude = Double.parseDouble(faker.address().longitude().replace(',', '.'));
    public static double latitude = Double.parseDouble(faker.address().latitude().replace(',', '.'));
    public static int altitude = faker.number().numberBetween(10, 20);
}
