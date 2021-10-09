package com.restassured.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateUtil 
{
	
	public static Date date1;
	public static Date date2;
	
	public static Date getCurrentdate() throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate=dateFormat.format(date);
		return dateFormat.parse(currentDate);
	}
	
	public static Date getCurrentdateInYearMonthDate() throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDate=dateFormat.format(date);
		return dateFormat.parse(currentDate);
	}
	
	public static Date getCurrentDateMinusOneDay() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String currentDateMinusOne = dateFormat.format(cal.getTime());
		return dateFormat.parse(currentDateMinusOne);
	}
	
	public static Date getCurrentDateMinusTwoDays() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -2);
		String currentDateMinusTwo=dateFormat.format(cal.getTime());
		return dateFormat.parse(currentDateMinusTwo);
	}
	
	public static Date getCurrentDateMinusFourDays() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -4);
		String currentDateMinusFour = dateFormat.format(cal.getTime());
		return dateFormat.parse(currentDateMinusFour);
	}
	
	public static String convertDateToString(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}
	
	public static String convertDateToStringInYearMonthDate(Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
	
	public static Date convertStringToDate(String date) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.parse(date);
	}
	
	public static boolean compareDatesDescending(String date1, String date2) throws ParseException
	{
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		Date dateOne=format.parse(date1);
		Date dateTwo=format.parse(date2);
		if(dateOne.compareTo(dateTwo)==0 || dateOne.compareTo(dateTwo)==-1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean compareDatesAscending(String date1, String date2) throws ParseException
	{
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		Date dateOne=format.parse(date1);
		Date dateTwo=format.parse(date2);
		if(dateOne.compareTo(dateTwo)==0 || dateOne.compareTo(dateTwo)==-1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
