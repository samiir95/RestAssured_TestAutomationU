package chapter6;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.junit.Assert;
import org.testng.annotations.Test;

import dataentities.Location;
import io.restassured.http.ContentType;

public class Chapter6Test {


	//de-serialization
	    @Test
	    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

	        Location location =

	        given().
	        when().
	            get("http://zippopotam.us/us/90210").
	        as(Location.class);

	        Assert.assertEquals(
	            "Beverly Hills",
	            location.getPlaces().get(0).getPlaceName()
	        );
	    }

	    //serialization
	    @Test
	    public void sendLvZipCode1050_checkStatusCode_expect200() {

	        Location location = new Location();
	        location.setCountry("Netherlands");

	        given().
	            contentType(ContentType.JSON).
	            body(location).
	            log().body().
	        when().
	            post("https://webhook.site/4f2ce6f2-ed5e-42d6-8634-5e0b0eff8373").
	        then().
	            assertThat().
	            statusCode(200);
	    }
	    
	  
}
