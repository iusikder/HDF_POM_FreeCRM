package com.crm.qa.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ExtentReports.ExtentTestManager;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;


public class LoginPageTest extends BaseTest {
	LoginPage loginPage;
	HomePage homePage;	
	
	String sheetName = "Login_Sheet";	
	public LoginPageTest(){
		super();
	}
	
	
	
	@BeforeMethod
	public void setUp(){       
		initialization();			
		loginPage = new LoginPage();
	}
	/*
	   @Test (priority = 0, description="Invalid Login Scenario with wrong username and password.")
	   
	    public void invalidLoginTest_InvalidUserNameInvalidPassword (Method method) {
	        //ExtentReports Description
	        ExtentTestManager.startTest(method.getName(),"Invalid Login Scenario with empty username and password.");
	 */
	
	

	@Test(priority=1)
	public void loginPageTitleTest(Method method){
		//ExtentReports Description
		ExtentTestManager.startTest(method.getName(), "Login Page Title Test"); 		 
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
	}

	@Test(priority=2)
	public void crmLogoImgTest(Method method){
		ExtentTestManager.startTest(method.getName(),"CRMLogo Image Test.");		 
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void loginTest(Method method) throws Exception{
		//ExtentReports Description
		ExtentTestManager.startTest(method.getName(),"Login Test Verification.");		
		Object page =loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			
		
		//verify Login
		if(page instanceof LoginPage)
			Assert.fail("Login Failed");
		else if(page instanceof HomePage)
			System.out.println("Logged in successfully");
			//test.log(LogStatus.PASS, "Test Passed");			
	}		

	@AfterMethod()
	public void tearDown(){
		driver.quit(); 
	}
	
}







