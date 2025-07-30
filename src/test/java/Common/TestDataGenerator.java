package Common;

import com.github.javafaker.Faker;

public class TestDataGenerator {

    public static String longitude = Faker.instance().address().longitude();
    public static String latidude = Faker.instance().address().latitude();
    public static int altidude = Faker.instance().number().numberBetween(10,20);

}
