package Common;

import com.github.javafaker.Faker;

public class TestDataGenerator {


        public static double longitude = Double.parseDouble(Faker.instance().address().longitude().replace(",", "."));
        public static double latitude = Double.parseDouble(Faker.instance().address().latitude().replace(",", "."));
        public static int altitude = Faker.instance().number().numberBetween(10, 20);

    }


