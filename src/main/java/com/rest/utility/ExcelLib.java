package com.rest.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib 
{
	
	public static int getRowCount(String xlpath,String sheetName)
	{
		try
		{
			FileInputStream fis=new FileInputStream(xlpath);
			Workbook wb=WorkbookFactory.create(fis);
			int rc=wb.getSheet(sheetName).getLastRowNum();
			return rc;
			
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	public static String getCellValue(String xlpath, String SheetName, int rowNum,int cellNum)
	{
		try
		{
			FileInputStream fis=new FileInputStream(xlpath);
			Workbook wb=WorkbookFactory.create(fis);
			String rcVal = wb.getSheet(SheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
			return rcVal;
		}
		catch(Exception e)
		{
			return "";
		}
	}
	public static int getCellValueInt(String xlpath, String SheetName, int rowNum,int cellNum)
	{
		try
		{
			FileInputStream fis=new FileInputStream(xlpath);
			Workbook wb=WorkbookFactory.create(fis);
			int rcVal = (int) wb.getSheet(SheetName).getRow(rowNum).getCell(cellNum).getNumericCellValue();
			return rcVal;
		}
		catch(Exception e)
		{
			return 5555;
		}
	}
	public static String setCellValue(String xlpath,int cell, int row, String content, String sheetName)
	{
		try
		{
			FileInputStream fis=new FileInputStream(xlpath);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet1=wb.getSheet(sheetName);
			sheet1.getRow(row).createCell(cell).setCellValue(content);
			FileOutputStream fos= new FileOutputStream(xlpath);
			wb.write(fos);
			return "";
		}
		catch(Exception e)
		{
			return "";
		}
	}

}
