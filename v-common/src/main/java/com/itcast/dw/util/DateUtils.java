/**
 * Copyright © 2017 Company Name. All rights reserved.
 * 
 * @Title: DateUtils.java
 * @Prject: party-app-image-server
 * @Package: com.xdao.party.app.commons
 * @Description: TODO
 * @author: WangPengHua
 * @date: 2017年6月14日 下午4:35:12
 * @version: V1.0
 */
package com.itcast.dw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: DateUtils
 * @Description: TODO
 * @author: WangPengHua
 * @date: 2017年6月14日 下午4:35:12
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

	public static Date string2Date(String dateStr, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date string2Date(String dateStr) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static String date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 获取两个日期之间的实际天数，支持跨年
	 */
	public static int getDaysBetween(Calendar start, Calendar end) {
		int days = 0;
		if (start.before(end)) {
			days = end.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR);
			int y2 = end.get(Calendar.YEAR);
			if (start.get(Calendar.YEAR) != y2) {
				start = (Calendar) start.clone();
				do {
					days += start.getActualMaximum(Calendar.DAY_OF_YEAR);
					start.add(Calendar.YEAR, 1);
				} while (start.get(Calendar.YEAR) != y2);
			}
		}
		return days;
	}

	/**
	 * 获取两个日期之间的实际天数，支持跨年
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(Date start, Date end) {
		return getDaysBetween(dateToCalendar(start), dateToCalendar(end));
	}

	/**
	 * 获取两个日期之间的实际天数，支持跨年
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDaysBetween(Long start, Date end) {
		return getDaysBetween(dateToCalendar(longToDate(start)), dateToCalendar(end));
	}

	public static int getDaysBetween(Date end, Long start) {
		return getDaysBetween(dateToCalendar(end), dateToCalendar(longToDate(start)));
	}

	// 返回2个时间之间的秒数
	public static long getMinBetween(Date start, Date end) {
		return (start.getTime() - end.getTime()) / 1000;
	}

	/**
	 * date转Calendar
	 * 
	 * @param str
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 得到指定时间beforeDays天前的时间
	 * 
	 * @param beforeDays
	 * @return
	 */
	public static Date getSevenAgoDate(int beforeDays, Date inputDate) {

		if (inputDate == null) {
			inputDate = getNow();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);

		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - beforeDays);

		return cal.getTime();
	}

	public static Date getSevenAgoMonth(int beforeDays, Date inputDate) {

		if (inputDate == null) {
			inputDate = getNow();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - beforeDays);
		return cal.getTime();
	}

	public static Date getSevenAgoYear(int beforeDays, Date inputDate) {

		if (inputDate == null) {
			inputDate = getNow();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);

		int inputDayOfYear = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, inputDayOfYear - beforeDays);

		return cal.getTime();
	}

	/**
	 * 得到当前时间beforeDays天前的时间
	 * 
	 * @param beforeDays
	 * @return
	 */
	public static Date getSevenAgoDate(int beforeDays) {
		return getSevenAgoDate(beforeDays, null);
	}

	/**
	 * 获取指定小时前的时间
	 * 
	 * @param beforehours
	 * @param inputDate
	 * @return
	 */
	public static Date getSevenAgoHour(int beforehours, Date inputDate) {
		if (inputDate == null) {
			inputDate = getNow();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);

		int inputDayOfYear = cal.get(Calendar.HOUR);
		cal.set(Calendar.HOUR, inputDayOfYear - beforehours);
		// System.out.println(DateToStr(cal.getTime(), "yyyy-MM-dd hh:mm:ss"));
		return cal.getTime();
	}

	/**
	 * 获取指定小时后的时间
	 * 
	 * @param afterhours
	 * @param inputDate
	 * @return
	 */
	public static Date getSevenAnonHour(double afterhours, Date inputDate) {
		if (inputDate == null) {
			inputDate = getNow();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);

		int inputDayOfYear = cal.get(Calendar.HOUR);
		int hour = (int) afterhours;
		cal.set(Calendar.HOUR, inputDayOfYear + hour);
		// System.out.println(DateToStr(cal.getTime(), "yyyy-MM-dd hh:mm:ss"));

		String str = "0." + String.valueOf(afterhours).replaceAll("\\d+\\.", "");
		int inputMinuteOfYear = cal.get(Calendar.MINUTE);
		int minute = (int) (Double.parseDouble(str) * 60);
		cal.set(Calendar.MINUTE, inputMinuteOfYear + minute);

		return cal.getTime();
	}

	/**
	 * 获取指定小时后的时间
	 * 
	 * @param afterhours
	 * @param inputDate
	 * @return
	 */
	public static Date getSevenAnonHour(int afterhours, Date inputDate) {
		if (inputDate == null) {
			inputDate = getNow();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);

		int inputDayOfYear = cal.get(Calendar.HOUR);
		cal.set(Calendar.HOUR, inputDayOfYear + afterhours);
		// System.out.println(DateToStr(cal.getTime(), "yyyy-MM-dd hh:mm:ss"));
		return cal.getTime();
	}

	/**
	 * 
	 * Description : 字符串日期转换成Date
	 * 
	 * @param sStr
	 * @return
	 * 
	 */
	public static Date strToDate(String sStr) {
		if (sStr == null)
			return null;
		SimpleDateFormat formatter;
		if (sStr.length() == 28) {
			formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
			Date date;
			try {
				date = formatter.parse(sStr);
				DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return formatTo.parse(formatTo.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (sStr.length() == 19) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if (sStr.length() == 10) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} else if (sStr.length() == 8) {
			formatter = new SimpleDateFormat("yyyyMMdd");
		} else if (sStr.length() == 23) {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		} else if (sStr.length() == 14) {
			formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		} else {
			formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		}
		ParsePosition pos = new ParsePosition(0);
		return formatter.parse(sStr, pos);
	}

	public static Date strToDate(String sStr, String da) {
		if (sStr == null)
			return null;
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(da, Locale.UK);
		ParsePosition pos = new ParsePosition(0);
		// formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return formatter.parse(sStr, pos);
	}

	/**
	 * 毫秒转换为字符串日期
	 * 
	 * @param m
	 * @return
	 */
	public static String longToStrDate(String m) {
		return longToStrDate(m, null);
	}

	public static String longToStrDate(String m, String sdt) {
		if (StringUtils.isEmpty(sdt)) {
			sdt = "yyyy-MM-dd hh:mm:ss";
		}
		DateFormat formatter = new SimpleDateFormat(sdt);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(m));
		return formatter.format(calendar.getTime());
	}

	// 字符串日期转毫秒
	public static Long StrToLongDate(String m) {
		Date strToDate = strToDate(m);
		return strToDate.getTime();
	}

	/**
	 * 
	 * Description : 获取当前系统时间
	 * 
	 * @param fromFormat
	 *            : yyyy-MM-dd HH:mm:ss
	 * @return
	 * 
	 */
	public static String getNowTime(String fromFormatStr, Date date) {
		String now = "";
		if (fromFormatStr == null || "".equals(fromFormatStr)) {
			return "";
		}
		Pattern pattern = Pattern
				.compile("^(y{4})(-{0,1})(MM)(-{0,1})(dd)( {0,1})(H{0,2})(:{0,1})(m{0,2})(:{0,1})(s{0,2})$");
		Matcher match = pattern.matcher(fromFormatStr);
		if (match.find()) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromFormatStr);
			// simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			if (date == null) {
				date = new Date();
			}
			now = simpleDateFormat.format(date);
		}
		return now;
	}

	public static String getNowTime(String fromFormatStr) {

		return getNowTime(fromFormatStr, null);
	}

	public static String getNow(String format) {
		String now = "";
		if (format == null || "".equals(format)) {
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		now = simpleDateFormat.format(date);
		return now;
	}

	/**
	 * 得到指定日期的第一天
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay(Calendar c) {
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 得到指定日期的最后一天
	 * 
	 * @return
	 */
	public static Date getMonthLastDay(Calendar c) {
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/**
	 * 得到指定日期的第一天
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return getMonthFirstDay(c);
	}

	/**
	 * 得到指定日期的最后一天(月)
	 * 
	 * @return
	 */
	public static Date getMonthLastDay(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return getMonthLastDay(c);
	}

	/**
	 * 
	 * Description : Calendar转换成时间字符串
	 * 
	 * @param calendar
	 * @return
	 * 
	 */
	public static String getTimeByCalendar(Calendar calendar, String formatStr) {
		if (formatStr == null || "".equals(formatStr)) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
		String time = simpleDateFormat.format(calendar.getTime());
		return time;
	}

	public static String DateToStr(Date date, String formatStr) {
		String time = "";
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
			time = simpleDateFormat.format(date);
		}
		return time;
	}

	public static boolean checkTimeBetween(Date date, Date start, Date end) {
		Date s = start;
		Date e = end;
		if (start.getTime() > end.getTime()) {
			s = end;
			e = start;
		}
		if (date.getTime() < s.getTime() || date.getTime() > e.getTime())
			return false;
		return true;
	}

	/**
	 * 根据日期获得此日期是星期几 列: 周四==5
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return intWeek;
	}

	/**
	 * 获取指定时间(未指定时间取当前时间) 的周是 指定时间的那一年的第几周 列 :2016-10-27 是2016 年的第44周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		int i = c.get(Calendar.WEEK_OF_YEAR);
		return i;
	}

	/**
	 * 字符转换成 java.sql.Date
	 */
	public static java.sql.Date getSqlDate(String str) {
		java.sql.Date d = new java.sql.Date(strToDate(str).getTime());
		return d;
	}

	/**
	 * 字符转换成 java.sql.Timestamp
	 * 
	 * @param str
	 * @return
	 */
	public static java.sql.Timestamp getTimestamp(String str) {
		java.sql.Timestamp d = new java.sql.Timestamp(strToDate(str).getTime());
		return d;
	}

	public static int getSecondBetween(Date date1, Date date2) {
		long before = date1.getTime();
		long after = date2.getTime();
		long between = (after - before) / 1000;
		return (int) between;
	}

	public static String getTimeToStr(Long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return DateToStr(cal.getTime(), "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * 获取昨天的年月日字符串 格式 yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDateToStr() {
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		return DateToStr(date.getTime(), "yyyyMMdd");
	}

	/**
	 * 获取传递的日期年月日字符串 格式 yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDateToStr(Date d) {
		if (d == null)
			d = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(d);
		return DateToStr(date.getTime(), "yyyyMMdd");
	}

	/**
	 * 获取今天的年月日字符串 格式 yyyyMMdd
	 * 
	 * @return
	 */
	public static String getTodayDateToStr() {
		Date beginDate = new Date();
		return DateToStr(beginDate, "yyyyMMdd");
	}

	/**
	 * 取得Date型的当前日期
	 *
	 * @return
	 */

	public static Date getNow() {
		return new Date();
	}

	public static Date addTimeSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 
	 * Description : 字符串日期转换成Date
	 * 
	 * @param sStr
	 * @return
	 * 
	 */
	public static boolean checkDateForm(String dateStr, String formatStr) {
		if (StringUtils.isBlank(dateStr))
			return false;
		if (StringUtils.isBlank(formatStr))
			return false;
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			formatter.setLenient(false);
			Date date = formatter.parse(dateStr);
			System.out.println(date.toLocaleString());
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Date longToDate(long time) {
		return new Date(time);
	}

	/**
	 * 获取当前时间到今晚12点的总秒数
	 * 
	 * @return
	 */
	public static Long getToNightSS() {
		Calendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		long minus = currentDate.getTimeInMillis() - System.currentTimeMillis();
		return minus / 1000;
	}

	/**
	 * 获取指定时间对应的季度
	 * 
	 * @param date
	 *            指定的时间
	 * @return
	 */
	public static int getSeason(Date date) {
		if (date == null) {
			date = new Date();
		}
		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	// public static void main(String[] args) {
	// System.out.println(DateUtils.StrToLongDate("2015-12-09 16:18:00"));
	// System.out.println(DateUtils.StrToLongDate("2015-12-09 16:00:00"));
	// System.out.println(DateUtils.StrToLongDate("2015-12-14 19:00:00"));
	// System.out.println(DateUtils.longToStrDate("1448941149500"));
	// System.out.println(DateUtils.longToStrDate("1448940410862"));
	// System.out.println(DateUtils.longToStrDate("1448938850798"));
	// System.out.println(DateUtils.longToStrDate("1449739761465"));
	// System.out.println(DateUtils.DateToStr(getNow(), "yyyyMMdd"));

	// }
	/**
	 * 指定日期开始时间
	 * 
	 * @return
	 */
	public static Date startOfDay(Date date) {
		Calendar calendar;
		if (date != null) {
			calendar = dateToCalendar(date);
		} else {
			calendar = Calendar.getInstance();
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 指定日期结束时间
	 * 
	 * @return
	 */
	public static Date endOfDay(Date date) {
		Calendar calendar;
		if (date != null) {
			calendar = dateToCalendar(date);
		} else {
			calendar = Calendar.getInstance();
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 当天的开始时间
	 * 
	 * @return
	 */
	public static long startOfTodDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		return date.getTime();
	}

	/**
	 * 当天的结束时间
	 * 
	 * @return
	 */
	public static long endOfTodDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date date = calendar.getTime();
		return date.getTime();
	}

	/**
	 * 昨天的开始时间
	 * 
	 * @return
	 */
	public static Date startOfyesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 昨天的结束时间
	 * 
	 * @return
	 */
	public static Date endOfyesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获取上周的开始时间
	 */
	public static Date startOfLastWeek() {// 上周开始时间
		return longToDate(startOfThisWeek() - 7 * 24 * 60 * 60 * 1000);
	}

	/**
	 * 获取上周的结束时间
	 */
	public static Date endOfLastWeek() {// 上周开始时间
		return longToDate(endOfThisWeek() - 7 * 24 * 60 * 60 * 1000);
	}

	/**
	 * 获取下周的开始时间
	 */
	public static Date startNextWeek() {// 下周开始时间
		return longToDate(startOfThisWeek() + 7 * 24 * 60 * 60 * 1000);
	}

	/**
	 * 获取下周的结束时间
	 */
	public static Date endOfNextWeek() {// 下周开始时间
		return longToDate(endOfThisWeek() + 7 * 24 * 60 * 60 * 1000);
	}

	/**
	 * 获取本周的开始时间
	 */
	public static long startOfThisWeek() {// 当周开始时间
		Calendar currentDate = Calendar.getInstance();
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date date = currentDate.getTime();
		return date.getTime();
	}

	/**
	 * 获取本周的结束时间
	 */
	public static long endOfThisWeek() {// 当周结束时间
		Calendar currentDate = Calendar.getInstance();
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date date = currentDate.getTime();
		return date.getTime();
	}

	/**
	 * 生成随机时间
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Date randomDate(Date start, Date end) {

		try {

			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			// Date start = format.parse(beginDate);// 构造开始日期
			//
			// Date end = format.parse(endDate);// 构造结束日期

			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。

			if (start.getTime() >= end.getTime()) {

				return null;

			}

			long date = random(start.getTime(), end.getTime());

			return new Date(date);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public static long random(long begin, long end) {

		long rtn = begin + (long) (Math.random() * (end - begin));

		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值

		if (rtn == begin || rtn == end) {

			return random(begin, end);

		}

		return rtn;

	}

	/**
	 * 获取上月的开始时间
	 */
	public static Date startOfLastMonth() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.add(Calendar.MONTH, -1);
		Date date = currentDate.getTime();
		return date;
	}

	/**
	 * 获取上月的结束时间
	 */
	public static Date endOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		return date;
	}

	/**
	 * 获取下月的开始时间
	 */
	public static Date startOfNextMonth() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		currentDate.set(Calendar.DAY_OF_MONTH, 1);
		currentDate.add(Calendar.MONTH, 1);
		Date date = currentDate.getTime();
		return date;
	}
	
	/**
	 * 获取下月的结束时间
	 */
	public static Date endOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, 2);
		cal.add(Calendar.DATE, -1);
		Date date = cal.getTime();
		return date;
	}
	
	/**
	 * 清除 分/秒/毫秒
	 * 
	 * @return
	 */
	public static Date clearDateOfMinute(Date date) {
		Calendar calendar = dateToCalendar(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 清除 秒/毫秒
	 * 
	 * @return
	 */
	public static Date clearDateOfSecond(Date date) {
		Calendar calendar = dateToCalendar(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期所加的天数 (时分秒为0)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDasAgoByDate(Date d, int addDay) {
		if (d == null) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, addDay);
		String month = c.get(Calendar.MONTH) + 1 + "", date = c.get(Calendar.DATE) + "";
		if (month.length() == 1)
			month = "0" + month;
		if (date.length() == 1)
			date = "0" + date;
		String dt = c.get(Calendar.YEAR) + "-" + month + "-" + date + " 00:00:00";
		// String
		// dt=c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE)+"
		// 00:00:00";
		return strToDate(dt);
	}

	/**
	 * 判断差值大小
	 * 
	 * @return
	 */
	public static boolean isDifferenceValue(String date) {
		Date d1 = new Date();
		d1 = strToDate(date);
		String dstr = "2016-09-02 23:59:59";
		Date d2 = strToDate(dstr);
		if (d1.getTime() > d2.getTime())
			return true;
		return false;
	}

	/**
	 * 获取月初日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	/**
	 * 获取月末日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	public static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 转换日期 时分秒取0
	 * 
	 * @param d
	 * @return
	 */
	public static Date getDasAgoLastByDate(Date d) {
		if (d == null) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String month = c.get(Calendar.MONTH) + 1 + "", date = c.get(Calendar.DATE) + "";
		if (month.length() == 1)
			month = "0" + month;
		if (date.length() == 1)
			date = "0" + date;
		String dt = c.get(Calendar.YEAR) + "-" + month + "-" + date + " 23:59:59.999";
		return strToDate(dt);
	}

	/**
	 * 获取当前天 的一个随机时间
	 * 
	 * @param d
	 * @return
	 */
	public static Date getRandomDate(Date d) {
		Date time = startOfDay(d);
		time.setTime(time.getTime() + new Random().nextInt(1000 * 3600 * 24));
		return time;
	}

	/**
	 * 获取日期 时间为0，返回Calendar
	 * 
	 * @param d
	 * @return
	 */
	public static Calendar getCalByDate(Date d) {
		if (d == null) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		String month = c.get(Calendar.MONTH) + 1 + "", date = c.get(Calendar.DATE) + "";
		if (month.length() == 1)
			month = "0" + month;
		if (date.length() == 1)
			date = "0" + date;
		String dt = c.get(Calendar.YEAR) + "-" + month + "-" + date + " 00:00:00";
		return dateToCalendar(strToDate(dt));
	}

	 public static void main(String[] args) {
//	 Date now = DateUtils.startOfDay(new Date());
//	 Date now2 = DateUtils.getDasAgoByDate(now, 2);
//	 System.out.println(now);
	 System.out.println(DateUtils.clearDateOfSecond(new Date()));
	 }
}
