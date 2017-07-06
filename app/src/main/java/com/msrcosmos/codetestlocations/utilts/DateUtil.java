package com.msrcosmos.codetestlocations.utilts;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    public static Date dateFromString(String dateString )  {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return dateFormat.parse(dateString);


        }
        catch (Exception exception) {
            return null;

        }

    }
    public static Date dateFromString(String dateString, String format )  {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateString);


        }
        catch (Exception exception) {
            return null;

        }

    }
    public static String getDateStringFromDate(Date date, String format )  {
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);


        }
        catch (Exception exception) {
            return null;

        }

    }
    public static Date getEarlyToday() {
        try
        {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = dateFormat.format(now);
            Date earlyToday = timeFormat.parse(dateString+" 00:00:00");
            return earlyToday;

        }
        catch (Exception exception) {
            return null;
        }

    }
    public static String hhmmss(int seconds) {
        if (seconds < 0)
        {
            return "";
        }
        int hr = seconds/3600;
        int rem = seconds%3600;
        int mn = rem/60;
        int sec = rem%60;
        String hrStr = (hr<10 ? "0" : "")+hr;
        String mnStr = (mn<10 ? "0" : "")+mn;
        String secStr = (sec<10 ? "0" : "")+sec;
        return hrStr + ":" + mnStr + ":" + secStr;
    }
    public static String hhmm(int seconds) {
        if (seconds < 0)
        {
            return "";
        }
        int hr = seconds/3600;
        int rem = seconds%3600;
        int mn = rem/60;
        int sec = rem%60;
        String hrStr = (hr<10 ? "0" : "")+hr;
        String mnStr = (mn<10 ? "0" : "")+mn;
        return hrStr + "h " + mnStr + "m" ;
    }
}
