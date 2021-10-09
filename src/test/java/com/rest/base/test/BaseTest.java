package com.rest.base.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Test;
import com.rest.reports.ExtentManager;
import com.restassured.utility.Xls_Reader;

import automation.restassured.baseservice.BaseOperation;

public class BaseTest {
	
	public ExtentReports rep  = ExtentManager.setup();
	public ArrayList<ExtentTest> test =new ArrayList<ExtentTest>();
	public Hashtable<String, String> testScenarioName=new Hashtable<String, String>();
	public Hashtable<String, String> moduleName=new Hashtable<String, String>();
	public Hashtable<String, String> authorName=new Hashtable<String, String>();
	public Properties prop = new Properties();
	public InputStream input = null;
	
	@BeforeSuite
	public void setCookie() throws IOException
	{
		InputStream in = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(in);
		prop.store(new FileOutputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties"), null);
	}
	
	@BeforeTest
	public void doSetup() throws IOException
	{
		Xls_Reader xls =new Xls_Reader(System.getProperty("user.dir")+"/ExcelFiles/TestSuite.xlsx");
		int rCount = xls.getRowCount("TestScenarious");
		for(int rNum=2;rNum<=rCount;rNum++)
		{
			testScenarioName.put(xls.getCellData("TestScenarious", 0, rNum),xls.getCellData("TestScenarious", 1, rNum));
			moduleName.put(xls.getCellData("TestScenarious", 0, rNum),xls.getCellData("TestScenarious", 2, rNum));
			authorName.put(xls.getCellData("TestScenarious", 0, rNum),xls.getCellData("TestScenarious", 3, rNum));
		}
	}
	
	public void initTestName(String testName, String author, String testCategory) throws IOException
	{
		test.add(rep.createTest(testName));
		Test test1 = new Test();
		test1.setName(testName);
		test.get(0).assignAuthor(author);
		test.get(0).assignCategory(testCategory);
		input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(input);
	}
	
	public void logDataSet(Hashtable<String, String> data)
	{
		test.get(0).info("Test Data Set: "+data.entrySet().toString());
	}
	
	public void logRequestPayLoad(String requestPayload) throws IOException
	{
		test.get(0).info("Request Body: "+ new BaseOperation().extentReportFormatter(requestPayload));
	}
	
	public void logFailure(String failureLogMessage, String responsePayload ) throws IOException
	{
		test.get(0).fail(failureLogMessage+ new BaseOperation()
				.extentReportFormatter(responsePayload));
	}
	
	public void logSuccess(String successLogMessage, String responsePayload) throws IOException
	{
		test.get(0).pass(successLogMessage+ new BaseOperation()
				.extentReportFormatter(responsePayload));
	}
	
	
	@AfterSuite(alwaysRun=true)
	public void flushReport() throws IOException
	{
		rep.flush();
	}

}
