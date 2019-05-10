package com.crm.qa.testcases;


import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ExtentReports.ExtentTestManager;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends BaseTest{
	LoginPage loginPage;
	HomePage homePage;	
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest(){
		super(); //Calling super class constructor.
	}	
	
	
	
	
	@BeforeMethod
	public void setUp(Method method) throws Exception{
		  //ExtentReports Description
        //ExtentTestManager.startTest(method.getName(),"Home Page Test.");		 		
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage =(HomePage) loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(Method method){
		ExtentTestManager.startTest(method.getName(),"Verify HomePage Title Test.");
		String actualHomePageTitle=homePage.verifyHomePageTitle();
		Assert.assertEquals(actualHomePageTitle,"CRMPRO22","Home page title not matched");
	}	
	
	@Test(priority=2)
	public void verifyUserNameTest(Method method){
		ExtentTestManager.startTest(method.getName(),"Verify Username Test.");
	//	testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(Method method){
		ExtentTestManager.startTest(method.getName(), "Verify Contacts Link Test.");
		//testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	

	@AfterMethod()
	public void afterMethod(){
		driver.quit(); 
	}
}
