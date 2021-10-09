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

import automation.restassured.anythingservices.AnythingApi;
import io.restassured.response.Response;

public class DeleteAnythingResponseBodyTest extends BaseTest{
	
	String sheetName = "TS05";
	AnythingApi deleteAnything;

	@BeforeClass
	public void initiateTestName() {
		try {
			initTestName(testScenarioName.get("TS05"), authorName.get("TS05"), moduleName.get("TS05"));
			this.deleteAnything = new AnythingApi();
		} catch (Exception e) {
			test.get(0).skip("@BeforeClass configuration failed");
			throw new SkipException("Skipping Test: @BeforeClass configuration failed");
		}
	}

	@Test(dataProvider = "getData")
	public void verifyPostAnythingResponseBodyTest(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		try {
			Response response = deleteAnything.deleteAnythingAsQueryParam(data.get("Skip"), data.get("Limit"));
			int actualStatusCode = response.getStatusCode();
			String actualResponseBody = deleteAnything.getPrettifiedResponseBody(response);
			if (actualStatusCode == 200 && actualResponseBody.contains(data.get("Expected Response Attributes"))) {
				logDataSet(data);
				logSuccess("Actual status code " + actualStatusCode + "and response attributes matches with expected status code 200 and attributes"
						+ " \n", actualResponseBody);
				Assert.assertTrue(true);
			} else {
				logDataSet(data);
				logFailure("Actual status code " + actualStatusCode
						+ ", Either status code or response attributs are not matching" + " \n", actualResponseBody);
				Assert.fail();
			}
		} catch (Exception e) {
			test.get(0).skip("Skipping This test due to exception: " + e);
			Assert.fail("Skipping This test due to exception: " + e);
		}
	}

	@DataProvider(name = "getData")
	public Object[][] getData() {
		return DataUtil.loadDataIntoHashTable(
				new Xls_Reader(System.getProperty("user.dir") + "/ExcelFiles/TestData.xlsx"), sheetName);
	}

}
