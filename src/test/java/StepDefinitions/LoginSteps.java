package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginSteps {
	
	WebDriver driver = null;
	LoginPage loginpage = null;
	
	
	@Before
	public void setUp() {
		 WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver();
		 loginpage = new LoginPage(driver);
	}

	@Given("user is on the login page")
	public void navigateToLoginPage() {
	  driver.get("https://automationintesting.online/#/admin");
	  loginpage.validateTitleOnLoginPage();
	}

	@When("^user enters (.*) and (.*)$")
	public void enterUsernameAndPassword(String username, String password) {
		
	    loginpage.enterUsername(username);
	    loginpage.enterPassword(password);
	}

	@And("clicks on login button")
	public void clickOnLoginButton() {
		loginpage.clickLoginButton();
	}

	@Then("user is navigated to the home page")
	public void isNavigatedToHomePage() {
		 System.out.println("User is navigated");
		 driver.quit();
	}
	
	@Then("user remains on the login page and cannot proceed to application")
	public void userCannotAccesTheApplication() {
		System.out.println("User cannot access the application");
		 driver.quit();
	}
	
}
