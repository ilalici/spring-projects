package com.DateDifference.model;

public class Date {
	private Integer month;
	private Integer day;
	private Integer year;
    private Integer totalDate;
 
	public Date(String month, String day, String year){
		String regexLeadingZero = "^0+(?!$)";
		this.month = Integer.valueOf(month.replaceFirst(regexLeadingZero,""));
		this.day = Integer.valueOf(day.replaceFirst(regexLeadingZero,""));
		this.year = Integer.valueOf(year.replaceFirst(regexLeadingZero,""));
		this.totalDate = Integer.valueOf(year+month+day);
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
	
	public Integer getTotalDate() {
		return totalDate;
	}
}
