package com.zol.backserver.util;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import com.zol.backserver.util.date.DateFormatUtil;

public class CommonUtil {
	
	
	 //12:45:00
	 public static String getMinute(String time){
		   if(!time.equals("不明确")){
			   Calendar cal = Calendar.getInstance();
			   cal.setTime(DateFormatUtil.parse_HH_mm(time));
			   return String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		   }
		   return "";
		   
	   }
	
	
	public static String getHourStr(String strhour){
		int hour = 0;
		if(StringUtils.isNotBlank(strhour)){
			hour = Integer.parseInt(strhour);
		}else{
			Calendar ca = Calendar.getInstance();
			hour = ca.get(Calendar.HOUR_OF_DAY);
		}
		
		String result = "";
		switch(hour){
			case 8:
				result = "ba";
				break;
			case 9:
				result = "jiu";
				break;
			case 10:
				result = "shi";
				break;
			case 11:
				result = "shiyi";
				break;
			case 12:
				result = "shier";
				break;
			case 13:
				result = "shisan";
				break;
			case 14:
				result = "shisi";
				break;
			case 15:
				result = "shiwu";
				break;
			case 16:
				result = "shiliu";
				break;
			case 17:
				result = "shiqi";
				break;
			case 18:
				result = "shiba";
				break;
			case 19:
				result = "shijiu";
				break;
			case 20:
				result = "ershi";
				break;
			case 21:
				result = "ershiyi";
				break;
			case 22:
				result = "ershier";
				break;
		}
		return result;
	}

}
