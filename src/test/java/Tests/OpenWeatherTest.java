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


    @Test(dependsOnMethods = "createWeatherStationTest")
    public void getCreatedWeatherStationTest() {
        /** This test retrieves the weather station created in the previous test */
        OpenWeatherRequestBuilder.getCreatedWeatherStationResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }


    @Test(dependsOnMethods = "getCreatedWeatherStationTest")
    public void updateWeatherStationTest() {
        /** This test updates the weather station created earlier */
        OpenWeatherRequestBuilder.updateWeatherStationResponse(
                        PayloadBuilder.OpenWeatherPayloadBuilder.updateWeatherStationBody()
                )
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

}
