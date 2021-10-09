package com.rest.reports;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentManager {

	static ExtentSparkReporter htmlReporter;
	static ExtentReports extent;
	static Properties prop = new Properties();
	static InputStream input = null;
	public static final String REPORT_FILE_PATH = System.getProperty("user.dir")+"/ExtentReports/";
	
	public static ExtentReports setup() 
	{
		if (extent == null) 
		{
			try {
				input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
				prop.load(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports();
			htmlReporter =  new ExtentSparkReporter(REPORT_FILE_PATH+fileName);
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setReportName("Regression testing executed in: "+prop.getProperty("BASEURI"));
			htmlReporter.config().setTheme(Theme.STANDARD);
		}
		return extent;
	}

}
