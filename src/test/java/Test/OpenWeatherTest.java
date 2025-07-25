package Test;

import RequestBuilder.OpenWeatherRequestBuilder;
import org.junit.Test;

public class OpenWeatherTest {

    @Test
    public void  createWeatherStationTest() {
        // This method will contain the test logic for creating a weather station
        // It will use the OpenWeatherPayloadBuilder to create the payload
        // and then send a POST request to the OpenWeather API
        // The response will be validated to ensure the station was created successfully

        OpenWeatherRequestBuilder.CreateopenweatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);



    }
}
