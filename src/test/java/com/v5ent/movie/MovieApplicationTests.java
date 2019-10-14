package com.v5ent.movie;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.v5ent.movie.controller.NewsController;
import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.vo.UrlVo;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.utils.CharUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Resource
	private DataMapper dataDao;
	
	@Resource
	private NewsController spiderController;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieApplicationTests.class);
 
	@Test
	public void testSearch() throws Exception {
		LOGGER.info("[]",dataDao.search("哈利"));
	}
	
	@Test
	public void testSpider() throws Exception {
		UrlVo vo = new UrlVo();
		vo.setUrl("https://www.xiangcunxiaoshuo.la/html/617693/");
		vo.setId("21");
		LOGGER.info(spiderController.spiderNews(vo));
	}
	
	@Test
	public void testNickName() {
		for(Data d:dataDao.selectByCondition(null)) {
			String nickName = "";
			try {
				nickName = CharUtils.getFirstSpell(d.getVName());
				LOGGER.info("vname:[{}],nickName:[{}]",d.getVName(),nickName);
			} catch (Exception e1) {
				LOGGER.error(d.getVName()+" error:",e1);
			}
        	d.setVNickname(nickName);
        	dataDao.updateByPrimaryKeySelective(d);
		}
	}
	
	@Test
	public void testSHZ() throws IOException {
		//http://www.my2852.com/gdwx/jpsh/70.htm 01-70 
		String url = "http://www.my2852.com/gdwx/jpsh/03.htm";//8363 8441
//		WebClient webClient = new WebClient();  
//		// 1 启动JS  
//	    webClient.getOptions().setJavaScriptEnabled(true);  
//	    // 2 禁用Css，可避免自动二次请求CSS进行渲染  
//	    webClient.getOptions().setCssEnabled(false);  
//	    // 3 启动客户端重定向  
//	    webClient.getOptions().setRedirectEnabled(true);  
//	  
//	    // 4 js运行错误时，是否抛出异常  
//	    webClient.getOptions().setThrowExceptionOnScriptError(false);  
//	    // 5 设置超时  
//	    webClient.getOptions().setTimeout(5000);   
//	    HtmlPage htmlPage = webClient.getPage(url);
//	    
//	    // 等待JS驱动dom完成获得还原后的网页  
//	    webClient.waitForBackgroundJavaScript(60000);  
//	    // 网页内容  
//	    System.out.println(htmlPage.asXml());  
//		Document book = Jsoup.connect(url).get();
//		Elements list = book.select("body > center:nth-child(4) > table");
//		System.out.println(list);
	}
	

}
