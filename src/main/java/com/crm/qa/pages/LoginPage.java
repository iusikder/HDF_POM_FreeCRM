package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ExtentReports.ExtentTestManager;
import com.crm.qa.base.BaseTest;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;


public class LoginPage extends BaseTest {
	TestUtil testUtil;/////////
	
	//Page Factory/OR
	@FindBy(name="username") 
	public WebElement username;
	
	@FindBy(name="password")
	public WebElement password;
	 
	//@FindBy(xpath="//input[@type='submit']")
	@FindBy(xpath="//input[@class='btn btn-small' and @type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement crmLogo;
	
	@FindBy(xpath="//input[@value='Quick Search']")
	WebElement quickSearch;
	
	//Initializing the Page Objects
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	
	//Methods
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();		
	}
	
	
	//Login Validation
	public HomePage login(String uName, String pWord) throws Exception{
		TestUtil testUtil = new TestUtil();////// I'm just checking		
		username.sendKeys(uName);  
		password.sendKeys(pWord);
		testUtil.wait(4);
		Actions actions = new Actions(driver); 
		actions.click(loginBtn).perform();		
		//Solution 2
		//THIS JAVASCRIPT WORKED for the Radio Button also !!!!!
		/*JavascriptExecutor js = (JavascriptExecutor) driver;   //Clicking on Login Button
		js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);",(loginBtn));
	
		*/
		//Logic/Validate
		testUtil.switchToFrame();		
	 	boolean loginSuccess = quickSearch.isDisplayed();
		if(loginSuccess){
			PageFactory.initElements(driver, HomePage.class);
			//test.log(LogStatus.INFO, "Login Success");
		}
		else{
			PageFactory.initElements(driver, LoginPage.class);
		}		
		return new HomePage();  //After Login it will return HomePage Object.
	}	
}


	