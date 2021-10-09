package automation.restassured.anythingservices;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import automation.restassured.anythingconstants.AnythingConstant;
import automation.restassured.baseservice.BaseOperation;
import io.restassured.response.Response;

public class AnythingApi extends BaseOperation {

	public AnythingApi() throws IOException {
		super();
	}

	public Response postAnythingAsRequestBodyData(String firstName, String lastName) throws InterruptedException {
		AnythingConstant.setPostAnythingRequestBody(firstName, lastName);
		String body = AnythingConstant.getPostAnythingRequestBody();
		Response res = given().header("Content-type", prop.get("Content-type")).body(body).when()
				.post(prop.getProperty("BASEURI") + "/anything");
		return res;
	}

	public Response putAnythingAsRequestFormData(String country, String city) throws InterruptedException {
		Response res = given().header("Content-Type", "multipart/form-data").multiPart("country", country).multiPart("city", city)
				.when().put(prop.getProperty("BASEURI") + "/anything");
		return res;
	}

	public Response deleteAnythingAsQueryParam(String skip, String limit) throws InterruptedException {
		Response res = given().header("Content-type", prop.get("Content-type")).queryParam("skip", skip)
				.queryParam("limit", limit).when().delete(prop.getProperty("BASEURI") + "/anything");
		return res;
	}

}
