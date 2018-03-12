package com.csf.cloud.common;

import com.aug3.sys.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by junmeng.xu on 2016/7/29.
 */
public class DateUtils {

    private static Logger log = Logger.getLogger(DateUtils.class);

    public static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    private static ThreadLocal<SimpleDateFormat> iso_dateformat_threadlocal = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_DATE_PATTERN);
        }
    };

    /**
     * 获取给定日期前后几天日期
     *
     * @param date
     *            给定日期
     * @param days
     *            日数，正数是日期往后推，负数是日期往前推
     * @return
     */
    public static Date getPreOrNextDate(Date date, int days) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date getCNNow() {
        return getNow(8);
    }

    public static Date getNow(int timeZoneOffset) {
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        TimeZone defaultZone = TimeZone.getDefault();
        long diff = 0 - defaultZone.getRawOffset();
        long rawOffset = getRawOffset(timeZoneOffset);
        long currentTimeMillis = System.currentTimeMillis();
        return new Date(currentTimeMillis + diff + rawOffset);
    }

    public static long getRawOffset(int timeZoneOffset) {
        return timeZoneOffset * 60 * 60 * 1000;
    }

    /**
     * 获取今天日期，不带时分秒
     *
     * @return 系统当前日期
     */
    public static Date getToday() {
        return parseDate(formatCurrentDate());
    }

    public static Date parseDate(String s) {
        if(StringUtils.isBlank(s)) {
            return null;
        }
        try {
            return DateUtil.parseDate(s);
        } catch (ParseException e) {
            log.warn(e, e);
        }
        return null;
    }

    public static String formatCurrentDate() {
        return getISODateFormat().format(new Date());
    }

    public static SimpleDateFormat getISODateFormat() {
        return iso_dateformat_threadlocal.get();
    }


}
