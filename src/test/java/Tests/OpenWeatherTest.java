package Tests;

import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

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

    @Test(dependsOnMethods = "createWeatherStationTest")
    public void getWeatherStationTest() {
        /**This test is getting a weather station */
        OpenWeatherRequestBuilder.getOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("name", org.hamcrest.Matchers.equalTo("San Francisco Test Station"))
                .body("latitude", org.hamcrest.Matchers.equalTo(37.76f));
    }

    @Test(dependsOnMethods = "getWeatherStationTest")
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

    @Test(dependsOnMethods = "updateWeatherStationTest")
    public void deleteWeatherStationTest() {
        /**This test is deleting a weather station */
        OpenWeatherRequestBuilder.getOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");

        OpenWeatherRequestBuilder.deleteOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(204);
    }

}