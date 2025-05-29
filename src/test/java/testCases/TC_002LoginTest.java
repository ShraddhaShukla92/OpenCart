package testCases;


import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.TestBase;

public class TC_002LoginTest extends TestBase {
	@Test(groups = {"sanity", "master"})
	
	public void Verify_Login() {
		logger.info("***********Starting TC002***********");
		try {
			Homepage hp = new Homepage(driver);
			logger.info("***********Click on MyAccount and login**********");
			hp.ClickMyAccount();
			hp.ClickLogin();
			
			logger.info("**********Providing login details*************");
			LoginPage lp = new LoginPage(driver);
			lp.SetEmail(prop.getProperty("registered_email"));
			lp.SetPass(prop.getProperty("registered_password"));
			lp.ClickLogin();
			logger.info("**************Login Successfull***************");
			
			MyAccount Acc = new MyAccount(driver);
			boolean targetPage = Acc.isMyAccountPageExists();
			Assert.assertTrue(targetPage);
			System.out.println("Test passed");
			logger.info("*******Test passed");
			
			
			
		}catch(Exception e) {
			Assert.fail();
		System.out.println("Test failed");
		}
		logger.info("************Login finished*************");
	}
	
}
