package PayloadBuilder;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;

import static Common.TestDataGenerator.*;
public class OpenWeatherPayloadBuilder {

    public static JSONObject createWeatherStationBody(){

        JSONObject station = new JSONObject();

        station.put("external_id",externalId);
        station.put("name",stationName);
        //station.put("latitude",Double.parseDouble(Faker));
        String latitudeStr = Faker.instance().address().latitude().replace(",", ".");
        double latitude = Double.parseDouble(latitudeStr);
        String longitudeStr = Faker.instance().address().longitude().replace(",", ".");
        double longitude = Double.parseDouble(longitudeStr);
        int altitudeStr = Faker.instance().number().numberBetween(10, 20);
        double altitude = (double) altitudeStr;
//        station.put("latitude", Double.parseDouble(Faker.instance().address().latitude()));
//        station.put("longitude", Double.parseDouble(Faker.instance().address().longitude()));
        station.put("longitude",longitude);
        station.put("altitude",altitude);
        station.put("latitude",latitude);

        return station;
    }

    public static JSONObject updateWeatherStationBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","Update first station");
        station.put("latitude",37.76);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
    }

    public static JSONObject createWeatherStationWithoutNameBody(){

        JSONObject station = new JSONObject();
        station.put("external_id","ext station id");
        station.put("name","");
        station.put("latitude",37.76);
        station.put("longitude",-122.43);
        station.put("altitude",230);

        return station;
    }
}



