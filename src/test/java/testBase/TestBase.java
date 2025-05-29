package testBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {
	public static WebDriver driver;
	public Logger logger;
	public Properties prop;
	

	@BeforeClass(groups = {"sanity", "regression", "master"})
	@Parameters("browser")
	public void setup(String browser) throws IOException {
		//Loading config.properties
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		logger =  LogManager.getLogger(this.getClass());
		switch(browser.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser");
		return;
		}
		
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterClass(groups = {"sanity", "regression", "master"})
	public void teardown() {
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		String GenerateString = RandomStringUtils.randomAlphabetic(8);
		return GenerateString;
	}

	public String randomNumber() {
		String GenerateNumber = RandomStringUtils.randomNumeric(9);
		return GenerateNumber;
	}

	   public String randomAlphaNumeric() {
	        String str = RandomStringUtils.randomAlphabetic(3);
	        String num = RandomStringUtils.randomNumeric(3);
	        return (str + "@" + num);
	    }
	   
	   public String randomPass() {
	        String pass = RandomStringUtils.randomAlphanumeric(8);
	        return pass;
	    }
	   public void saveProperties(String key, String value) {
	        try (FileOutputStream output = new FileOutputStream(".//src//test//resources//config.properties")) {
	            prop.setProperty(key, value);
	            prop.store(output, null); // The second argument is a comment for the file
	        } catch (IOException e) {
	            e.printStackTrace();
	            logger.error("Failed to save property: " + key + "=" + value, e);
	        }
	    }
	   public String getProperty(String key) {
	        return prop.getProperty(key);
	    }
	   
	   public String captureScreen(String tname) throws IOException {
		   String timestamp = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss").format(new Date());
		   TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		   File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		   
		  String targetFilePath = System.getProperty("user.dir") + "\\Screenshots\\" + tname + "_" +timestamp + ".png";
		  File targetFile = new File(targetFilePath); 
		  src.renameTo(targetFile);
		  
		  return targetFilePath;
		   
	   }

}
