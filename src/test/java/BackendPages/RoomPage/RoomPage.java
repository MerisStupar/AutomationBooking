package BackendPages.RoomPage;


import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import BackendPages.AuthenticationPage;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class RoomPage {
	
	
	public Map<String, Object> getHealtRoomEP() {
		Response response = RestAssured.get("https://automationintesting.online/room/actuator/health");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();

		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("statusCode", statusCode);
		responseMap.put("responseBody", responseBody);
		
		return responseMap;
	}
	
	
	public Response createNewRoom() {
		
		AuthenticationPage auth = new AuthenticationPage();
		String requestBody = "{ \"roomName\": \"100\", \"type\": \"Double\", \"accessible\": true, \"image\": \"https://images.unsplash.com/photo-1586023492125-27b2c045efd7?q=80&w=2158&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\", \"description\": \"Test Room - Selenium ROOM\", \"features\": [ \"Wifi\", \"Safe\", \"Radio\" ], \"roomPrice\": 50 }";
		String cookieValue = auth.loginUser();
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.cookie("token", cookieValue)
				.body(requestBody)
				.when()
				.post("https://automationintesting.online/room/")
				.thenReturn();  // Use thenReturn to get the response without assertions
		
  	
		System.out.println("test:: "+ response.getBody().asString());
		
		return response; // Return the whole response object
		
	}
	
	
	public Response updateRoom(String roomId) {
		
		AuthenticationPage auth = new AuthenticationPage();
		String requestBody = "{ \"roomName\": \"100\", \"type\": \"Double\", \"accessible\": true, \"image\": \"https://images.unsplash.com/photo-1586023492125-27b2c045efd7?q=80&w=2158&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\", \"description\": \"Test Room - Selenium ROOM\", \"features\": [ \"Wifi\", \"Safe\", \"Radio\", \"TV\", \"Views\", \"Refreshments\" ], \"roomPrice\": 50 }";
		String cookieValue = auth.loginUser();
		
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.cookie("token", cookieValue)
				.body(requestBody)
				.when()
				.put("https://automationintesting.online/room/" + roomId)
				.thenReturn();
		
		return response;
	}
	
	
	public void assertStatusCodeEquals(int expectedStatusCode, int actualStatusCode) {
		assertEquals(actualStatusCode, expectedStatusCode, "Status code is not as expected");
	}
	
	
}
