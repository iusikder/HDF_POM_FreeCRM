package com.crm.qa.testcases;


import java.lang.reflect.Method;

import org.testng.Assert;
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
	
	@BeforeMethod  //I HAVE TO CHECH THE DESCRIPTION ONCE REPORT IS DONE
	public void setUp(Method method){
		 ExtentTestManager.startTest(method.getName(), "LoginPageTest");
		
		initialization();			
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
		public void loginPageTitleTest(){
       	String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
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
	}
	*/
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		   
		}
	
}
