package com.ankur.utils;

import java.io.File;
import java.io.IOException;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    
    private static ExtentReports extent;
    static ReportDirectory reportDirectory;
    static String reportDir;
    
    public static ExtentReports getInstance(String fileName) throws IOException {
    	
    	if (extent == null) {
  
    		extent = new ExtentReports(fileName, true, DisplayOrder.OLDEST_FIRST);

			//Loading the configuration file for the report
			extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\reportConfig.xml"));
			
			//Adding the system information in the reports
			extent.addSystemInfo("Selenium Version", "3.141.59").addSystemInfo("WebSite", "http://automationpractice.com");
    	}
   
    	
        return extent;
    }
    
}
