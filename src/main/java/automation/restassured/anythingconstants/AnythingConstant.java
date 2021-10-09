package automation.restassured.anythingconstants;

import org.json.JSONObject;

public class AnythingConstant {
	
public static String AnythingAPIRequestBody;
	
	public static void setPostAnythingRequestBody(String firstName, String lastName)
	{
		@SuppressWarnings("unused")
		JSONObject temp;
		JSONObject AnythingRequestJson = new JSONObject();
		temp = firstName != null ? AnythingRequestJson.put("firstName", firstName) : null;
		temp = lastName != null ? AnythingRequestJson.put("lastName", lastName) : null;
		AnythingAPIRequestBody = AnythingRequestJson.toString();
	}
	
	public static String getPostAnythingRequestBody() {
		return AnythingAPIRequestBody;
	}

}
