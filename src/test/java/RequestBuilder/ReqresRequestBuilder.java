package RequestBuilder;

import PayloadBuilder.OpenWeatherPayloadBuilder;
import PayloadBuilder.ReqresPayloadBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Common.Aurhorization.openWeather_apiKey;
import static Common.Aurhorization.reqres_apiKey;
import static Common.BasePaths.*;

public class ReqresRequestBuilder {

    static String ReqId;

    public static Response CreateReqresResponse() {
        Response response = RestAssured.given()
                .baseUri(reqres_baseUrl)
                .basePath(reqres_path)
                .contentType(ContentType.JSON)
                .queryParam("x-api-key", reqres_apiKey)
                .log().all()
                .body(ReqresPayloadBuilder.CreateReqresBody().toString())
                .post();

        ReqId = response.jsonPath().getString("ID");

        return response;
    }


}
