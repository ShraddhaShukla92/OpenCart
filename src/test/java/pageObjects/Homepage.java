package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {
	//WebDriver driver;
	public Homepage(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement MyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement Register;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement Login;
	public void ClickMyAccount() {
		MyAccount.click();
		System.out.println("MyAccount clicked");
	}
	
	public void ClickRegister() {
		Register.click();
		System.out.println("Register clicked");
	}
	public void ClickLogin() {
		Login.click();
		System.out.println("Login clicked");
	}
}
