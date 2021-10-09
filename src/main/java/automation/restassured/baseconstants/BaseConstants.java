package automation.restassured.baseconstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseConstants {
	
	public static List<Map<String, String>> setMultipleInfoIntoArray(String value) {
		String[] eachObject = value.split(";");
		List<Map<String, String>> tripletArray = new ArrayList<Map<String, String>>();
		for (int i = 0; i < eachObject.length; i++) {
			String[] eachKeyVal = eachObject[i].split("-");
			Map<String, String> keyVal = new HashMap<String, String>();

			for (int j = 0; j < eachKeyVal.length; j++) {
				keyVal.put(eachKeyVal[j].substring(0, eachKeyVal[j].indexOf("=")),
						eachKeyVal[j].substring(eachKeyVal[j].indexOf("=") + 1));
			}
			tripletArray.add(keyVal);
		}
		return tripletArray;
	}
	
	public static Map<String, String> setObject(String tripletInfo) {
		String[] eachTriplet = tripletInfo.split(",");
		Map<String, String> keyVal = new HashMap<String, String>();
		for (int j = 0; j < eachTriplet.length; j++) {
			keyVal.put(eachTriplet[j].substring(0, eachTriplet[j].indexOf("=")),
					eachTriplet[j].substring(eachTriplet[j].indexOf("=") + 1));
		}
		return keyVal;
	}
	
	public static String[] setArray(String values) {
		return values.split(";");
	}

}
