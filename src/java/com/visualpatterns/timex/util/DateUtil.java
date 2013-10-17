package com.visualpatterns.timex.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Date utility class.
 * 
 * @author Anil Hemrajani
 */
public class DateUtil {

	/**
	 * Returns date for Sunday of current week.
	 */
	public static Date getCurrentPeriodEndingDate() {
		// loop forward till we find SUNDAY
		GregorianCalendar gc = new GregorianCalendar();
		while (gc.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY)
			gc.add(GregorianCalendar.DATE, 1);
		return gc.getTime();
	}

	/**
	 * Returns date for Monday of current week.
	 */
	public static Date getCurrentPeriodStartingDate() {
		// loop backward till we find MONDAY
		GregorianCalendar gc = new GregorianCalendar();
		while (gc.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.MONDAY)
			gc.add(GregorianCalendar.DATE, -1);
		return gc.getTime();
	}

	/**
	 * Returns list of Dates from current period ending date, going backwords.
	 * 
	 * @param entries
	 *            How many elements in list
	 */
	public static List<Date> getPeriodRange(int entries) {
		Date date = getCurrentPeriodEndingDate();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		List<Date> periods = new ArrayList<Date>(entries);
		periods.add(getCurrentPeriodEndingDate());
		for (int i = 1; i < entries; ++i) {
			gc.roll(Calendar.DAY_OF_YEAR, -7);
			periods.add(gc.getTime());
		}
		return periods;
	}

	/**
	 * Returns date parameter with time components zeroed out.
	 */
	public static Date getDateWithZeroTime(Date date) {
		Calendar modifiedDate = new GregorianCalendar();
		modifiedDate.setTime(date);
		modifiedDate.set(Calendar.HOUR_OF_DAY, 0);
		modifiedDate.set(Calendar.MINUTE, 0);
		modifiedDate.set(Calendar.SECOND, 0);
		modifiedDate.set(Calendar.MILLISECOND, 0);
		return modifiedDate.getTime();
	}

	/**
	 * Returns date parameter with time components set to maximum values for day
	 * (that is, 11:59:59.999).
	 */
	public static Date getDateWithMaxTime(Date date) {
		Calendar modifiedDate = new GregorianCalendar();
		modifiedDate.setTime(date);
		modifiedDate.set(Calendar.HOUR_OF_DAY, 23);
		modifiedDate.set(Calendar.MINUTE, 59);
		modifiedDate.set(Calendar.SECOND, 59);
		modifiedDate.set(Calendar.MILLISECOND, 999);
		return modifiedDate.getTime();
	}

	/**
	 * Determines if checkDate falls in current week (from current Monday till
	 * Sunday).
	 */
	public static boolean isInCurrentPayPeriod(Date checkDate) {
		Date weekStartDate = getDateWithZeroTime(getCurrentPeriodStartingDate());
		Date weekEndDate = getDateWithMaxTime(getCurrentPeriodEndingDate());
		return (!checkDate.before(weekStartDate) && !checkDate
				.after(weekEndDate));
	}

}
