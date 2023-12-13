package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

WebDriver driver = null;
	
	
	//Selectors
	@FindBy (css = "h2[data-testid='login-header']")
	public WebElement loginTitle;
	@FindBy (css = "button#doLogin")
	public WebElement loginButton;
	@FindBy (css = "a.navbar-brand.mx-auto")
	public WebElement roomTitle;
	@FindBy (css = "input#username")
	public WebElement usernameInput;
	@FindBy (css = "input#password")
	public WebElement passwordInput;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		this.usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		this.passwordInput.sendKeys(password);
	}
	
	public void clickLoginButton() {
		this.loginButton.click();
	}
	
	public void validateTitleOnLoginPage() {
		this.loginTitle.isDisplayed();
		String textFromTitle = loginTitle.getText();
		
        System.out.println("Text from element: " + textFromTitle);
        
	}
	
}
