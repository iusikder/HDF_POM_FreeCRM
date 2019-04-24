package com.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;	 
//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
	


public class ExtentManager {
	 
   private static ExtentReports extent;
	 
   public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"./ExtentReports/ExtentReportResults.html");
            //extent = new ExtentReports(workingDir+"\\test-output\\Extent.FreeCRM.html", false);
        }
        return extent;
    }
}

