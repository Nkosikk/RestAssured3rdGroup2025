package Tests;

import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

public class OpenWeatherTest {

    @Test
    public void createWeatherStationTest(){
        /**This test is creating a new weather station */
        OpenWeatherRequestBuilder.createOpenWeatherResponse("station_ext_id_1")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "createWeatherStationTest()")
    public void getCreatedWeatherStationTest(){
        /**This test is getting the weather station created in the previous test */
        OpenWeatherRequestBuilder.createOpenWeatherResponse("station_ext_id_1")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "getCreatedWeatherStationTest()")
    public void updateWeatherStationTest(){
        /**This test is updating the weather station created in the previous test */
        OpenWeatherRequestBuilder.updateOpenWeatherResponse("Updated Station Name")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "updateWeatherStationTest()")
    public void deleteWeatherStationTest(){
        /**This test is deleting the weather station created in the previous tests */
        OpenWeatherRequestBuilder.deleteOpenWeatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(204);
    }
}
