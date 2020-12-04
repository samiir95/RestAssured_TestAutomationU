package chapter6;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PassFileAsPayload {

	private static RequestSpecification requestSpec;

	
	//Read JSON file
	 @Test
	    public void passFileAsPayload()
	    {
	    	
	    	//Creating a file instance
	    	File jsonDataInFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\file.json");
	    	
	    	
			    given()
					.spec(requestSpec)
					.contentType(ContentType.JSON)
					.body(jsonDataInFile)
					.log().body()
				.when()
					.post()
				.then()
				 	.assertThat()
				 	.statusCode(200)
				 	.body("token", notNullValue())
				 	.body("token.length()", is(15))
				 	.body("token", matchesRegex("^[a-z0-9]+$"));
	    	
	    }
	 
	//Read XML file
		 @Test
		    public void passXMLFileAsPayload()
		    {
		    	
		    	//Creating a file instance
		    	File xmlDataInFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\file.xml");
		    	
		    	
				    given()
						.spec(requestSpec)
						.contentType(ContentType.XML)
						.body(xmlDataInFile)
						.log().body()
					.when()
						.post()
					.then()
					 	.assertThat()
					 	.statusCode(200);
		    }
		 
	 
	 @BeforeClass
	    public static void createRequestSpecification() {
	    
	        requestSpec = new RequestSpecBuilder().
	            setBaseUri("https://restful-booker.herokuapp.com/auth").
	            build();
	 }
}
