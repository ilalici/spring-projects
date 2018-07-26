package com.DateDifference.model;

import java.util.stream.IntStream;

public class DateUtility {
	
	public static boolean isLeapYear(int year) {
		if (year % 4 != 0) {
			return false;
		} else if (year % 400 == 0) {
		    return true;
		} else if (year % 100 == 0) {
		    return false;
		} else {
		    return true;
		}
    }
	
	public static String getMonthName(int i) {
		String[] monthNames = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		return monthNames[i-1];
	}
	
	public static String ordinal(int i) {
	    String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	    switch (i % 100) {
	    case 11:
	    case 12:
	    case 13:
	        return i + "th";
	    default:
	        return i + sufixes[i % 10];
	    }
	}
	
	public static int calculateDays(Date start, Date end) {
		
		int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31},
			  daysInMonthLeap = {31,29,31,30,31,30,31,31,30,31,30,31};
		
		int totalDate1 = start.getTotalDate();			
		int totalDate2 = end.getTotalDate();
		int total = 0;
		
		if(totalDate1 == totalDate2) {
			return total;
		} else if (totalDate1 > totalDate2) {
		   Date temp = start;
		   start = end;
		   end = temp;
		}
		
		int yearDiff = end.getYear() - start.getYear();
		
		if(yearDiff == 0) {
			total += isLeapYear(start.getYear()) ? (daysInMonthLeap[start.getMonth()-1] - start.getDay()) 
					                               + IntStream.of(daysInMonthLeap).skip(start.getMonth()).limit(end.getMonth()-2).sum()
					                               + end.getDay() - 1
					                              : (daysInMonth[start.getMonth()-1] - start.getDay()) 
					                               + IntStream.of(daysInMonth).skip(start.getMonth()).limit(end.getMonth()-2).sum()
					                               + end.getDay() - 1;			
		} else if (yearDiff == 1) { 
			total += isLeapYear(start.getYear()) ? (daysInMonthLeap[start.getMonth()-1] - start.getDay()) + IntStream.of(daysInMonthLeap).skip(start.getMonth()).sum() 
					                               + IntStream.of(daysInMonthLeap).limit(end.getMonth()-1).sum() + end.getDay() - 1
                                                  : (daysInMonth[start.getMonth()-1] - start.getDay()) + IntStream.of(daysInMonth).skip(start.getMonth()).sum() 
					                               + IntStream.of(daysInMonth).limit(end.getMonth()-1).sum() + end.getDay() - 1;
        } else {
			
			total += isLeapYear(start.getYear()) ? (daysInMonthLeap[start.getMonth()-1] - start.getDay()) 
                                                   + IntStream.of(daysInMonthLeap).skip(start.getMonth()).sum()
                                                 : (daysInMonth[start.getMonth()-1] - start.getDay()) 
                                                 + IntStream.of(daysInMonth).skip(start.getMonth()).sum();
			total += IntStream.range(start.getYear() + 1, end.getYear())
					.map(i -> (isLeapYear(i) ? IntStream.of(daysInMonthLeap).sum() 
			                 : IntStream.of(daysInMonth).sum())).sum();
			total += isLeapYear(end.getYear()) ? IntStream.of(daysInMonthLeap).limit(end.getMonth()-1).sum() + end.getDay()
					                           : IntStream.of(daysInMonthLeap).limit(end.getMonth()-1).sum() + end.getDay();
		}
		
		return total;
	}
}
