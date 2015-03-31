package com.zol.backserver.service.back;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zol.backserver.cache.CacheService;
import com.zol.backserver.dao.CommonDao;
import com.zol.backserver.util.DateCommonUtil;
import com.zol.backserver.util.date.DateFormatUtil;


/**
 * 查询刷新时间
 */

@Service
public class QueryReferTimeService {
	
	private Logger logger = Logger.getLogger(QueryReferTimeService.class);
	
	@Autowired
	CommonDao dao;
	
	@Autowired
	CacheService cache;
	
	
	
	//根据用户查询刷新时间
	public Map<String,String> queryTime(int uid){
		logger.info("===queryTime===");
		Map<String,String> result = new HashMap<String,String>();
		//设置一天内的时段
		String[] times = {"09","10","11","12","13","14","15","16","17","18","19","20","21","22"};
		
		Calendar date = Calendar.getInstance();
		//获取年月
		String yearMonth = DateCommonUtil.checkTime(date.get(Calendar.YEAR))+""+DateCommonUtil.checkTime(date.get(Calendar.MONTH)+1);
		//获取日
		String end = yearMonth+DateCommonUtil.checkTime(date.get(Calendar.DAY_OF_MONTH));
		
		Calendar ecd = Calendar.getInstance();
		ecd.add(Calendar.DAY_OF_MONTH, -15);//在当前时间基础上减去20天
		String begin = DateCommonUtil.checkTime(ecd.get(Calendar.YEAR))+""+DateCommonUtil.checkTime(ecd.get(Calendar.MONTH)+1)+DateCommonUtil.checkTime(ecd.get(Calendar.DAY_OF_MONTH));
		
		try {
			Map<String,Integer> timeMap = null;
			for(String time : times){
				timeMap = new HashMap<String,Integer>();
				
				StringBuffer sb =  new StringBuffer("SELECT  t.updates  FROM tdata t");
				sb.append("  where t.urlid in (select urlid from orderurl where userid = "+uid+") ");
				sb.append("  and  date_format(t.updates,'%Y%m%d') >= '"+begin+"' and date_format(t.updates,'%Y%m%d') <='"+end+"' ");
				sb.append("  and  date_format(t.updates,'%H%i') >= '"+time+"00' and date_format(t.updates,'%H%i') <='"+time+"59' ");
				sb.append("  group by t.updates ");// order by t.updates desc 
				
				logger.info("sql："+sb.toString());
				
				List<Object[]> list = dao.queryListBySql(sb.toString(), null);
				for(int i=0;i<list.size();i++){
					Object obj = list.get(i);
					Date d = DateFormatUtil.parse_yyyy_MM_dd_HH_mm_ss(obj.toString());
					DateCommonUtil.compare(d,timeMap);
				}
				if(list == null || list.size()<1){
					result.put(time, "不明确");
					continue;
				}
				result.put(time, DateCommonUtil.compareList(time,timeMap));
			}
			logger.info("最后的更新时间结果："+result.toString());
		} catch (Exception e) {
			logger.error("",e);
			e.printStackTrace();
		}
		return result;
	}

}
