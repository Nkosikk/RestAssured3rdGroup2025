package Test;

import org.junit.Test;

public class ReqresTest {
    @Test

    public void a_createReqresTest() {
        RequestBuilder.ReqresRequestBuilder.CreateReqresResponse()
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    }
}
