package com.crm.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class DealsPageTest extends BaseTest{

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	DealsPage dealsPage;	
	
	String sheetName = "Deal_Sheet";
	public DealsPageTest(){ //Constructor of Deals Page.
		super();			//super keyword will call the Super constructor.
	}	
	
	@BeforeMethod
	public void setUp() throws Exception{
		//REPORT NOT INCLUDED
		initialization();
		loginPage = new LoginPage();   //Initializatin of LoginPage
		testUtil = new TestUtil();
		dealsPage = new DealsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		dealsPage = homePage.clickOnDealsLink();			
	}
	
	
	@Test(priority=1)
	public void verifyDealsPageTitle(){
		Assert.assertTrue(dealsPage.verifyDealsLabel(),"Deals Label is missing on the Page");
	}
	
	//Below Test did not work. The table might have been different. I have to check it....
	
//	@Test(priority=2)
//	public void selectDealsByCompany(){
//		dealsPage.selectDealsByCompany("ABCD");
//	}
	
	
	
	
	
	
	
	
	
	@AfterMethod
	public void tearDown(){
		//driver.quit();
		 if (driver == null) {
		        return;
		    }
		    driver.quit();
		    driver = null;
	}
	
	
	
	
	
	
			
}