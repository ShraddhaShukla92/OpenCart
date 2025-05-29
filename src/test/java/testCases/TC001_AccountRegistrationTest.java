package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistration;
import pageObjects.Homepage;
import testBase.TestBase;

public class TC001_AccountRegistrationTest extends TestBase {
	
	
	@Test(groups = {"regression", "master"})
	public void Verify_Account_Registration() {
		try {
		logger.info("***********Starting TC001***********");
		Homepage hp = new Homepage(driver);
		logger.info("************Clicked on My Account and Register***********");
		hp.ClickMyAccount();
		hp.ClickRegister();
		AccountRegistration accreg = new AccountRegistration(driver);

		logger.info("************Generate user details***********");
		String fn = randomString().toUpperCase();
		String ln = randomString().toUpperCase();
		String email = (randomString().toLowerCase() + "@tmail.com");
		String phone = randomNumber();
		String pass = randomPass();
		
		accreg.SetFirstname(fn);
		accreg.SetLastname(ln);
		accreg.SetEmail(email);
		 accreg.SetTelephone(phone);
		 accreg.SetPassword(pass);
		 accreg.SetConfirmPassword(pass);
		 logger.info("************Accept privacy policy***********");
		 accreg.SetPrivacyPolicy();
		 logger.info("************Clicked Continue to register***********");
		accreg.ClickContinue();
		logger.info("************Account is registered***********");
		String cnfmsg = accreg.getConfirmationMsg();
		if(cnfmsg.equals("Your Account Has Been Created!")) {
			
			Assert.assertTrue(true);
			 logger.info("Saving registered user details to config.properties...");
            

         } else {
             logger.error("Test failed: Account not created.");
             logger.debug("Confirmation message: " + cnfmsg);
             Assert.assertTrue(false);
         }
		logger.info("***********Printing customer details***********");
		DisplayDetails(fn, ln, email, phone, pass, cnfmsg);
		} catch(Exception e) {
			
			 logger.error("TC001_AccountRegistrationTest failed due to exception: " + e.getMessage(), e);
	            Assert.fail("Test failed due to an exception.");
		}
		System.out.println("Test Passed");
		logger.info("***********Test Passed**************");
		}
	public void DisplayDetails(String fn, String ln, String email, String phone, String pass, String cnfmsg) {
		System.out.println("First name : " + fn);
		System.out.println("last name : " + ln);
		System.out.println("email  : " + email);
		System.out.println("Phone : " + phone);
		System.out.println("Password: " + pass);
		System.out.println("Confirm Password: "  + pass);
		System.out.println("Account Created: " + cnfmsg);
	}

	
}
