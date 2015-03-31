package com.zol.backserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zol.backserver.cache.CacheService;
import com.zol.backserver.dao.CommonDao;
import com.zol.backserver.dao.bean.OrderUrl;
import com.zol.backserver.dao.bean.Owneruptime;
import com.zol.backserver.dao.bean.TUrl;
import com.zol.backserver.dao.bean.Userinfo;



@Service
@Transactional
public class MobileService {
	
	
	
   private Logger logger = Logger.getLogger(MobileService.class);
	
	@Autowired
	CommonDao dao;
	
	@Autowired
	CacheService cache;
	
	
	public void saveUpTime(Owneruptime ow)throws Exception{
		logger.info("===saveUpTime====");
		if(ow.getId()!=0){
			dao.update(ow);
		}else{
			dao.add(ow);
		}
		cache.delete("allReferTime");
	}
	
	public void removeUpTimeByUid(int uid)throws Exception{
		logger.info("===removeUpTimeByUid====");
		String sql = "delete from Owneruptime where uid =  "+uid;
		dao.executeUpdate(sql, null);
		cache.delete("allReferTime");
	}
	
	public Owneruptime queryUptimeByUid(int uid)throws Exception{
		logger.info("===queryUptimeByUid====");
		String sql = "from Owneruptime where uid =  "+uid;
		List<Owneruptime> list = dao.queryList(sql, null);
		return (list!=null && list.size()>0)?list.get(0):null;
	}
	
	
	//保存定制的手机产品
	public void saveOrderUrl(String ids,Userinfo u) throws Exception{
		logger.info("===saveOrderUrl=========");
		logger.info("==删除以前的数据==="+u.getId());
		String delsql = "delete from OrderUrl where userid = "+u.getId();
		dao.executeUpdate(delsql, null);
		
		logger.info("==保存现有设置的数据==="+ids);
		String[] id = ids.split(",");
		for(String s : id){
			dao.add(new OrderUrl(u,s));
		}
	}
	
	

	//查询所有的手机产品
	public List<TUrl> queryListInfo() throws Exception{
		String hql = " from TUrl  ";
		return dao.queryList(hql, null);
		
	}
	
	
	//新增产品URL
	public void addUrl(String url) throws Exception{
		logger.info("===addUrl========="+url);
		String sql = "from TUrl where url = :url";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("url", url);
		List<TUrl> list = dao.queryList(sql, map);
		if(list == null || list.size()<1){
			Document doc = getURLContentForGet(url);
			String name = doc.select(".page-title").text();
			logger.info("===addUrl========name======"+name);
			dao.add(new TUrl(name,url));
		}
		
	}
		
	
	/**
	 * get方式获取DOM
	 */
	private  Document getURLContentForGet(String url) throws Exception {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}

}
