package com.zol.backserver.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zol.backserver.cache.CacheService;
import com.zol.backserver.dao.bean.Owneruptime;
import com.zol.backserver.dao.bean.Userinfo;
import com.zol.backserver.service.MobileService;
import com.zol.backserver.service.back.IndexDataService;
import com.zol.backserver.service.back.QueryReferTimeService;
import com.zol.backserver.util.CommonUtil;
import com.zol.backserver.util.TripleDESUtil;
import com.zol.backserver.util.date.DateFormatUtil;


@Controller
@RequestMapping(value = "/mobile")
public class MobileController {
	
	Logger logger = Logger.getLogger(MobileController.class);
	
	@Autowired
	CacheService cache;
	
	@Autowired
	MobileService mobileService;
	
	@Autowired
	QueryReferTimeService queryReferTimeService;
	
	@Autowired
	IndexDataService  indexDataService;
	
	
	@RequestMapping(value = "toUptime")
	public String toUptime(Model model,HttpServletRequest request){
		logger.info("====toUptime====");
		String html = "<option value=''></option>";
		for(int i=0;i<=55;i++){
			html +="<option value='"+i+"'>"+checkNum(i)+"</option>";
		}
		model.addAttribute("html", html);
		try {
			Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
			Owneruptime ow = mobileService.queryUptimeByUid(u.getId());
			if(ow!=null){
				ow.setUpass(TripleDESUtil.decrypt(ow.getUpass()));
				model.addAttribute("jsontime", JSONObject.fromObject(ow.getJsontime()));
			}
			model.addAttribute("ow", ow);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("",e);
		}
		return "zol/owneruptime";
	}
	
	public String checkNum(int num){
		return num<10 ? "0"+num:num+"";
	}
	
	
	   //删除自定义更新时间
		@RequestMapping(value = "removeUpTime",method = RequestMethod.POST)
		public String removeUpTime(HttpServletRequest request){
			logger.info("====removeUpTime====");
			try {
				Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
				mobileService.removeUpTimeByUid(u.getId());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("",e);
			}
			return "redirect:/mobile/toUptime";
		}
	
	
	//保存自定义更新时间
	@RequestMapping(value = "saveOwnerUpTime",method = RequestMethod.POST)
	public String saveOwnerUpTime(@RequestParam(value = "jsontime",required=true)  String jsontime,
			@RequestParam(value = "uname",required=true)  String uname,
			@RequestParam(value = "upass",required=true)  String upass,
			@RequestParam(value = "url",required=true)  String url,
			@RequestParam(value = "uptimeid",required=false)  String uptimeid,
			HttpServletRequest request,Model model){
		logger.info("====saveOwnerUpTime====");
		Owneruptime  ow = new Owneruptime();
		try {
			JSONObject js = JSONObject.fromObject(jsontime);
			logger.info("=="+js.getString("jiu"));
			Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
			ow.setUname(uname);
			ow.setUrl(url);
			ow.setUpass(TripleDESUtil.encrypt(upass));
			ow.setJsontime(jsontime);
			ow.setUtime(new Date());
			ow.setUserinfo(u);
			if(StringUtils.isNotBlank(uptimeid)){
				ow.setId(Integer.parseInt(uptimeid));
			}
			mobileService.saveUpTime(ow);
		} catch (Exception e) {
			jsontime = "";
			e.printStackTrace();
			logger.error("",e);
		}
		return "redirect:/mobile/toUptime";
	}
	
	
	  //委托系统刷新时间
	   @RequestMapping(value = "authorizeSystemRefertime",method = RequestMethod.POST)
	   public String authorizeSystemRefertime(
			    @RequestParam(value = "uname",required=true)  String uname,
				@RequestParam(value = "upass",required=true)  String upass,
				@RequestParam(value = "url",required=true)  String url,
				HttpServletRequest request){
		   logger.info("=====authorizeSystemRefertime======");
		   try {
			    logger.info("===查询系统推荐刷新时间==");
			     String newtime = DateFormatUtil.format_yyyy_MM_dd(new Date());
				 Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
				 Map<String,String> result = cache.get("referTime_"+u.getId()+"_"+newtime);
				 if(result == null){
					 result = queryReferTimeService.queryTime(u.getId());
					 cache.set("referTime_"+u.getId()+"_"+newtime,result);
					
				 }
				 logger.info("====生成自定义时间======");
				 JSONObject json = new JSONObject();
				 for(Entry<String, String> en : result.entrySet()){
					 json.put(CommonUtil.getHourStr(en.getKey()), CommonUtil.getMinute(en.getValue()));
				 }
				 logger.info("==========="+json.toString());
				 
				 mobileService.removeUpTimeByUid(u.getId());
				 Owneruptime  ow = new Owneruptime();
				 ow.setUname(uname);
				 ow.setUrl(url);
				 ow.setUpass(TripleDESUtil.encrypt(upass));
				 ow.setJsontime(json.toString());
				 ow.setUtime(new Date());
				 ow.setUserinfo(u);
				mobileService.saveUpTime(ow);
				 
			} catch (Exception e) {
				logger.error("",e);
				e.printStackTrace();
			}
		   return "redirect:/mobile/toUptime";
	   }
	
	
	
	
	
	//保存我勾选的手机型号
	 @RequestMapping(value = "saveOrderUrl",method = RequestMethod.POST)
	 @ResponseBody
	 public String saveOrderUrl(@RequestParam(value = "ids",required=true)  String ids,
			             HttpServletRequest request){
		 logger.info("====saveOrderUrl====");
		try {
			 Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
			 mobileService.saveOrderUrl(ids,u);
		} catch (Exception e){
			logger.error("",e);
			e.printStackTrace();
			return "0";
		}
		 return "1";
		 
	 }
	 
	 
	   @RequestMapping(value = "showtime",method = RequestMethod.GET)
	    public String showtime(Model model,HttpServletRequest request) {
		   logger.info("====showtime====");
		 try {
			 String newtime = DateFormatUtil.format_yyyy_MM_dd(new Date());
			 Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
			 Map<String,String> result = cache.get("referTime_"+u.getId()+"_"+newtime);
			 if(result == null){
				 logger.info("===缓存无数据，进行时时查询==");
				 result = queryReferTimeService.queryTime(u.getId());
				 cache.set("referTime_"+u.getId()+"_"+newtime,result);
			 }
			 List<Map.Entry<String, String>> resultlist = new ArrayList<Map.Entry<String,String>>(result.entrySet());
				Collections.sort(resultlist, new Comparator<Map.Entry<String, String>>() {
					public int compare(Entry<String, String> o1, Entry<String, String> o2) {
						return o1.getKey().compareTo(o2.getKey());
					}
					
				});
			 model.addAttribute("result", resultlist);
			 model.addAttribute("time", DateFormatUtil.format_yyyy_MM_dd(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("",e);
		}
	        return "zol/show";
	    }
	   
	   
	 
	   
	   
	   
	     //新增手机URL
		 @RequestMapping(value = "toAddUrl",method = RequestMethod.GET)
		 public String toAddUrl(){
			 return "zol/addUrl";
		 }
		 
		 
		 
		 
		 //新增手机URL
		 @RequestMapping(value = "addUrl",method = RequestMethod.POST)
		 @ResponseBody
		 public String addUrl(@RequestParam(value = "url",required=true)  String url){
			 logger.info("====addUrl===="+url);
			try {
				 mobileService.addUrl(url.trim());
			} catch (Exception e){
				logger.error("",e);
				e.printStackTrace();
				return "0";
			}
			 return "1";
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 @RequestMapping(value = "toIndexData",method = RequestMethod.GET)
		 public String toIndexData(){
			 return "zol/addIndexData";
		 }
		 
		 
		 //抓取首页的数据
		 @RequestMapping(value = "getIndexData",method = RequestMethod.POST)
		 @ResponseBody
		 public String getIndexData(@RequestParam(value = "url",required=true)  String url){
			 logger.info("====toIndexData===="+url);
			try {
				indexDataService.dodata(url);
			} catch (Exception e){
				e.printStackTrace();
				return "0";
			}
			 return "1";
		 }

}
