package Tests;

import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

import static RequestBuilder.OpenWeatherRequestBuilder.weatherStationId;

public class OpenWeatherTest {

    @Test
    public void createWeatherStationTest(){
        /**This test is creating a new weather station */
        OpenWeatherRequestBuilder.createOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "createWeatherStationTest")
    public void getCreatedWeatherStationTest() {
        /** This test gets the weather station created in the previous test */

        OpenWeatherRequestBuilder.getWeatherStationById(weatherStationId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "getCreatedWeatherStationTest")
    public void updateWeatherStationTest() {
        /** This test updates the weather station created earlier */

        OpenWeatherRequestBuilder.updateWeatherStationById(weatherStationId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "updateWeatherStationTest")
    public void deleteWeatherStationTest() {
        /** This test deletes the weather station created earlier */

        OpenWeatherRequestBuilder.deleteWeatherStationById(weatherStationId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(204);  // OpenWeather returns 204 No Content on successful delete
    }
}
