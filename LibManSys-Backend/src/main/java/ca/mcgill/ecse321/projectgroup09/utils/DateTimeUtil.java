package ca.mcgill.ecse321.projectgroup09.utils;

import java.sql.Date;
import java.util.Calendar;

/**
 * Util methods for dealing with dates and times.
 * Important Note: Only use java.sql.Date, not java.util.Date for compatability
 * with the rest of the LibManSys project.
 */
public class DateTimeUtil {

	
	/**
	 * Given a date, add 'numDays' number of days to it and return the new date.
	 * @param date input date
	 * @param numDays number of days to add the input date.
	 * @return input date with 'numDays' number of days added to it.
	 */
	public static Date addDaysToDate(Date date, int numDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, numDays);
		Date newDate = new Date(cal.getTimeInMillis());
		return newDate;
	}
}
