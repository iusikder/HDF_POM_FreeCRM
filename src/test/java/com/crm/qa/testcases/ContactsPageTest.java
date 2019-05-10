package com.crm.qa.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ExtentReports.ExtentTestManager;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class ContactsPageTest extends BaseTest {
	LoginPage loginPage;
	Object homePage;	
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "Contact_Sheet";
	public ContactsPageTest(){
		super();
	}	

	
	
	@BeforeMethod
	public void setUp(Method result) throws Exception{
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage =loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		//testUtil.switchToFrame();
		contactsPage = ((HomePage) homePage).clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel(Method method){
		ExtentTestManager.startTest(method.getName(),"Verify Contacts Page Label.");
		//ExtentTestManager.startTest(method.getName(),"Login Page Title Test."); 
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts Label is missing on the Page");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest(Method method){
		ExtentTestManager.startTest(method.getName(),"Select Single Contacts Test.");
		contactsPage.selectContactsByName("Shameem Hossain");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(Method method){
		ExtentTestManager.startTest(method.getName(),"Select Multiple Contacts Test.");
		contactsPage.selectContactsByName("Shameem Hossain");
		contactsPage.selectContactsByName("test2 test2");
	}	
	
	@Test(priority=4, dataProvider="getCRMTestData")
		public void validateCreateNewContact(String title, String fName, String lName, String company, Method method){
		ExtentTestManager.startTest(method.getName(),"Validate Create New Contacts.");
		((HomePage) homePage).clickOnNewContactLink();
		contactsPage.createNewContacts(title, fName, lName, company);
		((HomePage) homePage).clickOnContactsLink();		
	//Assert.assertTrue(contactsPage.verifyContactsByName("ABCD EFG"));
	
	}  
	
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}	

	@AfterMethod()
	public void afterMethod(){
		driver.quit();
	}
	
}




















