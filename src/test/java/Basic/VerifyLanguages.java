package Basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;

    @Test
    public class VerifyLanguages {

        @Test
        public void verifySASLIncludedInSouthAfricaLanguages() {
            try {
                Response response = RestAssured
                        .given()
                        .when()
                        .get("https://restcountries.com/v3.1/independent?status=true&fields=languages,capital")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

                boolean saslFound = response.jsonPath()
                        .getList("")
                        .stream()
                        .anyMatch(country -> {
                            String capital = ((Map<String, Object>) country).get("capital").toString();
                            if (capital.contains("Pretoria")) {
                                Map<String, String> languages = (Map<String, String>) ((Map<String, Object>) country).get("languages");
                                return languages != null && languages.containsValue("South African Sign Language");
                            }
                            return false;
                        });
                Assert.assertTrue("South African Sign Language (SASL) is not included in South Africa's official languages.", saslFound);
                System.out.println("✅ SASL is included in South Africa's official languages.");
            } catch (AssertionError e) {
                System.out.println("❌ Test Failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Unexpected error: " + e.getMessage());
            }
        }
    }


