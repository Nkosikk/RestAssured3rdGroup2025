package Test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    @Test
    public void a_createReqresTest() {
        RequestBuilder.ReqresRequestBuilder.CreateReqresResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201)
                .extract().response();

    }
}
