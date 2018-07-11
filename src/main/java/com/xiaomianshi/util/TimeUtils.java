package com.xiaomianshi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author zhen.yu
 * @since 2017/5/24
 */
public class TimeUtils {
    private final static long MINUTE = 60 * 1000; // 1分钟
    private final static long HOUR = 60 * MINUTE; // 1小时
    private final static long DAY = 24 * HOUR;    // 1天
    private final static long MONTH = 31 * DAY;   // 月
    private final static long YEAR = 12 * MONTH;  // 年

    private static final DateFormat GMT_NORMAL_FORMAT;
    private static final DateFormat LOCAL_NORMAL_FORMAT;
    static {
        GMT_NORMAL_FORMAT = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        GMT_NORMAL_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
        LOCAL_NORMAL_FORMAT = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    }

    public static long currentTime() {
        return System.currentTimeMillis();
    }

    public static Date gmtNow() {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        return cal.getTime();
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite. Relying on specific times is problematic.
     *
     * @param early the "first date"
     * @param late the "second date"
     * @return the days between the two dates
     */
    public static int daysBetween(Date early, Date late) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);
        return daysBetween(c1, c2);
    }

    /**
     * Returns the days between two dates. Positive values indicate that
     * the second date is after the first, and negative values indicate, well,
     * the opposite.
     *
     * @param early
     * @param late
     * @return the days between two dates.
     */
    public static int daysBetween(Calendar early, Calendar late) {
        return (int) (toJulian(late) - toJulian(early));
    }

    /**
     * Return a Julian date based on the input parameter. This is
     * based from calculations found at
     * <a href="http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html">Julian Day Calculations
     * (Gregorian Calendar)</a>, provided by Bill Jeffrys.
     * @param c a calendar instance
     * @return the julian day number
     */
    public static float toJulian(Calendar c) {
        int Y = c.get(Calendar.YEAR);
        int M = c.get(Calendar.MONTH);
        int D = c.get(Calendar.DATE);
        int A = Y / 100;
        int B = A / 4;
        int C = 2 - A + B;
        float E = (int) (365.25f * (Y + 4716));
        float F = (int) (30.6001f * (M + 1));
        return C + D + E + F - 1524.5f;
    }

    public static Date dateIncreaseByDay(Date date, int days) {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static Date dateIncreaseByMonth(Date date, int mnt) {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.MONTH, mnt);
        return cal.getTime();
    }

    public static Date dateIncreaseByYear(Date date, int mnt) {
        Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);
        cal.add(Calendar.YEAR, mnt);
        return cal.getTime();
    }

}
