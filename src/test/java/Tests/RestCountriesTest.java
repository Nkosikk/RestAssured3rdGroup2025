package Tests;

import org.testng.annotations.Test;
import RequestBuilder.RestCountriesRequestBuilder;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class RestCountriesTest {
    @Test
    public void getAllCountriesTest() {
        /** This test retrieves all countries from the Rest Countries API */
            List<?> countries = RestCountriesRequestBuilder.getAllCountriesResponse()
            .then()
            .extract()
            .jsonPath()
            .getList("$"); // "$" refers to the root array

            assertEquals(countries.size(), 195, "Expected number of countries");
            System.out.println("Number of countries: " + countries.size());
    }
}
