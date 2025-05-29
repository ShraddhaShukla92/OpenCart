package testCases;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.TestBase;
import utilities.DataProviders;

public class TC_003LoginTestDDT extends TestBase{

	@Test(dataProvider = "LoginData", groups = "data driven")
	public void Verify_LoginDDT(String email, String pwd, String exp) {
		logger.info("************Starting TC003*****************");
		try {
			
		Homepage hp = new Homepage(driver);
		hp.ClickMyAccount();
		hp.ClickLogin();
		//Login
		LoginPage lp = new LoginPage(driver);
		lp.SetEmail(email);
		lp.SetPass(pwd);
		lp.ClickLogin();
		
		
		
	
		MyAccount acc = new MyAccount(driver);
		boolean targetpage = acc.isMyAccountPageExists();
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetpage==true) {
				acc.ClickLogOut();
				Assert.assertTrue(true);
				System.out.println("login success Testpassed");
				
			}
			else {
				Assert.assertTrue(false);
				System.out.println("Testfailed valid data-login fail");
			}
			
		}
		else {
			if(targetpage == true) {
				acc.ClickLogOut();
				Assert.assertTrue(false);
				System.out.println("Testfails invalid data login success");
				
			}
			
			else {
				Assert.assertTrue(true);
				System.out.println("Testpassed invalid");
				
			}
		}
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info("***********Test finished**************");
	}

}
