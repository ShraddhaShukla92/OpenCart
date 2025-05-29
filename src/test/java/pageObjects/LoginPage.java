package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage  extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPass;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	public void SetEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void SetPass(String pass) {
		txtPass.sendKeys(pass);
	}
	
	public void ClickLogin() {
		btnLogin.click();
		System.out.println("Login successfull");
	}

}
