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
    public void getCreatedWeatherStationTest(){
        OpenWeatherRequestBuilder.getWeatherResponse()
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
//                .log()
//                .all()




    }

}
