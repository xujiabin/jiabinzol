package com.zol.backserver.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DateCommonUtil {
	
	
	

	
	//分钟比较
	public static void compare(Date date,Map<String,Integer> timeMap){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int minute = ca.get(Calendar.MINUTE);
		if(minute >=0 && minute <5){
			timeMap.put("0", mapget(timeMap.get("0"))+1);//0分钟
			
		}else if(minute >=5 && minute <10){
			timeMap.put("5", mapget(timeMap.get("5"))+1);//5分钟
			
		}else if(minute >=10 && minute <15){
			timeMap.put("10", mapget(timeMap.get("10"))+1);//10分钟
			
		}else if(minute >=15 && minute <20){
			timeMap.put("15", mapget(timeMap.get("15"))+1);//15分钟
			
		}else if(minute >=20 && minute <25){
			timeMap.put("20", mapget(timeMap.get("20"))+1);//20分钟
			
		}else if(minute >=25 && minute <30){
			timeMap.put("25", mapget(timeMap.get("25"))+1);//25分钟
			
		}else if(minute >=30 && minute <35){
			timeMap.put("30", mapget(timeMap.get("30"))+1);//30分钟
			
		}else if(minute >=35 && minute <40){
			timeMap.put("35", mapget(timeMap.get("35"))+1);//35分钟
			
		}else if(minute >=40 && minute <45){
			timeMap.put("40", mapget(timeMap.get("40"))+1);//40分钟
			
		}else if(minute >=45 && minute <50){
			timeMap.put("45", mapget(timeMap.get("45"))+1);//45分钟
			
		}else if(minute >=50 && minute <55){
			timeMap.put("50", mapget(timeMap.get("50"))+1);//50分钟
			
		}else if(minute >=55 && minute <60){
			timeMap.put("55", mapget(timeMap.get("55"))+1);//55分钟
			
		}
	}
	
	public static int mapget(Object obj){
		if(obj == null){
			return 0;
		}else{
			return Integer.parseInt(obj.toString());
		}
	}
	
	/**
	 * 比较 返回结果 分钟:秒
	 */
	public static String compareList(String time,Map<String,Integer> timeMap){
		//将map转换成数组形式
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(timeMap.entrySet());
		//对数组进行排序  倒序
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
			
		});
		String timestr = "";
		if(list != null && list.size()>0){
			timestr = list.get(0).getKey();
		}
		String min = "";
		if(timestr.equals("0")){
			min = "00:00";
		}else if(timestr.equals("5")){
			min = "05:00";
		}else if(timestr.equals("10")){
			min = "10:00";
		}else if(timestr.equals("15")){
			min = "15:00";
		}else if(timestr.equals("20")){
			min = "20:00";
		}else if(timestr.equals("25")){
			min = "25:00";
		}else if(timestr.equals("30")){
			min = "30:00";
		}else if(timestr.equals("35")){
			min = "35:00";
		}else if(timestr.equals("40")){
			min = "40:00";
		}else if(timestr.equals("45")){
			min = "45:00";
		}else if(timestr.equals("50")){
			min = "50:00";
		}else if(timestr.equals("55")){
			min = "55:00";
		}else{
			return "不明确";
		}
		return time+":"+min;
	}
	
	
	public static String checkTime(int a){
		return a>=10?""+a:"0"+a;
	}

}
