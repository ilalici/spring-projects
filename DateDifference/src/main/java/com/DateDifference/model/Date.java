package com.DateDifference.model;

public class Date {
	private Integer month;
	private Integer day;
	private Integer year;
 
	public Date(String month, String day, String year){
		String regexLeadingZero = "^0+(?!$)";
		this.month = Integer.valueOf(month.replaceFirst(regexLeadingZero,""));
		this.day = Integer.valueOf(day.replaceFirst(regexLeadingZero,""));
		this.year = Integer.valueOf(year.replaceFirst(regexLeadingZero,""));
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getDay() {
		return day;
	}

	public Integer getYear() {
		return year;
	}
}
