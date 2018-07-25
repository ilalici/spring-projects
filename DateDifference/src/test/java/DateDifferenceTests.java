import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;


import com.DateDifference.model.Date;
import com.DateDifference.model.DateUtility;


public class DateDifferenceTests {
	@Test
	public void testDateCalculation() {
		String[] dateParts = "01/03/2012".split("/");
		Date d1 = new Date(dateParts[0],dateParts[1],dateParts[2]);
		dateParts = "04/06/2013".split("/");
		Date d2 = new Date(dateParts[0],dateParts[1],dateParts[2]);
		
		int total = DateUtility.calculateDays(d1,d2);
	
		assertTrue(total == 459);
	}
	
	@Test
	public void testLargeDateCalculation() {
		String[] dateParts = "01/03/2012".split("/");
		Date d1 = new Date(dateParts[0],dateParts[1],dateParts[2]);
		dateParts = "04/06/2015".split("/");
		Date d2 = new Date(dateParts[0],dateParts[1],dateParts[2]);
		
		int total = DateUtility.calculateDays(d1,d2);
		System.out.println(total);
	    assertTrue(total == 1190);
	}
	
	@Test
	public void testSameDateCalculation() {
		String[] dateParts = "01/03/2012".split("/");
		Date d1 = new Date(dateParts[0],dateParts[1],dateParts[2]);
		dateParts = "01/03/2012".split("/");
		Date d2 = new Date(dateParts[0],dateParts[1],dateParts[2]);
		
		int total = DateUtility.calculateDays(d1,d2);
		
		 assertTrue(total == 0);
	}
}