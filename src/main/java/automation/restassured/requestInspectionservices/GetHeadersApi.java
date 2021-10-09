package automation.restassured.requestInspectionservices;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import automation.restassured.baseservice.BaseOperation;
import io.restassured.response.Response;

public class GetHeadersApi extends BaseOperation{
	
	public GetHeadersApi() throws IOException {
		super();
	}

	public Response getHeaders() {
		Response res;
			res = given().when().header("Accept", prop.get("Content-type"))
					.get(prop.getProperty("BASEURI") + "/headers");
			return res;
	}

}
