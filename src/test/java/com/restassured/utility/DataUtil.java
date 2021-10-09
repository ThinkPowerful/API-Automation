package com.restassured.utility;

import java.util.Hashtable;

public class DataUtil 
{
	public static Object[][] loadDataIntoHashTable(Xls_Reader xls, String sheetName)
	{
		int rCount = xls.getRowCount(sheetName);
		//System.out.println("Row Count: "+rCount);
		int cCount = xls.getColumnCount(sheetName);
		//System.out.println("Coloumn Count: "+cCount);
		Object[][] data = new Object[rCount-1][1];
		int index = 0;
		Hashtable<String, String> table=null;
		for(int rNum=2;rNum<=rCount;rNum++)
		{
			table=new Hashtable<String, String>();
			for(int cNum=0;cNum<=cCount;cNum++)
			{
				String key = xls.getCellData(sheetName, cNum, 1);
				String value = xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
			}
			data[index][0] = table;
			index++;
		}
		
		return data;
		
	}

}
