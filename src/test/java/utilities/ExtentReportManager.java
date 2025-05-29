
	package utilities;

	import java.awt.Desktop;
	import java.io.File;
	import java.io.IOException;
	import java.net.URL;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.List;

	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.reporter.configuration.Theme;

	import testBase.TestBase;

	public class ExtentReportManager implements  ITestListener{
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		String repName;
		
		public void onStart(ITestContext testContext) {
			String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
			repName = "Test-Report - " + timestamp +".html";
			sparkReporter = new ExtentSparkReporter("C:\\Users\\hp\\eclipse-workspace\\OpenCart\\OpenCart_Framework\\Reports\\" + repName);
			sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
			sparkReporter.config().setReportName("OpenCart Functional Testing");
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "OpenCart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub-Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
//			
//			String os = testContext.getCurrentXmlTest().getParameter("os");
//			extent.setSystemInfo("Operating System", os);
//			
//			String browser = testContext.getCurrentXmlTest().getParameter("browser");
//			extent.setSystemInfo("Browser Name", browser);
			
			List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includedGroups.toString());	
				
			}
		}
		public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());

			test.log(Status.PASS, result.getName() + "Got Successfully executed");
		}
		
		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, result.getName() + "Got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
			try {
				String imgpath = new TestBase().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgpath);
				
			} catch (IOException e1) {
				e1.printStackTrace();
				
				
			}
		}
			
			public void onTestSkipped(ITestResult result) {
				test = extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.SKIP, result.getName() + "got Skipped");
				test.log(Status.INFO, result.getThrowable().getMessage());
			}
			
			public void onFinish(ITestContext context) {
				extent.flush();
				String pathOfExtentReport = "C:\\Users\\hp\\eclipse-workspace\\OpenCart\\OpenCart_Framework\\Reports\\" + repName;
				File extentReport = new File(pathOfExtentReport);
				try { 
					Desktop.getDesktop().browse(extentReport.toURI());
					
				}catch(IOException e) {
					e.printStackTrace();
				}
			
			}
	}



