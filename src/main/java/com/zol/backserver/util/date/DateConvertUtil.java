package com.zol.backserver.util.date;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateConvertUtil {

	private static final String[] WEEK_CN_FN = { "", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
	private static final String[] WEEK_CN_SN = { "", "周一", "周二", "周三", "周四", "周五", "周六", "周日" };

	/**
	 * 根据周次（1-7）, 获取星期几：星期一
	 * @param idx
	 * @return String
	 * @throws IllegalAccessException 
	 */
	public static String getWeekCnFn(int idx) throws IllegalAccessException {
		if (idx > 7) {
			throw new IllegalAccessException("参数错误：{" + idx + "}不能转换为有效星期几");
		}
		return WEEK_CN_FN[idx];
	}

	/**
	 * 根据周次（1-7）, 获取星期几：星期一
	 * @param idx
	 * @return String
	 * @throws IllegalAccessException 
	 */
	public static String getWeekCnFn(String idx) throws IllegalAccessException {
		int i = Integer.parseInt(idx);
		return getWeekCnFn(i);
	}

	/**
	 * 根据周次（1-7）, 获取周几：周一
	 * @param idx
	 * @return String
	 * @throws IllegalAccessException 
	 */
	public static String getWeekCnSn(int idx) throws IllegalAccessException {
		if (idx > 7) {
			throw new IllegalAccessException("参数错误：{" + idx + "}不能转换为有效周几");
		}
		return WEEK_CN_SN[idx];
	}

	/**
	 * 根据周次（1-7）, 获取周几：周一
	 * @param idx
	 * @return String
	 * @throws IllegalAccessException 
	 */
	public static String getWeekCnSn(String idx) throws IllegalAccessException {
		int i = Integer.parseInt(idx);
		return getWeekCnFn(i);
	}

	public static int getWeekNum(String date) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(DateFormatUtil.parse_yyyyMMdd(date));
		int result = c.get(Calendar.DAY_OF_WEEK) - 1;
		return result == 0 ? 7 : result;
	}
	
	public static int getWeekNum(Long date) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(new Date(date));
		int result = c.get(Calendar.DAY_OF_WEEK) - 1;
		return result == 0 ? 7 : result;
	}
	
	public static int getWeekNum(Date date) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		c.setTime(date);
		int result = c.get(Calendar.DAY_OF_WEEK) - 1;
		return result == 0 ? 7 : result;
	}
	
	
	/**
	 * 日期处理模块 (将日期加上某些天或减去天数)返回字符串 
	 * @param days
	 * @return
	 */
	public static Long dateAdd(int days,Date date) {
		Calendar canlendar = Calendar.getInstance();
		if(date != null){
			canlendar.setTime(date);
		}
		//java.util包 
		canlendar.add(Calendar.DATE, days);
		//日期减 如果不够减会将月变动 
		return canlendar.getTimeInMillis();
	}

	public static void main(String[] args) {
		System.out.println(getWeekNum("20140109"));
		System.out.println(getWeekNum("20140112"));
	}
}
