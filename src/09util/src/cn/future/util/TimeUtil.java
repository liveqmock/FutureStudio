package cn.future.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	/**
	 * yyyy年MM月dd日
	 * @param date
	 * @return
	 */
	public static String date2ShowYMD(Date date){
		return TimeUtil.dateToString("yyyy年MM月dd日", date);
	}
	/**
	 * yyyy年MM月dd日 HH时mm分ss秒
	 * @param date
	 * @return
	 */
	public static String date2ShowYMDHMS(Date date){
		return TimeUtil.dateToString("yyyy年MM月dd日 HH时mm分ss秒", date);
	}
	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */ 
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	/**
	 * 获取当前日期 yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}
	/**
	 * 按照格式对日期数据进行格式化
	 * @param format
	 * @param date
	 * @return
	 */
	public static String dateToString(String format, Date date){
		if(date==null){
			return "";
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
	}
	/**
	 * 
	 * @param s  2012-01-01 **:**:**
	 * @return
	 */
	public static Date stringToDate(String s){
		int year = Integer.parseInt(s.substring(0, 4));
		
		int month=s.length()>7 ? Integer.parseInt(s.substring(5,7)):0;
		int day=s.length()>10 ? Integer.parseInt(s.substring(8,10)):0;
		int h=s.length()>13 ? Integer.parseInt(s.substring(11,13)):0;
		int m=s.length()>16 ? Integer.parseInt(s.substring(14,16)):0;
		int se=s.length()>19 ? Integer.parseInt(s.substring(17,19)):0;
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, h, m, se);
	    return c.getTime();
	}
	/**
	 * 返回yyyy-MM-dd的日期数据
	 * @param date
	 * @return
	 */
	public static String dateToYMDString(Date date){
		return dateToString("yyyy-MM-dd",date);
	}
	/**
	 * 返回yyyy-MM-dd HH:mm:ss的日期数据
	 * @param date
	 * @return
	 */
	public static String dateToYMDHMSString(Date date){
		return dateToString("yyyy-MM-dd HH:mm:ss",date);		
	}
	/**
	 * 计算两个时间的差值  年月天
	 * @param start
	 * @param end
	 * @return
	 */
	public static String timeReduce(Calendar start, Calendar end){
		String re = "";
		int year = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		int month = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
		int day = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
		if(day<0){
			month--;
			day=day+30;
		}
		if(month<0){
			year--;
			month=month+12;
		}
		if(year!=0){
			re+=year+"年";
		}
		if(month!=0){
			re+=month+"月";
		}
		if(day!=0){
			re+=day+"天";
		}
		return re;
	}
	/**
	 * 时间差  天-时-分-秒
	 * @param start
	 * @param end
	 * @return
	 */
	public static String timeDifference(Date start, Date end) {
		if(start == null || end == null){
			return "";
		}
		long l = start.getTime() - end.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
		return "" + day + "天" + hour + "小时" + min + "分" + s + "秒";
	}
	/**
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if( null == date ) {
			return 0;
		}
		
		return date.getTime()/1000;
	}
		
	/**
	 * 时间转换成字符串
	 * @param date 时间
	 * @param formatType 格式化类型
	 * @return String
	 */
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}
}
