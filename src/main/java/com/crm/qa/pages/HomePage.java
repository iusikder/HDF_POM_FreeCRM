package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;
import com.crm.qa.util.TestUtil;

public class HomePage extends BaseTest {
	@FindBy(xpath="//td[@class='headertext']")
	WebElement userNameLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tesks')]")
	WebElement tasksLink;
	
	//Initializing the Page Objects....
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	
	
	//Methods
	public String verifyHomePageTitle(){
		////////////
		TestUtil testUtil = new TestUtil();
		testUtil.waitForPageToLoad();
		//////////////
		return driver.getTitle();		
	}
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage();		
	}
	
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnNewContactLink(){ //Clicking on New Contact Link
		Actions action = new Actions(driver);  //This code is to Mouse Over on Contact Button
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
	
	
	
}
