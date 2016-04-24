package application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

/**
 * A few utils for working with Date.
 */
// TODO Make this a CDI singleton?
public class DateUtil {

    private DateUtil() {
    }

    public static Date toDate(String date) {
        return toDate(date, "00:00.00.000");
    }

    public static Date toDate(String date, String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date + " " + time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Date TimeToDate(String time) {
        try {
            return new SimpleDateFormat("HH:mm").parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDateFromDateTime(String dateTime) {
        //03/15/2014 12:00 AM CET
        return dateTime.substring(0, dateTime.indexOf(" "));
    }

    public static String getTimeFromDateTime(String dateTime) {
        //03/15/2014 12:00 AM CET
        return dateTime.substring(dateTime.indexOf(" ") + 1);
    }

    // compute number of days between today and endDate (both set at midnight)
    public static long computeDuration(Date endDate) {
        //SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date today = trim(new Date()); // from today
        long diff = endDate.getTime() - today.getTime();
        return (diff / (24 * 60 * 60 * 1000)); // in days
    }

    public static Date trim(Date date) { // set time at midnight since we don't deal with time in the day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }
    
    public static Date getTimeOnly(Date date) { 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(1900, Calendar.JANUARY, 1);
        return calendar.getTime();
    }
    
    public static Date combine(Date date, Date time) { 
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(time);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

        return calendar.getTime();
    }
    
    public static String combineFormat(Date date, Date time) {
        Date newDate = combine(date, time);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(newDate);
    }
    
    public static String toYearMonthString(Date date) {
        return new SimpleDateFormat("MMyy").format(date);
    }
    
    public static Date toYearMonthDate(String date) throws ParseException {
        return new SimpleDateFormat("MMyy").parse(date);
    }
    
    public static Date getNewDate(Date sourceDate, DayOfWeek dayOfWeek) { 

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        int days = dayOfWeek.getValue() - toDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)).getValue();
        if (days < 0) {
            days = 7 - days;
        }
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    
    public static DayOfWeek toDayOfWeek(int dayOfWeek) {
        if (dayOfWeek == Calendar.MONDAY)
            return DayOfWeek.MONDAY;
        if (dayOfWeek == Calendar.TUESDAY)
            return DayOfWeek.TUESDAY;
        if (dayOfWeek == Calendar.WEDNESDAY)
            return DayOfWeek.WEDNESDAY;
        if (dayOfWeek == Calendar.THURSDAY)
            return DayOfWeek.THURSDAY;
        if (dayOfWeek == Calendar.FRIDAY)
            return DayOfWeek.FRIDAY;
        if (dayOfWeek == Calendar.SATURDAY)
            return DayOfWeek.SATURDAY;
        if (dayOfWeek == Calendar.SUNDAY)
            return DayOfWeek.SUNDAY;
        return DayOfWeek.SUNDAY;
    }
    
}
