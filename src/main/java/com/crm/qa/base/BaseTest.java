package com.crm.qa.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;


	public class BaseTest {		
		public static WebDriver driver;
		public static Properties prop;
		public static EventFiringWebDriver e_driver;
		public static WebEventListener eventListener;

	//	public static ExtentReports extent;
	//	public static ExtentTest test;
		//public ITestResult result;

		public WebDriver getDriver() {
	        return driver;
		}
		
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
		

		//	 EXTENT REPORT	
	/*	static {		
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			//Code below is working good
			//extent = new ExtentReports(System.getProperty("user.dir")+ "./Reports/Extent_Report/test" + formater.format(calendar.getTime()) + ".html");
			extent = new ExtentReports(System.getProperty("user.dir") + "./Reports/Extent_Report/test.html");
				
		}
*/
		
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
			
			//WEB DRIVER FIRING EVERNT // I HAVE TO CHECK IF IT IS WORKING OR NOT.........
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
		
		//TAKING SCREENSHOT
		/*public String getScreenShot(String imageName) throws IOException{			
			if(imageName.equals("")){
				//imageName = "blank";
			}
			File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String imagelocation= System.getProperty("user.dir")+ "./Reports/Screenshots.png";
		    //String imagelocation = System.getProperty("user.dir") + "/src/main/java/com/crm/qa/screenshot/";
		    Calendar calendar = Calendar.getInstance();
		    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
			File destFile = new File(actualImageName);
			FileUtils.copyFile(image, destFile);
		return actualImageName;
		}*/
	
	/*	public void getresult(ITestResult result) throws IOException {
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
		*/
		
		
	}
