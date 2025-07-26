package Tests;

import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

@Test
public class OpenWeatherTest {

    @Test
    public void createWeatherStationTest() {
        /**This test is creating a new weather station */
        OpenWeatherRequestBuilder.createOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test
    public void getWeatherStationTest() {
        /**This test is getting a weather station */
        OpenWeatherRequestBuilder.getOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
    @Test
    public void updateWeatherStationTest() {
        /**This test is updating a weather station */
        OpenWeatherRequestBuilder.updateOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
}