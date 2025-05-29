package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccount extends BasePage{

	public MyAccount(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = "//h2[text() = 'My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement btnLogout;
	
	public boolean isMyAccountPageExists() {
		try {
		return (msgHeading.isDisplayed());
	}catch(Exception e) {
		return false;
	}
	}
	
	public void ClickLogOut() {
		btnLogout.click();
		System.out.println("Logout successfull");
	}
}
