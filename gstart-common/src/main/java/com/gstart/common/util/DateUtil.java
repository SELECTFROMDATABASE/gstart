package com.gstart.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * 
 * warning 使用月份的时候千万要使用Calendar类中的静态属性
 * @author Jerry
 *
 */
public class DateUtil {
	public static int ADD = 0;
	public static int REDUCE = 1;
	public static String FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	public static String FORMAT_Y_M_D = "yyyy-MM-dd";
	public static String FORMAT_H_M_S = "HH:mm:ss";
	/**
	 * 判断类
	 * @author Jerry
	 *
	 */
	public interface TimePredicate{
		//between 时间差 iterator周期 range 误差范围
		boolean test(int between, int iterator, int range);
		boolean test(Object o);
	}
	/**
	 * 设置时间值
	 * @author Jerry
	 *
	 */
	public interface TimeInit{
		String iterDay();
		String range();
		long currentTime();
		String oldTime(Object somthing);
	}
	
	/**
	 * 获取两个日期的日差
	 * 
	 * @param start
	 * @param end
	 *            
	 * @return 时间差
	 */
	public static int betweenDays(long start,long end){
		Calendar startCa = getCalendar(start);
		Calendar endCa = getCalendar(end);
		int be = 0;
		int startDayOfYear = startCa.get(Calendar.DAY_OF_YEAR);
		int endDayOfYear = endCa.get(Calendar.DAY_OF_YEAR);

		int startYear = startCa.get(Calendar.YEAR);
		int endYear = endCa.get(Calendar.YEAR);
		
		if(startYear != endYear){
			for(int i = startYear ; i < endYear; i++){
				Calendar tmp = Calendar.getInstance();
				tmp.set(Calendar.YEAR, i);
				if(isLeapYear(tmp)){
					be+=366;
				}else{
					be+=365;
				}
				tmp = null;
			}
			be = be + endDayOfYear - startDayOfYear;
		}else{
			be = endDayOfYear - startDayOfYear;
		}
		
		startCa = null;
		endCa = null;
		return be;
	}
	
	/**
	 * 获得这个月的第一天
	 * 
	 * @param time
	 *            需要获得的日期的long
	 * @return 第一天
	 */
	public static Date getFirstDay(Long time) {
		Calendar c = getCalendar(time);
		c.add(Calendar.DAY_OF_MONTH, -(c.get(Calendar.DAY_OF_MONTH) - 1));
		return c.getTime();

	}
	
	
	/**
	 * 格式化日期字符串
	 * @param times
	 * @param pattern
	 * @return
	 */
	public static String getPatterDate(long times,String pattern){
		return getPatterDate(new Date(times), pattern);
	}
	
	/**
	 * 格式化日期字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getPatterDate(Date date ,String pattern){
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	
	/**
	 * 增减日期
	 * @param date
	 * @param days
	 * @param handle
	 * @return
	 */
	public static Date modifyDay(Date date,int days,int handle){
		Calendar c = getCalendar(date.getTime());
		if(handle == ADD)
			c.add(Calendar.DAY_OF_MONTH, days);
		else if(handle == REDUCE)
			c.add(Calendar.DAY_OF_MONTH,-days);
		return c.getTime();
	}
	
	/**
	 * 获得这个月的最后一天
	 * 
	 * @param time
	 *            需要获得的日期的long
	 * @return 最后一天
	 */
	public static Date getLastDay(Long time) {
		Calendar c = getCalendar(time);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 中间日期，判断是否二月</br> 闰年二月返回15</br> 平年二月返回14</br> 大月返回16 </br>小月返回15
	 * 
	 * @param time
	 *            需要获得的日期的long
	 * @return
	 */
	public static Date getMiddleDay(Long time) {
		Calendar c = getCalendar(time);

		// 判断输入是否二月
		if (c.get(Calendar.MONTH) == Calendar.FEBRUARY) {
			// 判断闰年
			if (isLeapYear(c)) {
				c.set(Calendar.DAY_OF_MONTH, 15);
				return c.getTime();
			} else {
				c.set(Calendar.DAY_OF_MONTH, 14);
				return c.getTime();
			}
			// 判断是否大月（31）
		} else if (isSolarMonth(c)) {
			c.set(Calendar.DAY_OF_MONTH, 16);
			return c.getTime();
		} else {
			c.set(Calendar.DAY_OF_MONTH, 15);
			return c.getTime();
		}
	}
	protected static boolean isSolarMonth(Calendar c) {
		int month = c.get(Calendar.MONTH);
		// 算法可优
		int[] a = { Calendar.JANUARY,Calendar.MARCH,Calendar.MAY,Calendar.JULY,Calendar.AUGUST,Calendar.OCTOBER,Calendar.DECEMBER};
		for (int i : a) {
			if (month == i) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 判断是否闰年
	 * 
	 * @param c
	 *            该年份的月历
	 */
	protected static boolean isLeapYear(Calendar c) {
		int year = c.get(Calendar.YEAR);
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	static protected Calendar getCalendar(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 由毫秒数转换成日历
	 * 
	 * @param time
	 *            需要转换的毫秒数
	 * @return 该日期的日历
	 */
	static protected Calendar getCalendar(Long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		return c;
	}
}
