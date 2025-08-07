package Tests;

import Common.TestDataGenerator;
import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

public class OpenWeatherTest {


    @Test
    public void createWeatherStationTest() {
        /**This test is creating a new weather station */
        TestDataGenerator.generateStationPayload();
        OpenWeatherRequestBuilder.createOpenWeatherResponse()
                .then()
                .log()
                .all()
                .body("ID", org.hamcrest.Matchers.notNullValue())
                .body("ID", org.hamcrest.Matchers.not(0))
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
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "getWeatherStationTest")
    public void updateWeatherStationTest() {
        /**This test is updating a weather station */
        TestDataGenerator.generateStationPayload();
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