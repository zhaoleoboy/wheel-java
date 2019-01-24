package com.leo.wheel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期操作的工具类
 * 
 * @author leo
 *
 */
public class DateUtils {

	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_COMPACT = "yyyyMMdd";
	public static final String DATE_FORMAT_COMPACTFULL = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_FULL_MSEL = "yyyyMMddHHmmssSSSS";
	public static final String DATE_YEAR_MONTH = "yyyyMM";
	public static final String DATE_FORMAT_FULL_MSE = "yyyyMMddHHmmssSSS";

	/**
	 * 根据时间格式返回对应的String类型的时间
	 *
	 * @param format
	 * @return
	 */
	public static String getCurDateTime(String format) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		String dataTime = now.format(dateTimeFormatter);
		return dataTime;
	}

	/**
	 * 获取系统当前日期时间
	 *
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 获取系统当前日期时间
	 *
	 * @return
	 */
	public static LocalDateTime getCurrentLocalDateTime() {
		return LocalDateTime.now();
	}


	/**
	 * 日期转字符串
	 *
	 * @return String
	 */
	public static String parseDateToString(Date thedate, String format) {
		if (thedate != null) {
			Instant instant = thedate.toInstant();
			ZoneId zone = ZoneId.systemDefault();
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
			return localDateTime.format(dateTimeFormatter);
		}
		return null;
	}

	/**
	 * 字符串转日期
	 *
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date parseStringToDate(String thedate, String format) throws ParseException {
		DateFormat sdf = new SimpleDateFormat(format);
		Date dd1 = sdf.parse(thedate);
		return dd1;
	}

	/**
	 * 得到当前日期的前N天时间 yyyymmdd
	 *
	 * @param format
	 * @param day
	 * @return
	 */
	public static String beforeNDaysDate(String format, int day) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		if (day > 0) {
			return LocalDateTime.now().minusDays(day).format(dateTimeFormatter);
		}
		return null;
	}

	/**
	 * 得到N天后的日期
	 *
	 * @param theDate 某日期 格式 yyyy-MM-dd
	 * @param nDayNum N天
	 * @return String N天后的日期
	 * @author kevin
	 */
	public static String afterNDaysDate(String theDate, Integer nDayNum, String format) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(theDate, dateTimeFormatter).plusDays(nDayNum).format(dateTimeFormatter);
	}

	/**
	 * 比较两个字符串格式日期大小,带格式的日期
	 * @param strdat1
	 * @param strdat2
	 * @param format
	 * @return
	 */
	public static boolean isBefore(String strdat1, String strdat2, String format) {
		try {
			Date dat1 = parseStringToDate(strdat1, format);
			Date dat2 = parseStringToDate(strdat2, format);
			return dat1.before(dat2);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 方法描述: 计算两个日期相差天数
	 *
	 * @param end   结束日期
	 * @param start 开始日期
	 */
	public static int getSubDays(String end, String start) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		Long between = ChronoUnit.DAYS.between(startDate, endDate);
		return between.intValue();
	}

}
