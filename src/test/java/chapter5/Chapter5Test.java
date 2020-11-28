package chapter5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Chapter5Test {

/*	
	<?xml version="1.0" encoding="UTF-8" ?>
	<response>
	  <postcode>90210</postcode>
	  <country>United States</country>
	  <countryAbbreviation>US</countryAbbreviation>
	  <places>
	    <place longitude="-118.4065" latitude="34.0901">
	      <placeName>Beverly Hills</placeName>
	      <state>California</state>
	      <stateAbbreviation>CA</stateAbbreviation>
	    </place>
	  </places>
	</response>
	*/
	
	@Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
        when().
            get("http://oj7je.mocklab.io/places").
        then().
            assertThat().
            body("response.places.place.placeName", equalTo("Beverly Hills"));
}
	
	/*
	<?xml version="1.0" encoding="UTF-8" ?>
	<response>
	  <postcode>24848</postcode>
	  <country>Germany</country>
	  <countryAbbreviation>DE</countryAbbreviation>
	  <places>
	    <place longitude="9.4333" latitude="54.3833">
	      <placeName>Alt Bennebek</placeName>
	      <state>Schleswig-Holstein</state>
	      <stateAbbreviation>SH</stateAbbreviation>
	    </place>
	    <place longitude="9.4833" latitude="54.45">
	      <placeName>Klein Rheide</placeName>
	      <state>Schleswig-Holstein</state>
	      <stateAbbreviation>SH</stateAbbreviation>
	    </place>
	    <place longitude="9.5087" latitude="54.4111">
	      <placeName>Kropp</placeName>
	      <state>Schleswig-Holstein</state>
	      <stateAbbreviation>SH</stateAbbreviation>
	    </place>
	    <place longitude="9.45" latitude="54.4">
	      <placeName>Klein Bennebek</placeName>
	      <state>Schleswig-Holstein</state>
	      <stateAbbreviation>SH</stateAbbreviation>
	    </place>
	  </places>
	</response>
	*/
	
	@Test
    public void requestDeZipCode24848_checkThirdPlaceNameInResponseBody_expectKropp() {

        given().
        when().
            get("http://rm1ml.mocklab.io/templated").
        then().
            assertThat().
            body("response.places.place[2].placeName", equalTo("Kropp"));
    }
	
	@Test
    public void requestDeZipCode24848_checkLastPlaceNameInResponseBody_expectKleinBennebek() {

        given().
        when().
            get("http://rm1ml.mocklab.io/templated").
        then().
            assertThat().
            body("response.places.place[-1].placeName", equalTo("Klein Bennebek"));
    }
	
	@Test
    public void requestDeZipCode24848_checkLatitudeForSecondPlaceInResponseBody_expect5445() {

        given().
        when().
            get("http://rm1ml.mocklab.io/templated").
        then().
            assertThat().
            body("response.places.place[1].@latitude", equalTo("54.45"));
    }
	
	 @Test
	    public void requestDeZipCode24848_checkNumberOfPlacesWithStateAbbreviationSH_expect4() {

	        given().
	        when().
	            get("http://rm1ml.mocklab.io/templated").
	        then().
	            assertThat().
	            body("response.places.place.findAll{it.stateAbbreviation!='SH'}", empty());
	    }
	
}
