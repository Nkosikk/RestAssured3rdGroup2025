package Test;

import Common.TestDataGenerator;
import RequestBuilder.OpenWeatherRequestBuilder;
import org.testng.annotations.Test;

public class OpenWeatherTest {

    @Test
    public void createWeatherStationBody(){
        /**This test is creating a new weather station */
        TestDataGenerator.generateWeatherStationData();
        OpenWeatherRequestBuilder.createOpenWeatherResponse()
                .then()
                .log()
                .all()
                .body("ID", org.hamcrest.Matchers.notNullValue())
                .body("ID",org.hamcrest.Matchers.not(0))
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");

    }

    @Test(dependsOnMethods ="createWeatherStationBody")
       public void getCreatedWeatherStationTest(){

        OpenWeatherRequestBuilder.getOpenWeatherResponse()
                .then()
//                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "getCreatedWeatherStationTest")
    public void updateWeatherStationTest(){
        OpenWeatherRequestBuilder.updateOpenWeatherResponse()
                .then()
//                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "updateWeatherStationTest")
    public void deleteWeatherStationTest(){
        OpenWeatherRequestBuilder.deleteOpenWeatherResponse()
                .then()
//                .log().all()
                .assertThat()
                .statusCode(204);
    }

    @Test(dependsOnMethods = "deleteWeatherStationTest")
    public void CreatedWeatherStationWithoutNameNegativeTest(){

        OpenWeatherRequestBuilder.createOpenWeatherWithoutNameResponse()
                .then()
//                .log().all()
                .assertThat()
                .statusCode(400)
                .contentType("application/json; charset=utf-8");
    }

}
