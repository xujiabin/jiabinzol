package com.zol.backserver.service.back;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.zol.backserver.cache.CacheService;
import com.zol.backserver.dao.CommonDao;
import com.zol.backserver.dao.bean.Owneruptime;
import com.zol.backserver.util.CommonUtil;
import com.zol.backserver.util.TripleDESUtil;
import com.zol.backserver.util.date.DateFormatUtil;


/**
 * 驱动浏览器操作
 */
public class DriverToBrowserService {
	
	private Logger logger = Logger.getLogger(DriverToBrowserService.class);
	
	
	Timer timer;
	
	List<Timer> timerlist = new ArrayList<Timer>();
	
	@Autowired
	CommonDao dao;
	
	@Autowired
	CacheService cache;
	
	
	//查询用户自定义刷新数据，添加定时任务
	public void browserReferTime(){
		logger.info("=====browserReferTime=====");
		try {
			String sql = "from Owneruptime o where o.userinfo.activetime > now() ";
			List<Owneruptime> list = cache.get("allReferTime");
			if(list == null || list.size()<1){
				list = dao.queryList(sql, null);
				cache.set("allReferTime", list);
			}
			//记录这里产生多少个定时器，初始化为0
			cache.set("timerCount", 0);
			
			if(list!=null && list.size()>0){
				timerlist = new ArrayList<Timer>(list.size());
				for(Owneruptime o : list){
					String hour = CommonUtil.getHourStr("");
					JSONObject json = JSONObject.fromObject(o.getJsontime());
					//获取分钟
					String minute = "";
					try {
						minute =json.getString(hour); 
					} catch (Exception e) {
						continue;
					}
					logger.info(o.getUname()+"===分钟==========="+minute);
					if(StringUtils.isNotBlank(minute) && !minute.equals("0")){
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.MINUTE, Integer.parseInt(minute));
						//驱动浏览器
						toBrowser(o.getUrl(),o.getUname(),TripleDESUtil.decrypt(o.getUpass()),cal.getTime());
					}
					
				}
			}
			
//			{'ba':'1','jiu':'2','shi':'3','shiyi':'9','shier':'13','shisan':'','shisi':'','shiwu':'','shiliu':'11','shiqi':'','shiba':'','shijiu':'7','ershi':'','ershiyi':'8','ershier':'53'}
			
		} catch (Exception e) {
			logger.error("",e);
			e.printStackTrace();
		}
		
	}
	
	
	//定时任务驱动浏览器
	public void toBrowser(final String url,final String username,final String password,Date date){
		logger.info("====doBrowser===="+DateFormatUtil.format_yyyy_MM_dd_HH_mm_ss(date));
		//记录定时器的个数
		int count = cache.get("timerCount");
		++count;
		cache.set("timerCount", count);
		logger.info("===="+count);
		timer = new Timer();
		timerlist.add(timer);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					logger.info("====TimerTask====");
					logger.info(url);
					logger.info(username);
					logger.info(password);
					System.setProperty("webdriver.firefox.bin", "/root/data/firefox/firefox");
					WebDriver web =  new FirefoxDriver();//firefox 19.0   selenium 2.31.0
					logger.info("====open url===="+url);
					web.get(url);
					WebElement nameEl =web.findElement(By.id("loginUsername"));
					nameEl.sendKeys(username);
					WebElement passEl =web.findElement(By.id("loginPassword"));
					passEl.sendKeys(password);
					logger.info("====1");
					WebElement login =web.findElements(By.className("login-btn")).get(0);
					login.click();
					try {
						WebElement dialog =web.findElements(By.className("layerbox-button-true")).get(0);
						dialog.click();
//						layerbox-border
					} catch (Exception e) {
						logger.error("经销商没有弹出框提醒！",e);
					}
					logger.info("====2");
					WebElement refer =web.findElement(By.id("oneKeyRefreshPirce"));
					refer.click();
					WebElement sure =web.findElements(By.className("layerbox-button-true")).get(0);
					sure.click();
//					
					web.quit();
					
					int count = 0;
					synchronized (this) {
						count = cache.get("timerCount");
						count--;
						cache.set("timerCount", count);
					}
					
					//如果所有的定时器都执行完任务了，则进行销毁
					logger.info(count);
					if(count < 1){
						logger.info("======任务都完成，进行清除=====");
						for(Timer t : timerlist){
							t.cancel();
						}
						timerlist = null;
					}
				} catch (Exception e) {
					logger.error("",e);
					e.printStackTrace();
				}
			}
		}, date);
	}
	
	
	

}
