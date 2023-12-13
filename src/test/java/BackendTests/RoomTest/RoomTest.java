package BackendTests.RoomTest;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BackendPages.RoomPage.RoomPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class RoomTest {

	RoomPage room = new RoomPage();
	
	@BeforeTest
	public void setUp() {
		System.out.println("=======TEST STARTED=======");
	}
	
	@Test
	public void isRoomComponentUp() {
		
		//test22
		Map<String, Object> responseMap = room.getHealtRoomEP();		
		
		int statusCode = (int) responseMap.get("statusCode");
		String valueStatus = (String) responseMap.get("responseBody");
		
		assertEquals(statusCode, 200, "Expected status code is 200 OK");
		System.out.printf("Response body is: %s%n", valueStatus);
	}
	
	@Test
	public void createNewRoom() {
		
		 // Call the method to create a new room and get the response
        Response response = room.createNewRoom();

        // Get the response body and status code
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        int statusCode = response.getStatusCode();
        List<String> features = jsonPath.getList("features");
        String[] expectedFeatures = {"Wifi", "Safe", "Radio"};
        

        //Perform assertions on the status code and response body keys/values
        Assert.assertEquals(statusCode, 201, "Status code mismatch");
        Assert.assertEquals(features.size(), expectedFeatures.length, "Number of features is mismatch");
        Assert.assertEquals(jsonPath.getString("roomName"), "100", "Room Name mismatch");
        Assert.assertEquals(jsonPath.getString("type"), "Double" , "Type isn't the same as request body");
        Assert.assertEquals(jsonPath.getString("accessible"), "true" , "Accessible is not same!");
	}
	
	@Test
	public void updateRoom() {
		
		 Response response = room.createNewRoom();
		 String responseBody = response.getBody().asString();
		 JsonPath jsonPath = new JsonPath(responseBody);
		 
		 String roomId = jsonPath.getString("roomid");
		 
		 System.out.println("proba ROOM ID: " + roomId);
		 
		 Response updateResponse = room.updateRoom(roomId);
		 int statusCode = updateResponse.getStatusCode();
		 
		 
		 Assert.assertEquals(statusCode, 202, "Update did not return a success status");

	}
	
}
