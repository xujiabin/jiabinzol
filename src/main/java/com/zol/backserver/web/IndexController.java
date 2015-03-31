package com.zol.backserver.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zol.backserver.dao.bean.TUrl;
import com.zol.backserver.dao.bean.Userinfo;
import com.zol.backserver.service.MobileService;


@Controller
@RequestMapping(value = "/")
public class IndexController {
	
	Logger logger = Logger.getLogger(IndexController.class);
	
	
	
	@Autowired
	MobileService mobileService;
	
	 @RequestMapping(value = "login",method = RequestMethod.GET)
      public String login() {
        return "login";
      }
	 
	 @RequestMapping(value = "header",method = RequestMethod.GET)
     public String header(Model model,HttpServletRequest request) {
		 Userinfo u = (Userinfo) request.getSession().getAttribute("uinfo");
		 model.addAttribute("u", u);
       return "header";
     }
	 
	 @RequestMapping(value = "left",method = RequestMethod.GET)
     public String left() {
       return "left";
     }
	
	
	   @RequestMapping(value = "index",method = RequestMethod.GET)
	    public String index(Model model) {
		 logger.info("======index========");
			 try {
				 List<TUrl> list = mobileService.queryListInfo();
				 model.addAttribute("list", list);
			} catch (Exception e) {
				logger.error("",e);
				e.printStackTrace();
			}
	        return "zol/index";
	    }

	
	    @RequestMapping(value = "testLexin",method = RequestMethod.GET)
	    public  String testLexin(){
			try {
				logger.info("====testLexin===1=");
//				System.setProperty("webdriver.chrome.driver", "E:\\judian\\chromedriver.exe");
//				WebDriver web =  new ChromeDriver();
//				System.setProperty("webdriver.firefox.bin", "E:\\Mozilla\\Firefox\\firefox.exe");
				System.setProperty("webdriver.firefox.bin", "/root/data/firefox/firefox");
//				System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
//						"com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
				WebDriver web =  new FirefoxDriver();//firefox 19.0   selenium 2.31.0
				
				logger.info("====testLexin==3333==");
				
				web.get("http://dealer.zol.com.cn/beijing/");
				logger.info("====1");
//				WebElement nameEl =web.findElement(By.id("loginUsername"));
//				nameEl.sendKeys("pysc");
//				logger.info("====2");
//				WebElement passEl =web.findElement(By.id("loginPassword"));
//				passEl.sendKeys("7418523");
//				logger.info("====3");
//				WebElement login =web.findElements(By.className("login-btn")).get(0);
//				login.click();
//				WebElement dialog =web.findElements(By.className("layerbox-button-true")).get(0);
//				dialog.click();
//				WebElement faxin =web.findElement(By.className("but_fx"));
//				faxin.click();
//				logger.info("====6");
//				web.switchTo().frame("mainFrame").switchTo().frame(1);
//				
//				
//				WebElement phone =web.findElement(By.id("aimCodesInput"));
//				phone.sendKeys("15901057949");
//				
//				WebElement content =web.findElement(By.id("smsContent"));
//				content.sendKeys("尊敬的用户，您本次绑定手机所使用的验证码是568942（验证码30分钟后失效）【博雅彩】");
//				
//				WebElement fasong =web.findElement(By.id("sendSmsButton"));
//				fasong.click();
//				
//				WebElement divs = web.findElements(By.className("messager-button")).get(0);
//				WebElement sure = divs.findElements(By.tagName("a")).get(0);
//				sure.click();
				/** 跳出frame,进入default content;*/
//			    web.switchTo().defaultContent();
				System.out.println("====4");
				
				web.quit();
				
				//尊敬的用户，您本次绑定手机所使用的验证码是000560（验证码30分钟后失效）【博雅彩】
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	    
}
