package automation.restassured.requestInspectionServices.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.base.test.BaseTest;
import com.restassured.utility.DataUtil;
import com.restassured.utility.Xls_Reader;

import automation.restassured.requestInspectionservices.GetHeadersApi;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;

public class GetHeadersResponseBodyTest extends BaseTest{
	
	String sheetName="TS01";
	GetHeadersApi getHeaders;
	
	@BeforeClass
	public void initiateTestName() 
	{
		try {
			initTestName(testScenarioName.get("TS01"),authorName.get("TS01"),moduleName.get("TS01"));
			this.getHeaders= new GetHeadersApi();
		} catch (Exception e) {
			test.get(0).skip("@BeforeClass configuration failed");
			throw new SkipException ("Skipping Test: @BeforeClass configuration failed");
		}
	}
	
	@Test(dataProvider="getData")
	public void getHeadersResponseBodyTest(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		try {
			ArrayList<String> missingAttributes = new ArrayList<String>();
			Response response = getHeaders.getHeaders();
			String actualResponseBody = getHeaders.getPrettifiedResponseBody(response);
			int actualStatusCode = response.getStatusCode();
			boolean isMatch = true;
		      String[] expectedResponseBodyAttributes = data.get("Expected Attributes").split("\\|");
		      for (int i = 0; i < expectedResponseBodyAttributes.length; i++) {
		        if (!actualResponseBody.contains("\"" + expectedResponseBodyAttributes[i].split(":", 2)[0] + "\"" + ": " + "\""
		            + expectedResponseBodyAttributes[i].split(":", 2)[1] + "\"")) {
		          isMatch = false;
		          missingAttributes.add(expectedResponseBodyAttributes[i]);
		        }
		      }
			if (actualStatusCode==Integer.parseInt(data.get("Expected Status Code"))  && isMatch == true) 
				{
				logDataSet(data);
				logSuccess("Response status code and response body attributes are as expected", actualResponseBody);
				} else {
				/*test.get(0).info("Test Data Set: "+data.entrySet().toString());
				test.get(0).fail("Response status code or response body attributes are not as expected: "+ missingAttributes + " \n"+ getHeaders.extentReportFormatter(actualResponseBody));*/
				logDataSet(data);
				logFailure("Response status code or response body attributes are not as expected: "+ missingAttributes + " \n", actualResponseBody);
				Assert.fail("Response status code and response body attributes are not as expected");
			} 
		}
		catch (Exception e) {
			test.get(0).skip("Skipping This test due to exception: " + e);
			Assert.fail("Skipping This test due to exception: " + e);
		}
	}
	
	@DataProvider(name="getData")
	public Object[][] getData() 
	{
		return DataUtil.loadDataIntoHashTable(new Xls_Reader(System.getProperty("user.dir")+"/ExcelFiles/TestData.xlsx"), sheetName);
	}


}
