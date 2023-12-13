package BackendTests;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BackendPages.AuthenticationPage;

public class AuthenticationTest {

	AuthenticationPage auth = new AuthenticationPage();
	
	
	@BeforeTest
	public void setUp() {
		System.out.println("=======TEST STARTED=======");
	}
	
	@Test
	public void loginUserTest() {
		
		String token;
		token = auth.loginUser();	
		assertNotNull(token, "Token should not be null after login.");
	}
	
	@Test
	public void validateSessionTokenTest() {
	
		String sessionTokenValue;
		sessionTokenValue = auth.loginUser();
		auth.validateSessionToken(sessionTokenValue);
	}
	
	
}
