package com.zol.backserver.service.back;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zol.backserver.service.MobileService;


//抓取首页的数据，更新turl表
@Service
@Transactional
public class IndexDataService {
	
	@Autowired
	MobileService mobileService;
	
	public  void dodata(String url){
		try {
			Document doc = getURLContentForGet(url);
			Elements ets =  doc.select(".list-box").select(".list-item");
			String zuihouurl = "";
			for(Element e : ets){
				try {
					String ul = e.select(".pro-intro").select("h3").select("a").attr("abs:href");
					Thread.sleep(1000 *5);
					Document childdoc = getURLContentForGet(ul);
					System.out.println("ul+=="+ul);
					String childurl = childdoc.select("#tagNav").select(".nav").select("li").get(1).select("a").attr("abs:href");
					System.out.println("childurl+=="+childurl);
					Thread.sleep(1000 *5);
					Document pdoc = getURLContentForGet(childurl);
					Elements wangluos = pdoc.select(".version-price-filter").select("li").get(0).select("a");
					try {
						for(Element wl : wangluos){
							String href = wl.attr("abs:href");
							Thread.sleep(1000 *5);
							Document d = getURLContentForGet(href);
							String name = d.select(".page-title").text();
							System.out.println("==========="+name);
							Elements urls = d.select(".version-price-filter").select("li").get(1).select("a");
							
							try {
								for(Element u : urls){
									zuihouurl = "";
									zuihouurl = u.attr("abs:href");
									System.out.println("最终结果："+zuihouurl);
									mobileService.addUrl(zuihouurl);
								}
							} catch (Exception e2) {
								continue;
							}
							
						}
					} catch (Exception e2) {
						continue;
					}
					Thread.sleep(1000 *5);
				} catch (Exception e2) {
					e2.printStackTrace();
					continue;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * get方式获取DOM
	 */
	private   Document getURLContentForGet(String url) throws Exception {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}

}
