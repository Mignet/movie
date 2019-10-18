package com.v5ent.movie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
import com.v5ent.movie.entity.vo.UrlVo;
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
	public void testHLM() {
		for(News n:newsDao.selectAllByTid(25)) {
			StringBuilder sb = new StringBuilder("<table border=\"0\" cellspacing=\"1\" style=\"font-size: 12pt; font-family: 宋体; line-height:150%\">\n" + 
					"			<tbody>\n" + 
					"			<tr>\n" + 
					"				<td height=\"50\" style=\"font-size: 14pt; font-family: 黑体; color: #B10000; \" align=\"center\">");
			sb.append(n.getNTitle());
			sb.append("</td>\n" + 
					"			</tr>\n" + 
					"			\n" + 
					"<tr>\n" + 
					"<td>");
			sb.append(n.getNContent().replace("<script>chaptererror();</script>", ""))
			.append("</td></tr>\n" + 
					"		</tbody></table>");
			newsDao.updateByPrimaryKeySelective(new News() {{
				setNId(n.getNId());
				setNContent(sb.toString());
			}});
		}
	}
	
	@Test
	public void testSHZ() throws IOException {
		Integer nId = 8363;
		for(int i=4;i<=70;i++) {
			nId = newsDao.getNextIdByNid(""+nId);
			doex(nId,i);
		}
	}
	
	/**
	 * jp shz
	 * @param nId
	 * @param i
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void doex(int nId,int i) throws MalformedURLException, IOException {
		String s = i<10?"0"+i:""+i;
		String realUrl = "http://m.v5ent.com:9527/http://www.my2852.com/gdwx/jpsh/"+s+".htm";
		Document book = Jsoup.parse(new URL(realUrl).openStream(), "UTF-8", realUrl);
		Elements list = book.select("body > center:nth-child(4) > table > tbody > tr");
		StringBuilder sb = new StringBuilder("");
		sb.append("<table border=\"0\" cellspacing=\"1\" style=\"font-size: 12pt; font-family: 宋体; line-height:150%\"><tbody>");
		sb.append(list.get(1).toString().replace("15pt", "14pt"));
		sb.append(list.get(3));
		sb.append("</tbody></table>");
		LOGGER.info("{}-{}", nId,i);
		newsDao.updateByPrimaryKeySelective(new News() {{
			setNId(nId);
			setNFrom("http://www.my2852.com/gdwx/jpsh/"+s+".htm");
			setNContent(sb.toString());
		}});
	}
}
