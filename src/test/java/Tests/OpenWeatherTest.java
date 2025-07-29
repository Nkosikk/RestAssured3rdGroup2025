package Tests;

import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

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

    @Test(dependsOnMethods = "createWeatherStationTest()")
    public void getCreatedWeatherStationTest(){
        /**This test is getting the created weather station */
        OpenWeatherRequestBuilder.getWeatherStationId()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");

    }

    @Test(dependsOnMethods = "getCreatedWeatherStationTest()")
    public void updateCreatedWeatherStationTest(){
        /**This test is updating the created weather station */
        OpenWeatherRequestBuilder.UpdateWeatherStation()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");

    }

    @Test(dependsOnMethods = "updateCreatedWeatherStationTest()")
    public void deleteWeatherStationTest(){
        /**This test is deleting the created weather station */
        OpenWeatherRequestBuilder.deleteWeatherStation()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(204);


    }
}
