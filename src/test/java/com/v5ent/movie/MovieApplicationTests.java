package com.v5ent.movie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.v5ent.movie.controller.MovieController;
import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.News;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.mapper.NewsMapper;
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
	private NewsMapper newsDao;
	
	@Resource
	private MovieController spiderController;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieApplicationTests.class);
 
	@Test
	public void testSearch() throws Exception {
		LOGGER.info("[]",dataDao.search("哈利"));
	}
	
	@Test
	public void testSpiderMovie() throws Exception {
		try {
			LOGGER.info(spiderController.spiderMovies());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testSpider() throws Exception {
		UrlVo vo = new UrlVo();
		vo.setUrl("https://www.xiangcunxiaoshuo.la/html/617693/");
		vo.setId("21");
		LOGGER.info(spiderController.spiderNews(vo));
	}*/
	
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
	public void testHLM() throws MalformedURLException, IOException {
		Integer nId = 2884;
		for(int i=1;i<=80;i++) {
			String s = i<10?"00"+i:"0"+i;
			doex(nId,s);
			nId = newsDao.getNextIdByNid(""+nId);
		}
	}
	
	@Test
	public void testSHZ() throws IOException {
		Integer nId = 6157;
		doex(nId,"0");
		nId = newsDao.getNextIdByNid(""+nId);
		doex(nId,"00");
		nId = newsDao.getNextIdByNid(""+nId);
		doex(nId,"000");
		for(int i=1;i<=70;i++) {
			nId = newsDao.getNextIdByNid(""+nId);
			String s = i<10?"0"+i:""+i;
			doex(nId,s);
		}
	}
	
	/**
	 * jp shz
	 * @param nId
	 * @param i
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void doex(int nId,String s) throws MalformedURLException, IOException {
		//http://www.my2852.com/gdwx/hlm/119.htm
		String realUrl = "http://m.v5ent.com:9527/http://www.my2852.com/gdwx/jpsh/"+s+".htm";
		Document book = Jsoup.parse(new URL(realUrl).openStream(), "UTF-8", realUrl);
		Elements list = book.select("body > center:nth-child(4) > table > tbody > tr");
		StringBuilder sb = new StringBuilder("");
		sb.append("<table border=\"0\" cellspacing=\"1\" style=\"font-size: 12pt; font-family: 宋体; line-height:150%\"><tbody>");
		sb.append(list.get(1).toString().replace("15pt", "14pt"));
		sb.append(list.get(3));//content
		sb.append("</tbody></table>");
		LOGGER.info("{}-{} -{} ", nId,s,list.get(1).text());
		newsDao.updateByPrimaryKeySelective(new News() {{
			setNId(nId);
			setNTitle(list.get(1).text());
			setNFrom("http://www.my2852.com/gdwx/jpsh/"+s+".htm");
			setNContent(sb.toString());
		}});
	}
	
	public static void main(String[] args) {
		try {
			xyj();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void xyj() throws IOException {
		//phantomjs  --ignore-ssl-errors=yes server.js 9527
		/*StringBuilder sb = new StringBuilder("酷M3U8$$");
		for(int i =1;i<=60;i++) {
			String realUrl = "http://m.v5ent.com:9527/https://51kdy.org/vodplay/xinxiyouji-1-"+i+".html";
			Document book = Jsoup.parse(new URL(realUrl).openStream(), "UTF-8", realUrl);
			Elements list = book.select("iframe[data-play]");
			sb.append("第"+(i<10?"0"+i:i)+"集$");
			for(Element e:list) {
				LOGGER.info("{}",e.attr("data-play"));
				sb.append(e.attr("data-play"));
			}
			sb.append("#");
		}
		LOGGER.info("{}",sb);*/
		String s = "第01集$https://zy.kakazy-yun.com/20180830/juJdsEv8/index.m3u8#第02集$https://zy.kakazy-yun.com/20180830/OqoH7qF1/index.m3u8#第03集$https://zy.kakazy-yun.com/20180830/LXx158uj/index.m3u8#第04集$https://zy.kakazy-yun.com/20180830/lJoVLD8x/index.m3u8#第05集$https://zy.kakazy-yun.com/20180830/uhwJU7no/index.m3u8#第06集$https://zy.kakazy-yun.com/20180830/2sX6991L/index.m3u8#第07集$https://zy.kakazy-yun.com/20180830/Bl2aAm2j/index.m3u8#第08集$https://zy.kakazy-yun.com/20180831/LdvM4tA9/index.m3u8#第09集$https://zy.kakazy-yun.com/20180831/WUwNOJ4l/index.m3u8#第10集$https://zy.kakazy-yun.com/20180831/2HJpLMna/index.m3u8#第11集$https://zy.kakazy-yun.com/20180831/yIOJRhic/index.m3u8#第12集$https://zy.kakazy-yun.com/20180831/AOQwTltY/index.m3u8#第13集$https://zy.kakazy-yun.com/20180831/L9IknPaX/index.m3u8#第14集$https://zy.kakazy-yun.com/20180831/DRnD2BKN/index.m3u8#第15集$https://zy.kakazy-yun.com/20180831/pNmj1MKQ/index.m3u8#第16集$https://zy.kakazy-yun.com/20180831/63OyX5kz/index.m3u8#第17集$https://zy.kakazy-yun.com/20180831/tTSk7oB4/index.m3u8#第18集$https://zy.kakazy-yun.com/20180831/fg84a2tq/index.m3u8#第19集$https://zy.kakazy-yun.com/20180831/RsLQQ9OS/index.m3u8#第20集$https://zy.kakazy-yun.com/20180831/sOIasbTq/index.m3u8#第21集$https://zy.kakazy-yun.com/20180831/Rv7sR6JT/index.m3u8#第22集$https://zy.kakazy-yun.com/20180831/YyBtduDM/index.m3u8#第23集$https://zy.kakazy-yun.com/20180831/Tfss6uO5/index.m3u8#第24集$https://zy.kakazy-yun.com/20180831/TV4g57Em/index.m3u8#第25集$https://zy.kakazy-yun.com/20180831/6Llv2f5v/index.m3u8#第26集$https://zy.kakazy-yun.com/20180831/KQx4Lm4h/index.m3u8#第27集$https://zy.kakazy-yun.com/20180831/VutxL2cS/index.m3u8#第28集$https://zy.kakazy-yun.com/20180831/P7StB3li/index.m3u8#第29集$https://zy.kakazy-yun.com/20180831/VA2AHG5v/index.m3u8#第30集$https://zy.kakazy-yun.com/20180831/T7eVPHLf/index.m3u8#第31集$https://zy.kakazy-yun.com/20180831/7f1FQ7cK/index.m3u8#第32集$https://zy.kakazy-yun.com/20180831/BY9BDIak/index.m3u8#第33集$https://zy.kakazy-yun.com/20180831/8Koc5Kcv/index.m3u8#第34集$https://zy.kakazy-yun.com/20180831/QTX3zbYq/index.m3u8#第35集$https://zy.kakazy-yun.com/20180831/v75lrXxX/index.m3u8#第36集$https://zy.kakazy-yun.com/20180831/XmOKIM00/index.m3u8#第37集$https://zy.kakazy-yun.com/20180831/BwXlHpxe/index.m3u8#第38集$https://zy.kakazy-yun.com/20180831/SmXdaJ1l/index.m3u8#第39集$https://zy.kakazy-yun.com/20180831/3PFrimvj/index.m3u8#第40集$https://zy.kakazy-yun.com/20180831/GbYUqjSW/index.m3u8#第41集$https://zy.kakazy-yun.com/20180831/KdfbcgvF/index.m3u8#第42集$https://zy.kakazy-yun.com/20180831/vJwoj0m9/index.m3u8#第43集$https://zy.kakazy-yun.com/20180831/WEzgm44P/index.m3u8#第44集$https://zy.kakazy-yun.com/20180831/MytlweJf/index.m3u8#第45集$https://zy.kakazy-yun.com/20180831/fg8ZaiXr/index.m3u8#第46集$https://zy.kakazy-yun.com/20180831/EaVuowvt/index.m3u8#第47集$https://zy.kakazy-yun.com/20180831/YzRc5zTv/index.m3u8#第48集$https://zy.kakazy-yun.com/20180831/I16iZzdG/index.m3u8#第49集$https://zy.kakazy-yun.com/20180831/ZFisln1r/index.m3u8#第50集$https://zy.kakazy-yun.com/20180831/nJqZ8d8o/index.m3u8#第51集$https://zy.kakazy-yun.com/20180831/a3JVoeop/index.m3u8#第52集$https://zy.kakazy-yun.com/20180831/yISmFd79/index.m3u8#第53集$https://zy.kakazy-yun.com/20180831/EepnZ9QD/index.m3u8#第54集$https://zy.kakazy-yun.com/20180831/q7enTbVs/index.m3u8#第55集$https://zy.kakazy-yun.com/20180831/627vKLrb/index.m3u8#第56集$https://zy.kakazy-yun.com/20180831/BNr7zMnN/index.m3u8#第57集$https://zy.kakazy-yun.com/20180831/bx77lOXc/index.m3u8#第58集$https://zy.kakazy-yun.com/20180831/T8Pmag2y/index.m3u8#第59集$https://zy.kakazy-yun.com/20180831/aN0WLnhI/index.m3u8#第60集$https://zy.kakazy-yun.com/20180831/6SORvMXx/index.m3u8";
		for(String url:s.split("#")) {
			String real = "http://m.v5ent.com:9527/"+url.substring(5);
//			Document b = Jsoup.connect(real).get();
//			System.out.println(b);
			System.out.println(url.substring(5));
		}
	}
}
