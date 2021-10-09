package automation.restassured.anythingservices.tests;

import java.awt.AWTException;
import java.io.IOException;
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

import automation.restassured.anythingconstants.AnythingConstant;
import automation.restassured.anythingservices.AnythingApi;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;

public class PostAnythingResponseBodyTest extends BaseTest{
	
	String sheetName="TS03";
	AnythingApi postAnything;
	
	@BeforeClass
	public void initiateTestName() 
	{
		try {
			initTestName(testScenarioName.get("TS03"),authorName.get("TS03"),moduleName.get("TS03"));
			this.postAnything= new AnythingApi();
		} catch (Exception e) {
			test.get(0).skip("@BeforeClass configuration failed");
			throw new SkipException ("Skipping Test: @BeforeClass configuration failed");
		}
	}
	
	@Test(dataProvider="getData")
	public void verifyPostAnythingResponseBodyTest(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		try {
			Response response = postAnything.postAnythingAsRequestBodyData(data.get("First Name"), data.get("Last Name"));
			int actualStatusCode = response.getStatusCode();
			String actualResponseBody = postAnything.getPrettifiedResponseBody(response);
			String requestBody = AnythingConstant.getPostAnythingRequestBody();
			String prettyRequestBody = postAnything.toPrettyFormat(requestBody);
			if (actualStatusCode==200 && actualResponseBody.contains(data.get("Expected Response Attributes"))) 
				{
				logDataSet(data);
				logRequestPayLoad(prettyRequestBody);
				logSuccess("Actual status code "+actualStatusCode+" matches with expected status code 200"+ " \n", actualResponseBody);
				Assert.assertTrue(true);
			} else {
				logDataSet(data);
				logRequestPayLoad(prettyRequestBody);
				logFailure("Actual status code "+actualStatusCode+" does not matches with expected status code 200"+ " \n", actualResponseBody);
				Assert.fail();
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
