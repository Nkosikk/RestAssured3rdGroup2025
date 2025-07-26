package Test;

import RequestBuilder.OpenWeatherRequestBuilder;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OpenWeatherTest {

    @Test
    public void a_createWeatherStationTest() {
        OpenWeatherRequestBuilder.CreateopenweatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void b_getWeatherStationTest() {
        OpenWeatherRequestBuilder.GetopenweatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void c_updateWeatherStationTest() {
        OpenWeatherRequestBuilder.UpdateopenweatherResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

}
