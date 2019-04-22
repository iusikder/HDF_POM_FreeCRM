package com.crm.qa.base;
	import java.io.File;
import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
	import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


	public class BaseTest {		
		public static WebDriver driver;
		public static Properties prop;
		public static EventFiringWebDriver e_driver;
		public static WebEventListener eventListener;
		///////////////
		public static ExtentReports extent;
		public static ExtentTest test;
		public ITestResult result;
		
		//String concatenate = ".";
		///////////////////
		
		public BaseTest(){    //Constructor of BaseTest Class to Initialize the Properties file.
			try{
				prop = new Properties();
				//FileInputStream ip = new FileInputStream("/Proj_FreeCRMTest/src/main/java/com/crm/config/config.properties");
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/crm/qa/config/config.properties");
				prop.load(ip);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		
		///////////////////////////////
				
		static {		
			//Calendar calendar = Calendar.getInstance();
			//SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			//reporter =new ExtentHtmlReporter("./Reports/learn_automation.html");
			//extent = new ExtentReports("./src/main/java/com/crm/qa/report/test" + formater.format(calendar.getTime()) + ".html", false);
			
			//Code below is working good
			//extent = new ExtentReports(System.getProperty("user.dir")+ "./Reports/Extent_Report/test" + formater.format(calendar.getTime()) + ".html");
			extent = new ExtentReports(System.getProperty("user.dir")+ "./Reports/Extent_Report/test.html");
				
		}

		
		///////////////////////////////
		
		public static void initialization(){
			String browserName = prop.getProperty("browser");			
			if(browserName.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", "C:/AllDrivers/LatestChrome/chromedriver.exe");
				driver = new ChromeDriver();				
			}
			else if(browserName.equals("FF")){
				System.setProperty("webdriver.gecko.driver", "C:/AllDrivers/NewGeckoDriver/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			
			//WebDriver FiringEvent // I HAVE TO CHECK IF IT IS WORKING OR NOT.........
			e_driver= new EventFiringWebDriver(driver);
			//Now Create object of EventListenerHandler to register it EventFiringWebDriver
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;			
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
		}
		
		///////////////////////
//		
//		//This Screenshot is different from other screenshot which I have used before... 
//		//Taking Screenshot
//		public static void takeScreenshotAtEndOfTest() throws IOException{
//			File scrFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			String currentDir = System.getProperty("user.dir");
//		
//			//String filePath=com.pom.util.Constants.REPORT_PATH+"screenshots//"+screenshotFile; //I added this line to test
//			//	if(osName.startsWith("Mac")){  //If I'm using Mac then I have to do like this
//				FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
//			//}
//			//else{
//				//FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
//			}
		
		////////////////////////
		//System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/report/test" + formater.format(calendar.getTime()) + ".html", false);
		//System.getProperty("user.dir")+"/src/main/java/com/hybridFramework/screenshot/";
		
		
		
		
		//Taking Screenshot
		public String getScreenShot(String imageName) throws IOException{			
			if(imageName.equals("")){
				imageName = "blank";
			}
			File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
			String imagelocation= System.getProperty("user.dir")+ "./Reports/Screenshots/";
			//String imagelocation = System.getProperty("user.dir") + "/src/main/java/com/crm/qa/screenshot/";
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
			File destFile = new File(actualImageName);
			FileUtils.copyFile(image, destFile);
		return actualImageName;
		}
		
//		public void passFailScreenshot(String name) throws IOException{
//			String screenshotName = getScreenShot(name);
//			getresult(result);
//		}
//		
		
		//I included name as Parameter, if it is not working then I will delete it....
		
		public void getresult(ITestResult result) throws IOException {
			if (result.getStatus() == ITestResult.SUCCESS) {

				test.log(LogStatus.PASS, result.getName() + " test is pass");
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
			} else if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
				String screen = getScreenShot("");
				test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			} else if (result.getStatus() == ITestResult.STARTED) {
				test.log(LogStatus.INFO, result.getName() + " test is started");
			}
	}
		
		
		@AfterClass(alwaysRun = true)
		public void endTest() {
			//driver.quit();
			extent.endTest(test);
			extent.flush();
	}
		
		/*public static Object screenCapture(String logdetails, String imagePath){
			//Report with Screenshot
			test.log(LogStatus.INFO,logdetails, MediaEntityBuilder.compareToIgnoreCase(String)reateScreenCaptureFromPath(imagePath).build());
			return test;
		}*/
		
		
	}
