package com.zol.backserver.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class ZolFilter implements Filter{
	
	protected FilterConfig ftConfig;
	
	private Logger logger = Logger.getLogger(ZolFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("==过滤器初始化==");
		this.ftConfig = filterConfig;
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("zol/login","zol/login");
		
		String reqUrl = req.getRequestURL().toString();
		logger.info("==========="+reqUrl);
		if(reqUrl.indexOf("login") == -1 && reqUrl.indexOf("static") == -1){
			HttpSession session = req.getSession();
			if(session.getAttribute("uinfo")==null){
				res.sendRedirect("/zol/login");// 如果没有登录，把视图派发到登录页
			}else{
				chain.doFilter(req, res);
			}
		}else{
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("==过滤器销毁==");
		
	}

}
