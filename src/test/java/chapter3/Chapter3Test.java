package chapter3;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Chapter3Test {

	
	 @DataProvider
	    public static Object[][] zipCodesAndPlaces() {
	        return new Object[][] {
	            { "us", "90210", "Beverly Hills" },
	            { "us", "12345", "Schenectady" },
	            { "ca", "B2R", "Waverley"},
	            {"nl", "1001", "Amsterdam"}
	        };
}
	 
	 
	 @Test(dataProvider = "zipCodesAndPlaces")
	 public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) 
	    {
	        given().
	            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
	        when().
	            get("http://zippopotam.us/{countryCode}/{zipCode}").
	        then().
	            assertThat().
	            body("places[0].'place name'", equalTo(expectedPlaceName));
	    }
}