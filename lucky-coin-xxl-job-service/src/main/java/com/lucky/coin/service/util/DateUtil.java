package com.lucky.coin.service.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author yyh
 * @date 2020/3/11
 */
public class DateUtil {
    public static String dayFormat = "yyyyMMdd";
    public static String hourFormat = "yyyyMMddHH";
    public static String minuteFormat = "yyyyMMddHHmm";
    public static String targetFormat = "yyyy-MM-dd HH:mm:ss";
    static TimeZone tz = TimeZone.getTimeZone("UTC");

    public static Date getDayDate(Long day) {
        try {
            Date date = DateUtils.parseDate(String.valueOf(day), dayFormat);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long toDayDate(Long day) {
        try {
            Date date = DateUtils.parseDate(String.valueOf(day), dayFormat);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static Long getBeforeDay(Long day, int days) {
        try {
            Date date = DateUtils.parseDate(String.valueOf(day), dayFormat);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
            String dateStr = DateFormatUtils.format(calendar.getTime(), dayFormat);

            return Long.valueOf(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static Long getAfterDay(Long day, int days) {
        try {
            Date date = DateUtils.parseDate(String.valueOf(day), dayFormat);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
            String dateStr = DateFormatUtils.format(calendar.getTime(), dayFormat);

            return Long.valueOf(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }


    public static Long getBeforeHour(Long hourDay, int hour) {
        try {
            Date date = DateUtils.parseDate(String.valueOf(hourDay), hourFormat);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
            String dateStr = DateFormatUtils.format(calendar.getTime(), hourFormat);

            return Long.valueOf(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static String getDayDate() {
        try {
            String dateStr = DateFormatUtils.format(new Date(), dayFormat);
            return dateStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDayDate(String format) {
        try {
            String dateStr = DateFormatUtils.format(new Date(), format);
            return dateStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDayDate(Date date) {
        try {
            String dateStr = DateFormatUtils.format(date, dayFormat);
            return dateStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static Long toHourDate(Long hour) {
        try {
            Date date = DateUtils.parseDate(String.valueOf(hour), hourFormat);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static Long getMinuteDate() {
        try {
            String dateStr = DateFormatUtils.format(new Date(), minuteFormat);
            return Long.valueOf(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static Long getMinuteDate(Date date) {
        try {
            String dateStr = DateFormatUtils.format(date, minuteFormat);
            return Long.valueOf(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static String getHourDate() {
        try {
            String dateStr = DateFormatUtils.format(new Date(), hourFormat);
            return dateStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param dateStr "2020-03-18 13:15:00"
     * @return 20200318
     */
    public static Long getDayDateByFormatDateStr(String dateStr) {
        try {
            String dayStr = DateFormatUtils.format(DateUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss"), dayFormat);
            return Long.valueOf(dayStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 获取时间A与B之间的分钟差
     *
     * @param dateA
     * @param dateB
     * @return
     */
    public static long betweenMinute(final Date dateA, final Date dateB) {
        long between = dateB.getTime() - dateA.getTime();
        return between / (60 * 1000);
    }


    public static Date getDateBeforeDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateBeforeHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateBeforeMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static long getMinerHourBefore(int hour) {
        Date date = getDateBeforeHour(hour);
        String minerHourStr = DateFormatUtils.format(date, hourFormat);
        return Long.parseLong(minerHourStr);
    }

    public static Integer getNowHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.HOUR_OF_DAY);
    }

    public static Integer getNowMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.MINUTE);
    }

    public static long getMinerDayBefore(int day) {
        Date date = getDateBeforeDay(day);
        String minerHourStr = DateFormatUtils.format(date, dayFormat);
        return Long.parseLong(minerHourStr);
    }


    public static Date secondToDate(Long second) {
        if (second == null) {
            return null;
        }
        return new Date(second * 1000);
    }

    public static String getISO8601TimeStr(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    public static Date getISO8601TimeDate(String str) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            df.setTimeZone(tz);
            Date date = df.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateStr(Date date) {
        try {
            DateFormat df = new SimpleDateFormat(targetFormat);
            df.setTimeZone(tz);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回上一个最近的一刻钟
     */
    public static Long getLast15min(Date date) {
        Date last15min = new Date(900000 * ((date.getTime()) / 900000));
        Long minute = getMinuteDate(last15min);
        return minute;
    }

    public static void main(String[] args) throws Exception {
        Long day = getAfterDay(20210331L, 1);
        System.out.println(day);
    }
}
