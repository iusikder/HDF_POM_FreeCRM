package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.BaseTest;

public class DealsPage extends BaseTest {
	@FindBy(xpath="//td[contains(text(),'Deals')]")
	WebElement dealsLabel;
	
	public DealsPage(){   //Initializing the Page Objects
		PageFactory.initElements(driver, this); 
	}

	
	
	//Methods
	public boolean verifyDealsLabel(){
		return dealsLabel.isDisplayed();
	}
		
	//Selecting CheckBox (Divided into two lines)
	public void selectDealsByCompany(String company){
		driver.findElement(By.xpath("//a[text()='"+company+"']//parent::td[@class='datalistrow']"
				+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']"));																	   
	}
}
