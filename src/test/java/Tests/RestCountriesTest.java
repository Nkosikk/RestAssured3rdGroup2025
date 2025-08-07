package Tests;


import java.util.List;

import org.testng.annotations.Test;
import RequestBuilder.RestCountriesRequestBuilder;



public class RestCountriesTest {

    @Test
    public void displayAllCountries() {
        List<?> countries = RestCountriesRequestBuilder.getAllCountriesResponse()
                .then()
                .extract()
                .jsonPath()
                .getList("$");

        countries.forEach(System.out::println);
    }

    @Test(dependsOnMethods = "displayAllCountries")
    public void verifyAllCountriesCount() {
         RequestBuilder.RestCountriesRequestBuilder.getAndVerifyAllCountries();
    }
}


