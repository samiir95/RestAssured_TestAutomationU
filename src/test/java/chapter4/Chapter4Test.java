package chapter4;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class Chapter4Test {

	private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification() {
    
        requestSpec = new RequestSpecBuilder().
            setBaseUri("http://api.zippopotam.us").
            build();
        
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }
    
    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withRequestSpec() {

        given().
            spec(requestSpec).
        when().
            get("us/90210").
        then().
            assertThat().
            statusCode(200);
    }
    
    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills_withResponseSpec() {

        given().
            spec(requestSpec).log().all().
        when().
            get("us/90210").
        then().
            spec(responseSpec).
        and().
            assertThat().
            body("places[0].'place name'", equalTo("Beverly Hills"))
            .log().all();
    }
    
    @Test
    public void requestUsZipCode90210_extractPlaceNameFromResponseBody_assertEqualToBeverlyHills() {
    
        String placeName =
        given().
            spec(requestSpec).
        when().
            get("us/90210").
        then().
            extract().
            path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");
    }
    
}
