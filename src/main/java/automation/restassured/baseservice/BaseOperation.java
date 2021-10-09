package automation.restassured.baseservice;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import automation.restassured.baseservice.Utility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseOperation {
	
	public Properties prop = new Properties();
	public InputStream input = null;
	
	public BaseOperation() throws IOException {
		input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop.load(input);
	}
	
	public String extentReportFormatter(String reportLog) {
		return "<pre>" + reportLog + "</pre>";
	}

	public int getResponseStatusCode(Response res) {
		return res.getStatusCode();
	}

	public String getPrettifiedResponseBody(Response res) {
		Response rawResponse = res.then().extract().response();
		JsonPath JsonFormatedResponse = Utility.rawToJSON(rawResponse);
		return JsonFormatedResponse.prettify();
	}
	
	
	public String toPrettyFormat(String jsonString) 
	  {
	      JsonParser parser = new JsonParser();
	      JsonObject json = parser.parse(jsonString).getAsJsonObject();
	      Gson gson = new GsonBuilder().setPrettyPrinting().create();
	      String prettyJson = gson.toJson(json);
	      return prettyJson;
	  }
	
	public String readContentFromJsonFile(String jsonFilePath) throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(jsonFilePath);
		Object obj = jsonParser.parse(reader);
		return obj.toString();
	}

}
