package com.zol.backserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zol.backserver.dao.CommonDao;
import com.zol.backserver.dao.bean.OrderUrl;
import com.zol.backserver.dao.bean.Userinfo;



@Transactional
@Service
public class UserService {
	
	private Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	CommonDao dao;
	
	public List<Userinfo> login(String username,String password) throws Exception{
		logger.info("===login==");
		String hql = "from Userinfo where username = :username and password = :password  and status !=-1";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		return dao.queryList(hql, map);
		
	}
	
	
	public List<OrderUrl> ownerOrder(int userid) throws Exception{
		logger.info("===login==");
		String hql = "from OrderUrl where userid = :userid ";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		return dao.queryList(hql, map);
		
	}

}
