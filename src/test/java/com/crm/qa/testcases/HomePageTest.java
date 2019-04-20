package com.crm.qa.testcases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends BaseTest{
	LoginPage loginPage;
	HomePage homePage;	
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest(){
		super(); //Calling super class constructor.
	}	
	
	
	@BeforeMethod
	public void setUp(java.lang.reflect.Method result) throws Exception{
		test = extent.startTest(result.getName());////ER
		test.log(LogStatus.INFO, result.getName() + " test Started");///////ER
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String actualHomePageTitle=homePage.verifyHomePageTitle();
		Assert.assertEquals(actualHomePageTitle,"CRMPRO","Home page title not matched");
	}	
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
		//driver.quit(); quit should be in Base Test Class
	}
	
}
