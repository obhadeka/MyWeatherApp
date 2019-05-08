package com.exmaple.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter {
	private final static String INVALIDDATE = "9999-12-31";
	private static final DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyy-MMM-dd");
	
	private static final DateTimeZone utcTZ = DateTimeZone.forID("UTC");
	private static final DateTimeZone estTZ = DateTimeZone.forID("America/New_York");
    
	public static Date convertToDateTime (String dateTimeString) {
		String datePattern = "(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])";
		if (dateTimeString != null) {
			String dtTmString = dateTimeString.replace("T", " ");
			
			// 16 is the format for 1999-11-11-8:00, it is JAXB format for the
			// date.
			if (dtTmString.length() >= 10) {
				// To get 1999-11-11 to validate the date format.
				String dateString = dtTmString.substring(0, 10);
				boolean isMatch = dateString.matches(datePattern);
				if (isMatch) {
					return dateTimeFormat.parseDateTime(dtTmString).toDate();
				}
				else {
					return dateTimeFormat.parseDateTime(INVALIDDATE).toDate();
				}
		    }
		    else {
		    	return dateTimeFormat.parseDateTime(INVALIDDATE).toDate();
		    }
		}
		else {
		    return null;
		}
	}

	public static String convertToDateTimeString(Date date) {
		if(date == null) {
			return null;
		}
	
		String dateString = dateTimeFormat.print(new DateTime(date));
		dateString = dateString.replace(" ", "T");
		return dateString;
	}
	
	public static String convertToDateString(Date date) {
		if(date == null) {
			return null;
		}
		
		String dateString = dateFormat.print(new DateTime(date));
		dateString = dateString.replace(" ", "T");
		return dateString;
	}

	public static String convertToDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		return String.valueOf(dayOfWeek);
	}
	
    public static Date convertTime (Date time, DateTimeZone from, DateTimeZone to) {
	final long timeMillis = time.getTime();
	return new Date(timeMillis - from.getOffset(timeMillis) + to.getOffset(timeMillis));
    }
    
    public static Date convertToEasternTime (String dateString) {
	return convertTime( dateTimeFormat.parseDateTime(dateString).toDate(), utcTZ , estTZ);
    }
}
