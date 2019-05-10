package com.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;	 
//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
	


public class ExtentManager {
	 
   private static ExtentReports extent;
	
   //Note:--- "AnnotationTransformer" and "Retry" classes were creating problem that's why I deleted these two 
   //		Classes. Until now I don't need Retry the failed Tests. If need be in the future then I can add those
   //		two files. That's why I'm writing this note. 
   
   public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            //extent = new ExtentReports(workingDir+"./ExtentReports/ExtentReportResults.html");
            //Extent Report was not coming in Jenkins in the above way, that is why I had to use "Path" below...
            extent = new ExtentReports(workingDir+"./test-output/Extent.FreeCRM.html"); 
        }
        return extent;
    }
}

