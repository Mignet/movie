package com.v5ent.movie.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.Desc;
import com.v5ent.movie.entity.News;
import com.v5ent.movie.entity.Playdata;
import com.v5ent.movie.entity.vo.UrlVo;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.mapper.DescMapper;
import com.v5ent.movie.mapper.NewsMapper;
import com.v5ent.movie.mapper.PlaydataMapper;
import com.v5ent.movie.mapper.TypeMapper;
import com.v5ent.movie.utils.CharUtils;

@Service
public class SpiderService {
	
	@Resource
	private DataMapper dataDao;
	@Resource
	private TypeMapper typeDao;
	@Resource
	private PlaydataMapper playDao;
	@Resource
	private DescMapper descDao;
	@Resource
	private NewsMapper newsDao;

	@Value("${biz.spider.source}")
	private String source;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpiderService.class);
	
	private List<Map<String, String>> uniontypeList = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int currentPage = 1;int pagecount = 10;
//	private static String SOURCE = "http://api.iokzy.com/inc/apickm3u8.php";
//	private static String SOURCE1 = "http://www.zdziyuan.com/inc/s_ldgm3u8_sea.php";
	
	public void spiderMovies(StringBuilder result,int page) throws IOException {
		if(uniontypeList==null) {
			uniontypeList = typeDao.selectUnionTypes();
		}
		//http://www.zdziyuan.com/inc/s_ldgm3u8_sea.php
		//wd=keysearch
		//http://api.iokzy.com/inc/apickm3u8.php?ac=videolist&ids=3189
		Document doc = Jsoup.connect(source+"?ac=videolist&t=0&h=24&pg="+page).get();//&t=0&h=24
        Elements playlist = doc.getElementsByTag( "list" );
        if(playlist!=null && playlist.isEmpty()) {
        	LOGGER.error(source+"?ac=videolist&pg={}\r\n{}", page, doc.toString());
        	try {
				Thread.sleep(1000L);
				spiderMovies(result,currentPage);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        }
		Element list = playlist.get(0);
        currentPage = Integer.valueOf(list.attr("page"));
        pagecount =  Integer.valueOf(list.attr("pagecount"));
        LOGGER.info("currentPage[{}],pagecount[{}],pagesize[{}],recordcount[{}]",currentPage,pagecount,list.attr("pagesize"),list.attr("recordcount"));
        Elements videos = list.getElementsByTag("video");
        for(Element e:videos) {
        	Data d = new Data();
//        	d.setVId(Integer.valueOf(e.selectFirst("id").text()));
        	String tid = convert("3_"+e.selectFirst("tid").text());
        	if(tid==null) {
        		result.append(e.selectFirst("name").text()+"=没有绑类型["+e.selectFirst("tid").text()+"]-["+e.selectFirst("type").text()+"]<br>");
        		continue;
        	}else {
        		d.setTid(Short.valueOf(tid));
        	}
        	try {
        		int lastTime = (int)(sdf.parse(e.selectFirst("last").text()).getTime()/1000);
				d.setVAddtime(lastTime);
			} catch (ParseException e1) {
				e1.printStackTrace();
				d.setVAddtime((int)(System.currentTimeMillis()/1000));
			}
        	d.setVName(e.selectFirst("name").text());
//        	d.settype(e.selectFirst("type").text());
        	d.setVPic(e.selectFirst("pic").text());
        	d.setVLang(e.selectFirst("lang").text());
        	d.setVPublisharea(e.selectFirst("area").toString().replace("<area>", "").replace("</area>", ""));
        	d.setVPublishyear(Integer.valueOf(e.selectFirst("year").text()));
        	String nickName = "";
			try {
				nickName = CharUtils.getFirstSpell(d.getVName());
			} catch (Exception e1) {
				LOGGER.error(d.getVName()+" error:",e1);
			}
        	d.setVNickname(nickName);
//        	d.setstate(e.selectFirst("state").text());
        	d.setVNote(e.selectFirst("note").text());
        	d.setVActor(e.selectFirst("actor").text());
        	if(d.getVActor().length()>200) {
        		LOGGER.error(d.getVActor());
        		d.setVActor(d.getVActor().substring(0, 200));
        	}
        	d.setVDirector(e.selectFirst("director").text());
        	d.setBody("酷M3U8$$"+e.selectFirst("dl>dd").text().replace("$zuidam3u8",""));
        	d.setDesc(e.selectFirst("des").text());
   
        	Data t = dataDao.selectOneByCondition(new Data() {{setVName(d.getVName());}});
        	if(t!=null) {
        		if(d.getVAddtime()>t.getVAddtime()) {
        			result.append("<a href='playlist.html?vid="+t.getVId()+"'>"+d.getVName()+"</a>=已存在，只更新数据<br>");
        			dataDao.updateByPrimaryKeySelective(new Data() {{
        				setVId(t.getVId());
        				setVAddtime(d.getVAddtime());
        				setVNote(t.getVNote());
        				setVPic(d.getVPic());
        			}});
        			playDao.updateByPrimaryKeySelective(new Playdata() {{
        				setVId(t.getVId());
        				setBody(d.getBody());
        			}});
        		}else {
        			result.append(d.getVName()+"=已存在，时间较早不需要更新<br>");
        		}
        	}else {
        		dataDao.insert(d);
        		Integer vid = dataDao.getMaxId();
        		result.append("<a href='playlist.html?vid="+vid+"'>"+d.getVName()+"</a>=不存在，新增数据<br>");
        		playDao.insert(new Playdata() {{
        			setVId(vid);
        			setTid(d.getTid());
        			setBody(d.getBody());
        		}});
        		descDao.insert(new Desc() {{
        			setVId(vid);
        			setTid(d.getTid());
        			setBody(d.getDesc());
        		}});
        	}
        }
        currentPage++;
        if(currentPage<=pagecount) {
        	result.append("===========准备抓第"+currentPage+"页============<br>");
        	try {
				Thread.sleep(1000L);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
        	spiderMovies(result,currentPage);
        }else {
        	LOGGER.info("OK");
        }
	}

	private String convert(String tid) {
		for(Map<String,String> m:uniontypeList) {
			if(m.get("unionid").contains(tid)) {
				return String.valueOf(m.get("tid"));
			}
		}
		return null;
	}
	//https://www.xiangcunxiaoshuo.la/html/617693/
	private static Map<String,String> map = new HashMap<>();
	static{
		map.put("21","https://www.xiangcunxiaoshuo.la/html/617693/");
		map.put("22","https://www.xiangcunxiaoshuo.la/html/258/");
		map.put("23","https://www.xiangcunxiaoshuo.la/html/451218/");
		map.put("24","https://www.xiangcunxiaoshuo.la/html/537365/");
		map.put("25","https://www.xiangcunxiaoshuo.la/html/417594/");
		map.put("26","https://www.xiangcunxiaoshuo.la/html/573964/");
	}
	public String spiderNews(UrlVo vo) throws IOException {
		StringBuilder result = new StringBuilder("ok");
		boolean startFlag = false;
		Document book = Jsoup.connect(vo.getUrl()).get();
		Elements list = book.select("div.box_con>div#list>dl>dd>a");
		if(list==null || list.isEmpty()) {
			result.append("目录不存在,尝试新解");
			
			list = book.select("body > section.ml_main>dl>dd>a");
			if(list==null || list.isEmpty()) {
				result.append(">新解目录不存在");
				return result.toString();
			}
		}
		String lastTitle = newsDao.selectTitleByTid(vo.getId());
		for (Element e : list) {
			String link = e.attr("abs:href").replace("\r\n", "").replace("\t", "");
			String title = e.text();
			if (lastTitle != null && title!=null && CharUtils.replace(lastTitle).equals(CharUtils.replace(title))) {
					startFlag = true;
			} else if ((lastTitle == null) || (startFlag)) {
				LOGGER.info("title:[{}],link:[{}]",title,link);
				Document page = Jsoup.connect(link).get();
				Elements content = page.select("#content");
				String ctx = content.html();
				if(StringUtils.isEmpty(ctx)) {
					ctx = page.select(".yd_text2").html();
				}
				News n = new News();
				n.setTid(Short.valueOf(vo.getId()));
				n.setNTitle(title);
				n.setNAddtime((int) (System.currentTimeMillis() / 1000L));
				n.setNLetter("Hx");
				n.setNIsunion((short) 0);
				n.setNRecycled((short) 0);
				n.setNTitle(title);
				n.setNOutline(title);
				n.setNFrom(link);
				n.setNContent(ctx);
				newsDao.insertNews(n);
				int nId = newsDao.getMaxId();
				result.append("\r\n<a href='book.html?nid="+nId+"'>title:[" + title + "][" + link + "]</a>");
			}else {
				LOGGER.warn("title:[{}] lastTitle:[{}]", title,lastTitle);
			}
		}
		return result.toString();
	}
	public static void main(String[] args) throws MalformedURLException, IOException {
		//phantomjs server.js 9527
		String realUrl = "http://m.v5ent.com:9527/http://www.my2852.com/gdwx/jpsh/03.htm";
		Document book = Jsoup.parse(new URL(realUrl).openStream(), "UTF-8", realUrl);
		Elements list = book.select("body > center:nth-child(4) > table > tbody > tr");
		//<table border="0" cellspacing="1" style="font-size: 12pt; font-family: 宋体; line-height:150%"><tbody>
		System.out.println(list.get(1).toString().replace("15pt", "14pt"));
//		System.out.println(list.get(3));
		//</tbody></table>
	}
}
