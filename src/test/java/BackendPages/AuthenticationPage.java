package BackendPages;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;

public class AuthenticationPage {
	
	
private static Cookies authCookieToken;
	
	public void getAuthenticationHealth() {
		

		Response response = RestAssured.get("https://automationintesting.online/auth/actuator/health");
		String responseBody = response.getBody().asString();
		String statusValue = response.jsonPath().getString("status");
			
		assertEquals(200, response.getStatusCode());
		assertTrue(responseBody.contains("{\\\"status\\\":\\\"UP\\\"}"));
		assertEquals("UP", statusValue);

		
		System.out.printf("Response body is: %s%n", responseBody);
	
	}
	
	public String loginUser() {
		
		String tokenValue = null;
		JSONObject requestBody = new JSONObject();
		
		requestBody.put("username", "admin");
		requestBody.put("password", "password");
		
		 Response response = given()
		            .contentType(ContentType.JSON)
		            .body(requestBody.toString())
		        .when()
		            .post("https://automationintesting.online/auth/login")
		        .then()
		            .log().body()
		            .assertThat()
		            .statusCode(200)
		            .extract().response();
		 
		 authCookieToken = response.getDetailedCookies();
					
	    try {
	    	
			if (authCookieToken != null) {
				   
				   String cookieValue = authCookieToken.toString();
			       String[] parts = cookieValue.split("=");
			       
			       if (parts.length > 1) {
			            tokenValue = parts[1].split(";")[0]; 
			            //System.out.println("Extracted token value: " + tokenValue);
			        } else {
			            System.out.println("Token not found in the cookie.");
			        }
			    } else {
			        System.out.println("Cookie not found or null.");
			    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred: " + e.getMessage());
		}	
	    
	    return tokenValue;
	}
	
	
	public void validateSessionToken(String tokenValue) {
		
		JSONObject requestBody = new JSONObject();

		requestBody.put("token", tokenValue.toString());
		
		RestAssured
					.given()
						.contentType(ContentType.JSON)
						.body(requestBody.toString())
						.post("https://automationintesting.online/auth/validate")
					.then()
						.assertThat()
						.statusCode(200);
	 
	 }
}
