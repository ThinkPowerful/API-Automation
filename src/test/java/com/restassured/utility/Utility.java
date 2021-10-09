package com.restassured.utility;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Utility {
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+|}{:?><,./;''][";
	
	public static String randomAlphaNumeric(int count) 
	{
	StringBuilder builder = new StringBuilder();
	while (count-- != 0) 
	{
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
			return builder.toString();
	}
	
	public static XmlPath rawToXML(Response r)
	{
		String response=r.asString();
		XmlPath x = new XmlPath(response);
		return x;
	}

	public static JsonPath rawToJSON(Response r)
	{
		String response=r.asString();
		JsonPath x = new JsonPath(response);
		return x;
	}
}
