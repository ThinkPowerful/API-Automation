package automation.restassured.requestInspectionServices.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.base.test.BaseTest;
import com.restassured.utility.DataUtil;
import com.restassured.utility.Xls_Reader;

import automation.restassured.requestInspectionservices.GetHeadersApi;
import io.restassured.http.Headers;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;

public class GetHeadersResponseHeadersTest extends BaseTest{
	
	String sheetName="TS02";
	GetHeadersApi getHeaders;
	
	@BeforeClass
	public void initiateTestName() 
	{
		try {
			initTestName(testScenarioName.get("TS02"),authorName.get("TS02"),moduleName.get("TS02"));
			this.getHeaders= new GetHeadersApi();
		} catch (Exception e) {
			test.get(0).skip("@BeforeClass configuration failed");
			throw new SkipException ("Skipping Test: @BeforeClass configuration failed");
		}
	}
	
	@Test(dataProvider="getData")
	public void verifyGetHeadersResponseHeadersTest(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		try {
			ArrayList<String> missingAttributes = new ArrayList<String>();
			Response response = getHeaders.getHeaders();
			int actualStatusCode = response.getStatusCode();
			String actualResponseHeaders = response.getHeaders().toString();
			boolean isMatch = true;
		      String[] expectedResponseHeaderAttributes = data.get("Expected Header Attributes").split("\\|");
		      for (int i = 0; i < expectedResponseHeaderAttributes.length; i++) {
		        if (!actualResponseHeaders.contains(expectedResponseHeaderAttributes[i].split("=", 2)[0] + "=" 
		            + expectedResponseHeaderAttributes[i].split("=", 2)[1])) {
		          isMatch = false;
		          missingAttributes.add(expectedResponseHeaderAttributes[i]);
		        }
		      }
			if (actualStatusCode==Integer.parseInt(data.get("Expected Status Code"))  && isMatch == true) 
				{
				logDataSet(data);
				logSuccess("Response status code and response body attributes are as expected", actualResponseHeaders);
			} else {
				logDataSet(data);
				logFailure("Response status code or response header attributes are not as expected: "+ missingAttributes + " \n ", actualResponseHeaders);
				Assert.fail("Response status code and response header attributes are not as expected");
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
