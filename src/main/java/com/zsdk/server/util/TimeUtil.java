package com.zsdk.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhj on 17/3/16.
 */


public class TimeUtil {

    public static long getCurrentTimestamp() {
        return (System.currentTimeMillis() / 1000L);
    }

    public static String getCurrentTimestamp(String timeFormat) {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        return format.format(now);
    }

    public static long getTimeInMilliSec(String datetime,
                                         String timeFormat) {
        long result = 0;
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        try {
            Date date = format.parse(datetime);
            result = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}



