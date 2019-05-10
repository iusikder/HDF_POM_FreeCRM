package com.crm.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;

import com.crm.qa.base.BaseTest;

public class TestUtil extends BaseTest {			
	public static long PAGE_LOAD_TIMEOUT =40;
	public static long IMPLICIT_WAIT = 40;
	//For Reading Data. Excel Sheet Path
	public static String TESTDATA_SHEET_PATH =System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\testdata\\ExcelData.xlsx";
		
	static Workbook book;
	static Sheet sheet;
	
	
	
	

	
	
	
	
	//-----------------------FUCNTIONS----------------------------------------
	
	
	
	public void wait (int time){
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		while (i!=10){
			String state = (String)js.executeScript("return document.readyState;");
			System.out.println(state);
			
			if(state.equals("complete"))
				break;
		else
				wait(2);
			i++;
		}
		
		//Check jQuery status
		i=0;
		while(i!=10){
			
		Boolean result = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
		System.out.println(result);
		if(result)
			break;
		else
			wait(2);
		i++;					
		}
	}
	
	//Switching to Frame if there is any Frame........
	public void switchToFrame(){
		driver.switchTo().frame("mainpanel");								
	}
	
	

	
	//Reading Data from Excel File
	public static Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		try{
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		try{
			book = WorkbookFactory.create(file);
		}catch(InvalidFormatException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0; i < sheet.getLastRowNum(); i++){
				for(int k = 0; k< sheet.getRow(0).getLastCellNum(); k++){
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
			}
			return data;
		
		}
	/*//This Screenshot is different from other screenshot which I have used before... 
	//Taking Screenshot
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File scrFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
	
		//String filePath=com.pom.util.Constants.REPORT_PATH+"screenshots//"+screenshotFile; //I added this line to test
		//	if(osName.startsWith("Mac")){  //If I'm using Mac then I have to do like this
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		//}
		//else{
			//FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
		}
	}*/
	
	}
	
	
	
	
	
	
	
	
	
	
	



