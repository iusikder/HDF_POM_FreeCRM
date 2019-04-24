package com.crm.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestResult;
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
	
	public LoginPageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setUp(Method method) {
		//ExtentReports Description
        ExtentTestManager.startTest(method.getName(),"Login Page Test.");
		initialization();			
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service00");
	}
	

	@Test(priority=2)
	public void crmLogoImgTest(){
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
/*
	@Test(priority=3)
	public void loginTest() throws Exception{
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}*/
	
	
	
/*
	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		driver.quit(); 
	}*/
	
}
