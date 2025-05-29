package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage {

	public AccountRegistration(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement TxtFirstname;
	

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement TxtLastname;
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement CnfPass;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement Policy;
	
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement BtnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	

	public void SetFirstname(String firstname) {
		TxtFirstname.sendKeys(firstname);
		
	}
	
	public void SetLastname(String lastname) {
		TxtLastname.sendKeys(lastname);
		
	}
	
	public void SetEmail(String email) {
		txtEmail.sendKeys(email);
		
	}
	
	public void SetTelephone(String Phone) {
		txtPhone.sendKeys(Phone);
		
	}
	
	public void SetPassword(String password) {
		txtPassword.sendKeys(password);
		
	}
	
	public void SetConfirmPassword(String pwd) {
		CnfPass.sendKeys(pwd);
		
	}
	
	public void SetPrivacyPolicy() {
		Policy.click();
	}
	public void ClickContinue() {
		BtnContinue.click();
	}
	
	public String getConfirmationMsg() {
		try {
			System.out.println("Account created successfully");
			return (msgConfirmation.getText());
			
		}catch (Exception e) {
			return (e.getMessage());
		}
		
	}
	
	
 } 
