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
                .header("x-api-key", reqres_apiKey)
                .body(ReqresPayloadBuilder.CreateReqresBody().toString())
                .log().all()
                .post();

        ReqId = response.jsonPath().getString("ID");

        return response;
    }

    public static Response GetReqresResponse() {

        Response response = RestAssured.given()
                .baseUri(reqres_baseUrl)
                .basePath(reqres_path + "/" + ReqId)
                .header("x-api-key", reqres_apiKey)
                .log().all()
                .get();

        return response;
    }


    public static Response updateReqresBody() {

        Response response = RestAssured.given()
                .baseUri(reqres_baseUrl)
                .basePath(reqres_path + "/" + ReqId)
                .header("x-api-key", reqres_apiKey)
                .log().all()
                .body(ReqresPayloadBuilder.updateReqresBody().toString())
                .put();

        return response;


    }

}
