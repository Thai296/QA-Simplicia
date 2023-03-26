package com.simplicia.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimeStamp {
	private Log log;

	public TimeStamp() {
		this.log = LogFactory.getLog(getClass());
	}

	public String getCurrentDate() throws Exception {

		String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		log.info("        Returned Current Date: " + currentDate);
		return currentDate;
	}
	
	public String getCurrentDate(String formatDate) throws Exception {

		String currentDate = new SimpleDateFormat(formatDate).format(Calendar.getInstance().getTime());
		log.info("        Returned Current Date: " + currentDate);
		return currentDate;
	}

	public String getPreviousDate(String format, int days) throws Exception {
		String previousDate = LocalDateTime.now().minusDays(days).format(DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault()));
		log.info("        Returned Previous Date: " + previousDate);
		return previousDate;
	}

	public String getFutureDate(String format, int days) throws Exception {
		String futureDate = LocalDateTime.now().plusDays(days).format(DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault()));
		log.info("        Returned Future Date: " + futureDate);
		return futureDate;
	}

	public String getConvertedDate(String oldFormat, String newFormat, String oldDate) throws Exception {
		Date date = new SimpleDateFormat(oldFormat).parse(oldDate);
		String newDate = new SimpleDateFormat(newFormat).format(date);
		newDate = newDate.toUpperCase();
		log.info("        Returned New Format Date: " + newDate);
		return newDate;
	}

	public String getFirstDateOfMonth(String format, Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String firstDate = new SimpleDateFormat(format).format(cal.getTime());
		log.info("        Returned the First Date of Month: " + firstDate);
		return firstDate;
	}

	public String getLastDateOfMonth(String format, Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		String firstDate = new SimpleDateFormat(format).format(cal.getTime());
		log.info("        Returned the Last Date of Month: " + firstDate);
		return firstDate;
	}

	public String getPreviousMonth(String format, Date date, int numOfMonth) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -numOfMonth);
		String previousDate = new SimpleDateFormat(format).format(cal.getTime());
		log.info("        Returned Previous Month Date: " + previousDate);
		return previousDate;
	}

	public String getNextDay(String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date myDate = dateFormat.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		calendar.add(Calendar.DAY_OF_MONTH, +1);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		String result = dateFormat.format(previousDate);
		return result;
	}

	public String getNextDay(String format, Date date) throws ParseException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);

		// Use the date formatter to produce a formatted date string
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date previousDate = calendar.getTime();
		String result = dateFormat.format(previousDate);
		return result;
	}

	public String getDayOfPrevMonth(String format, Date date, int numOfMonth, int day) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -numOfMonth);
		cal.set(Calendar.DAY_OF_MONTH, day);
		String prevDate = new SimpleDateFormat(format).format(cal.getTime());
		log.info("        Returned the Date: " + prevDate);
		return prevDate;
	}
}
