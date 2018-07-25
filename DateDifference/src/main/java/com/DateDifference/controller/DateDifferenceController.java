package com.DateDifference.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.DateDifference.model.Date;
import com.DateDifference.model.DateUtility;

@Controller
public class DateDifferenceController {
 
	private static final Logger logger = LogManager.getLogger();
	
	@RequestMapping(value="/")
	public ModelAndView showHomePage() {
		ModelAndView mv = new ModelAndView("DateDifference");
		return mv;
	}
	
	@RequestMapping(value="/Calculate",method = RequestMethod.POST)
	public ModelAndView calculate(@RequestParam(value = "date1") String date1,@RequestParam(value = "date2") String date2) {
		
		ModelAndView mv = new ModelAndView("DateDifference");
		Map<String,String> formMap = new HashMap<String,String>();
		
		//Validate input		
		String regex = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(date1),m2 = p.matcher(date2);
		Date d1 = null, d2 = null;
		
		formMap.put("date1", date1);
		formMap.put("date2", date2);

		if(!m.matches() || !m2.matches()) {
			formMap.put("InvalidDate", "You have entered an invalid date. Please try again.");
		} else {
			try {
				String[] dateParts = date1.split("/");
				d1 = new Date(dateParts[0],dateParts[1],dateParts[2]);
				dateParts = date2.split("/");
				d2 = new Date(dateParts[0],dateParts[1],dateParts[2]);			
			} catch (NumberFormatException ex) {
				logger.error(ex.getMessage());
			}
			
			//Calculate					
			int total = DateUtility.calculateDays(d1, d2);
			
			StringBuilder sb = new StringBuilder();
			sb.append("The number of days between ");
			sb.append(DateUtility.getMonthName(d1.getMonth()));
			sb.append(" ");
			sb.append(DateUtility.ordinal(d1.getDay()));
			sb.append(" ");
			sb.append(d1.getYear());
			sb.append(" and ");
			sb.append(DateUtility.getMonthName(d2.getMonth()));			
			sb.append(" ");
			sb.append(DateUtility.ordinal(d2.getDay()));
			sb.append(" ");
			sb.append(d2.getYear());
			sb.append(" is ");
			sb.append(total);
			sb.append(" day(s)");
			mv.addObject("totalDaysMessage",sb.toString());
		}
		
		mv.addAllObjects(formMap);
		
		return mv;
	}
}