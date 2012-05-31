/*---------------------------------------------------------------
 * Confidential and Proprietary
 * Copyright 2006 CMO & Hewlett-Packard Development Company, L.P.
 * All Rights Reserved
 *---------------------------------------------------------------
 * Project Name    : MPS
 * Sub Project Name: MPS
 *
 * Class Name      : DateUtil
 * Purpose         : 
 *---------------------------------------------------------------
 * Modification Log
 * Date         Ver. #      Programmer       Description
 *
 * 2006-3-24    1.0          zhangminjin          Initial
 * 2006-11-02    1.2         zhangminjin          Replace Timestamp with Date
 *---------------------------------------------------------------
 */
package com.eh100.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// import com.cmo.mps.exception.ServiceHintException;
/**
 * <p>Date util</p>
 * @version     1.0     <br/>
 * @author      zhangminjin    
 */
public class DateUtil {
    public final static String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    public final static String DEFAULT_DATE_FORMAT_Minute = "yyyy-MM-dd HH:mm";

    public final static String DATE_FORMAT_yyyy__MM = "yyyy/MM";

    public final static String DATE_FORMAT_MMM_yy = "MMM-yy";

    public static final String DATE_FORMAT_yyyyMM = "yyyyMM";

    public static final String DATE_FORMAT_yyyy__MM__dd = "yyyy/MM/dd";

    public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";

    public final static String DATE_FORMAT_yyyy__MM__dd_HH_mm_ss = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_FORMAT_yyyy__MM__dd_mid = "yyy-MM-dd";

    public static List getDisplayMonth(Date time, int monthBefore,
            int monthAfter, SimpleDateFormat format) {
        ArrayList result = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        if (null != time) {
            calendar.setTimeInMillis(time.getTime());
        }
        SimpleDateFormat tmpformat = format;
        if (null == tmpformat) {
            tmpformat = new SimpleDateFormat(DATE_FORMAT_yyyy__MM);
        }
        Calendar calendartmp = Calendar.getInstance();

        for (int i = monthBefore; i <= monthAfter; i++) {
            calendartmp.setTimeInMillis(calendar.getTimeInMillis());
            calendartmp.set(Calendar.DAY_OF_MONTH, 1);
            calendartmp.set(Calendar.HOUR_OF_DAY, 0);
            calendartmp.set(Calendar.MINUTE, 0);
            calendartmp.set(Calendar.SECOND, 0);
            calendartmp.set(Calendar.MILLISECOND, 0);

            calendartmp.add(Calendar.MONTH, i);
            result.add(tmpformat
                    .format(new Date(calendartmp.getTimeInMillis())));
        }
        return result;

    }

    public static List getDisplayMonth(Date from, Date to, String pattern) {
        ArrayList result = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        Calendar calendarTo = Calendar.getInstance();
        calendarTo.setTime(getMonthLastDay(to));
        if (pattern == null) {
            pattern = DATE_FORMAT_yyyy__MM;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,
                Locale.ENGLISH);

        while (calendar.before(calendarTo)) {
            result.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.MONTH, 1);
        }
        return result;

    }

    public static List getDisplayDate(Date from, Date to, String pattern) {
        ArrayList result = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        Calendar calendarTo = Calendar.getInstance();
        calendarTo.setTime(getMonthLastDay(to));
        if (pattern == null) {
            pattern = "yyyyMMdd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,
                Locale.ENGLISH);

        while (calendar.before(calendarTo)) {
            result.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return result;

    }

    public static List getDisplayMonth(Date time, int monthBefore,
            int monthAfter) {
        return getDisplayMonth(time, monthBefore, monthAfter, null);
    }

    public static List getDisplayMonth(int monthBefore, int monthAfter) {
        return getDisplayMonth(null, monthBefore, monthAfter);
    }

    public static List getDisplayMonth(int monthBefore, int monthAfter,
            String format) {
        return getDisplayMonth(null, monthBefore, monthAfter,
                new SimpleDateFormat(format, Locale.ENGLISH));
    }

    /**
     * get months based on time, return array length is monthAfter-monthBefore+1 
     * @param time
     * @param monthBefore
     * @param monthAfter
     * @return Date[]
     */
    public static Date[] getMonth(Date time, int monthBefore, int monthAfter) {
        ArrayList result = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        if (null != time) {
            calendar.setTimeInMillis(time.getTime());
        }
        for (int i = monthBefore; i <= monthAfter; i++) {
            Calendar calendartmp = Calendar.getInstance();
            calendartmp.setTimeInMillis(calendar.getTimeInMillis());
            calendartmp.set(Calendar.DAY_OF_MONTH, 1);
            calendartmp.set(Calendar.HOUR_OF_DAY, 0);
            calendartmp.set(Calendar.MINUTE, 0);
            calendartmp.set(Calendar.SECOND, 0);
            calendartmp.set(Calendar.MILLISECOND, 0);
            calendartmp.add(Calendar.MONTH, i);
            result.add(new Date(calendartmp.getTimeInMillis()));
        }
        return (Date[]) (result.toArray(new Date[monthAfter - monthBefore + 1]));
    }

    /**
    * get months based on startMonth and endMonth, return array  
    * @param time
    * @param monthBefore
    * @param monthAfter
    * @return Date[]
    */
    public static Date[] getMonth(Date startMonth, Date endMonth) {
        ArrayList result = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        if (null != startMonth) {
            calendar.setTimeInMillis(startMonth.getTime());
        }
        if (null != endMonth) {
            calendarEnd.setTimeInMillis(endMonth.getTime());
        }
        while (!calendar.after(calendarEnd)) {
            result.add(new Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.MONTH, 1);

        }
        return (Date[]) (result.toArray(new Date[result.size()]));
    }

    public static Date getNextMonth(Date time) {
        Calendar calendar = Calendar.getInstance();
        if (null != time) {
            calendar.setTimeInMillis(time.getTime());
        }
        calendar.add(Calendar.MONTH, 1);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getNextMonth(Date time, int after) {
        Calendar calendar = Calendar.getInstance();
        if (null != time) {
            calendar.setTimeInMillis(time.getTime());
        }
        calendar.add(Calendar.MONTH, after);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getPreviousMonth(Date time) {
        Calendar calendar = Calendar.getInstance();
        if (null != time) {
            calendar.setTimeInMillis(time.getTime());
        }
        calendar.add(Calendar.MONTH, -1);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date[] getMonth(int monthBefore, int monthAfter) {
        return getMonth(null, monthBefore, monthAfter);
    }

    // public static void main(String[] args) throws ServiceHintException {
    // Date start = DateUtil.parse("2006/01", DateUtil.DATE_FORMAT_yyyy__MM);
    // Date end = DateUtil.parse("2008/01", DateUtil.DATE_FORMAT_yyyy__MM);
    // Date[] dates = DateUtil.getMonth(start, end);
    // }

    public static String getCurrentTimeString() {
        java.util.Date now = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return formatter.format(now);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }

        Format formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return formatter.format(date);
    }

    /**
     * Format date by the format
     * @param date - the source date
     * @param format - the date format
     *
     * @return the result String
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }

        Format formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static boolean monthInRange(Date current, Date before, Date after) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(current);
        Calendar temp = Calendar.getInstance();
        temp.setTime(before);
        temp.set(Calendar.DAY_OF_MONTH, 1);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        if (curr.before(temp)) {
            return false;
        }
        temp.setTime(after);
        temp.add(Calendar.MONTH, 1);
        temp.set(Calendar.DAY_OF_MONTH, 1);
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        if (curr.after(temp)) {
            return false;
        }
        return true;
    }

    public static boolean monthEquals(Date current, Date compare) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(current);
        Calendar cmp = Calendar.getInstance();
        cmp.setTime(compare);
        int year1 = curr.get(Calendar.YEAR);
        int year2 = cmp.get(Calendar.YEAR);
        int month1 = curr.get(Calendar.MONTH);
        int month2 = cmp.get(Calendar.MONTH);
        return (year1 == year2) && (month1 == month2);
    }

    public static String getMonthRate(Date date, boolean moveIn) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int daysOfThisMonth = calendar.getActualMaximum(Calendar.DATE);
        double rate;
        if (moveIn) {
            rate = (double) (daysOfThisMonth - day + 1) / daysOfThisMonth;
        } else {
            rate = (double) day / daysOfThisMonth;
        }
        rate = (double) Math.round(rate * 10) / 10;
        return String.valueOf(rate);
    }

    public static String getMonthRate(Date dateIn, Date DateOut) {
        Calendar calIn = Calendar.getInstance();
        calIn.setTime(dateIn);
        Calendar calOut = Calendar.getInstance();
        calOut.setTime(DateOut);
        int day = calOut.get(Calendar.DAY_OF_MONTH)
                - calIn.get(Calendar.DAY_OF_MONTH) + 1;
        int daysOfThisMonth = calIn.getActualMaximum(Calendar.DATE);
        double rate = (double) day / daysOfThisMonth;
        rate = (double) Math.round(rate * 10) / 10;
        return String.valueOf(rate);
    }

    public static Date parse(String dateString, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateString, new ParsePosition(0));
    }

    public static int compareDate(Date first, Date second) {
        Calendar cf = Calendar.getInstance();
        cf.setTime(first);
        Calendar cs = Calendar.getInstance();
        cs.setTime(second);
        if (cf.after(cs)) {
            return 1;
        } else if (cf.before(cs)) {
            return -1;
        } else {
            return 0;
        }
    }

    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getPreviousDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @param string
     * @return
     */
    public static String getCurrentTimeString(String pattern) {
        java.util.Date now = Calendar.getInstance().getTime();
        Format formatter = new SimpleDateFormat(pattern);
        return formatter.format(now);
    }

    /**
    						     * @param startMonth
    						     * @param i
    						     * @return
    						     */
    public static Date getMonthByOffset(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        calendar.add(Calendar.MONTH, offset);
        return calendar.getTime();
    }

    public static long getDateOffset(Date beginDate, Date endDate) {
        long beginTime = beginDate.getTime();
        long endTime = endDate.getTime();
        long betweenDays = (long) ((endTime - beginTime)
                / (1000 * 60 * 60 * 24) + 0.5);
        return Math.abs(betweenDays);
    }

    public static Date getDateByOffset(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            calendar.setTime(date);
            // calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     * @param targetMonth
     * @param newTargetMonth
     * @return
     */
    public static int compareMonth(Date first, Date second) {
        Calendar cf = Calendar.getInstance();
        cf.setTime(first);
        Calendar cs = Calendar.getInstance();
        cs.setTime(second);
        int offset = (cs.get(Calendar.YEAR) - cf.get(Calendar.YEAR)) * 12
                + (cs.get(Calendar.MONTH) - cf.get(Calendar.MONTH));
        return offset;
    }

    /**
     * @param currDate
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DATE);
    }

    /**
     * @param mpDate
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param monthSeq
     * @return
     */
    public static int getDaysOfMonth(Date startMonth, Integer monthSeq) {
        Date month = getMonthByOffset(startMonth, monthSeq.intValue() - 1);
        return getDaysOfMonth(month);
        // return 30;
    }

    static public void main(String[] args) {
        List list = DateUtil.getDisplayMonth(DateUtil
                .getMonthFirstDay(new Date()), DateUtil.getNextMonth(DateUtil
                .getNextMonth(new Date()), 3), DateUtil.DATE_FORMAT_yyyyMM);

        System.out.print("list " + list);

    }
}
