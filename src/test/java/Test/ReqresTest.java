package Test;

import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

  @Test
  public  void b_updateReqresTest() {
      Response response = RequestBuilder.ReqresRequestBuilder.updateReqresBody();
      response.then()
              .log()
              .all()
              .assertThat()
              .statusCode(200);

  }

}
