package Tests;

import RequestBuilder.OpenWeatherRequestBuilder;
import Utils.DatabaseConnection;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class OpenWeatherTest {

    @BeforeClass
    public void setup() {
        // Load database values before running tests
        DatabaseConnection.testConnection();
    }
    @Test
    public void createWeatherStationTest(){
        /**This test is creating a new weather station */
        OpenWeatherRequestBuilder.createOpenWeatherResponse(DatabaseConnection.retrievedEmail, DatabaseConnection.retrievedPassword)
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "createWeatherStationTest")
    public void getCreatedWeatherStationTest(){

        OpenWeatherRequestBuilder.getOpenWeatherResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "getCreatedWeatherStationTest")
    public void updateWeatherStationTest(){
        OpenWeatherRequestBuilder.updateOpenWeatherResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }

    @Test(dependsOnMethods = "updateWeatherStationTest")
    public void deleteWeatherStationTest(){
        OpenWeatherRequestBuilder.deleteOpenWeatherResponse()
                .then()
                .log().all()
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
