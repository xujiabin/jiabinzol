package com.zol.backserver.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zol.backserver.dao.CommonDao;
import com.zol.backserver.dao.bean.OrderUrl;
import com.zol.backserver.dao.bean.Userinfo;
import com.zol.backserver.service.UserService;
import com.zol.backserver.util.TripleDESUtil;



@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommonDao dao;
	
	
	@RequestMapping(value = "toChangePass",method = RequestMethod.GET)
	public String toChangePass(HttpServletRequest request){
		return "zol/changepassword";
	}
	
	
	@RequestMapping(value = "changePass",method = RequestMethod.POST)
	@ResponseBody
	public String changePass(HttpServletRequest request,
			@RequestParam(value = "newpassword",required=true)  String newpassword,
			@RequestParam(value = "oldpassword",required=true)  String oldpassword){
		  Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
		try {
			if(u.getPassword().equals(TripleDESUtil.encrypt(oldpassword))){
				  u.setPassword(TripleDESUtil.encrypt(newpassword));
				  dao.update(u);
				 return "0";
			  }else{
				  return "-1";//密码验证错误
			  }
		} catch (Exception e) {
			logger.error("密码修改失败！",e);
			e.printStackTrace();
			return "-2";//修改密码失败
		}
	}
	
	
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("uinfo");
		return "login";
	}
	
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(value = "username",required=true)  String username,
    					@RequestParam(value = "password",required=true)  String password,
    					HttpServletRequest request,Model model) {
		logger.info("=====login=====");
		try {
			List<Userinfo> list =  userService.login(username, TripleDESUtil.encrypt(password));
			if(list==null || list.size()<1){
				return "login";
			}else{
				Userinfo  u =  list.get(0);
				 if(u.getActivetime().getTime() <System.currentTimeMillis()){
					 model.addAttribute("msg", "使用期限已经过期！");
					 return "login";
				 }else{
					 request.getSession().setAttribute("uinfo", u);
					 return "index";
				 }
			}
			
		} catch (Exception e) {
			logger.error("login error ",e);
			e.printStackTrace();
		}
		 return "";
        
    }
	
	
	 @RequestMapping(value = "ownerOrder",method = RequestMethod.POST)
	 @ResponseBody
	 public String ownerOrder(HttpServletRequest request){
		 logger.info("====ownerOrder====");
		try {
			 Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
			 List<OrderUrl> list = userService.ownerOrder(u.getId());
			 if(list!=null && list.size()>0){
				 return JSONArray.fromObject(list).toString();
			 }else{
				 return "";
			 }
			 
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
		
	 }

}
