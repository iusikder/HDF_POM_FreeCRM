package com.Listener;

import com.ExtentReports.ExtentManager;
import com.ExtentReports.ExtentTestManager;
import com.crm.qa.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 
 
public class TestListener extends BaseTest implements ITestListener { 
	    private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
	 
	    public void onStart(ITestContext iTestContext) {
	        System.out.println("I am in onStart method " + iTestContext.getName());
	     //   iTestContext.setAttribute("WebDriver", BaseTest.driver);
	        iTestContext.setAttribute("WebDriver", driver);
	    }
	 
	    public void onFinish(ITestContext iTestContext) {
	        System.out.println("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for extentreports reporting!
	        ExtentTestManager.endTest();
	        ExtentManager.getReporter().flush();
	    }
	 
	    public void onTestStart(ITestResult iTestResult) {
	        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");	        
	    }
	 
	    public void onTestSuccess(ITestResult iTestResult) {
	        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
	        //Extentreports log operation for passed tests.
	        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
	    }
	 //////////
	    public void onTestFailure(ITestResult iTestResult) {
	        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
	      // I ADDED THIS LINE BELOW
	        //ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getName() + " Test Failed" + iTestResult.getThrowable());    
	         ExtentTestManager.getTest().log(LogStatus.FAIL, getTestMethodName(iTestResult) + "-- Test Failed --" + iTestResult.getThrowable());    
	        /////////////
	        //Get driver from BaseTest and assign to local webdriver variable.
	        Object testClass = iTestResult.getInstance();
	        WebDriver webDriver = ((BaseTest) testClass).getDriver();
	 
	        //Take base64Screenshot screenshot.
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	                getScreenshotAs(OutputType.BASE64);
	 
	        //Extentreports log and screenshot operations for failed tests.
	        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
	                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
	    }
	 
	    public void onTestSkipped(ITestResult iTestResult) {
	        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
	        //Extentreports log operation for skipped tests.
	     //   ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	        // I ADDED THIS CODE BUT AS OF NOW I DON'T HAVE ANY METHOD TO SKIP THE TEST. ONCE I HAVE IT THEN I WILL TEST THIS CODE 
	        ExtentTestManager.getTest().log(LogStatus.SKIP, getTestMethodName(iTestResult) + "-- Test Skipped" + iTestResult.getThrowable()); 
	    }

	 
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	       // System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	    }
}
	    

	    
	    
	  










/*


    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
 
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }
 
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
 //////////////////from here i can copy
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
        ExtentTestManager.getTest().log(LogStatus.INFO, iTestResult.getName() + "Test is Starting");//i don't know how it will be....
    }
 //////////////////upto here
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
       // ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        ExtentTestManager.getTest().log(LogStatus.PASS, iTestResult.getName() + "Test passed");  //Changed the code
    }
 
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
 ///////////////////////  I CREATED THIS CODE 
   // } else if (result.getStatus() == ITestResult.FAILURE) {
     //   ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getName() + " Test Failed" + iTestResult.getThrowable());    
    
        
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
                getScreenshotAs(OutputType.BASE64);
 
        //Extentreports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
                ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
 
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        //Extentreports log operation for skipped tests.
       // ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
         ExtentTestManager.getTest().log(LogStatus.SKIP,  iTestResult.getName() + "Test Skipped");
    }
 
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}*/