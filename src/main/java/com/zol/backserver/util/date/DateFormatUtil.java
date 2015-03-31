package com.zol.backserver.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateFormatUtil {

	private static Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

	private static final DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private static final DateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat yyyy_MM_dd_HH_mm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final DateFormat MM_dd_HH_mm_ss = new SimpleDateFormat("MM-dd HH:mm:ss");
	
	private static final DateFormat MM_dd_HH_mm = new SimpleDateFormat("MM-dd HH:mm");
	
	
	private static final DateFormat HH_mm = new SimpleDateFormat("HH:mm");

	private static String format(DateFormat df, Object obj) {
		String result = null;
		try {
			if (obj instanceof Date) {
				result = df.format(obj);
			} else if (obj instanceof Number) {
				result = df.format(((Number) obj).longValue());
			} else if (obj instanceof String) {
				result = df.format(Long.parseLong((String) obj));
			} else {
				throw new IllegalArgumentException("Cannot format given Object as a Date");
			}
		} catch (NumberFormatException e) {
			logger.error("日期格式化失败，输入的时间数值非法：" + obj);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.error("日期格式化失败，输入的日期字符串非法：" + obj);
			e.printStackTrace();
		}
		return result;
	}

	private static Date parse(DateFormat df, String time) {
		Date result = null;
		try {
			result = df.parse(time);
		} catch (ParseException e) {
			logger.error("日期格式化失败，输入的日期字符串非法：" + time);
			e.printStackTrace();
		}
		return result;
	}

	public static String format_yyyyMMdd(String time) {
		return format(yyyyMMdd, time);
	}

	public static String format_yyyyMMdd(Long time) {
		return format(yyyyMMdd, time);
	}

	public static String format_yyyyMMdd(Date date) {
		return format(yyyyMMdd, date);
	}

	public static String format_yyyy_MM_dd(Object time) {
		return format(yyyy_MM_dd, time);
	}

	public static String format_yyyy_MM_dd_HH_mm_ss(Object time) {
		return format(yyyy_MM_dd_HH_mm_ss, time);
	}

	public static String format_yyyy_MM_dd_HH_mm(Object time) {
		return format(yyyy_MM_dd_HH_mm, time);
	}

	public static String format_MM_dd_HH_mm_ss(Object time) {
		return format(MM_dd_HH_mm_ss, time);
	}

	public static String format_HH_mm(Object time) {
		return format(HH_mm, time);
	}

	public static Date parse_yyyyMMdd(String time) {
		return parse(yyyyMMdd, time);
	}

	public static Date parse_yyyy_MM_dd(String time) {
		return parse(yyyy_MM_dd, time);
	}

	public static Date parse_yyyy_MM_dd_HH_mm_ss(String time) {
		return parse(yyyy_MM_dd_HH_mm_ss, time);
	}

	public static Date parse_yyyy_MM_dd_HH_mm(String time) {
		return parse(yyyy_MM_dd_HH_mm, time);
	}

	public static Date parse_MM_dd_HH_mm_ss(String time) {
		return parse(MM_dd_HH_mm_ss, time);
	}
	
	public static Date parse_MM_dd_HH_mm(String time) {
		return parse(MM_dd_HH_mm, time);
	}
	
	public static Date parse_HH_mm(String time) {
		return parse(HH_mm, time);
	}

	public static void main(String[] args) {
		System.out.println(DateFormatUtil.format_yyyy_MM_dd(1381917600000L));
		System.out.println(DateFormatUtil.format_yyyy_MM_dd(new Long("1381917600000")));
		System.out.println(DateFormatUtil.format_yyyy_MM_dd(new Date()));

		Date d = DateFormatUtil.parse_yyyy_MM_dd("2013-05-04 11:00:00");
		System.out.println(DateFormatUtil.format_MM_dd_HH_mm_ss(d));
	}
}
