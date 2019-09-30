package com.v5ent.movie.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpiderService.class);
	
	private List<Map<String, String>> uniontypeList = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private int currentPage = 1;int pagecount = 10;
	
	public void spiderMovies(StringBuilder result,int page) throws IOException {
		if(uniontypeList==null) {
			uniontypeList = typeDao.selectUnionTypes();
		}
		Document playlist = Jsoup.connect("http://api.iokzy.com/inc/apickm3u8.php?ac=videolist&t=0&h=24&&pg="+page).get();
        Element list = playlist.getElementsByTag( "list" ).get(0);
        currentPage = Integer.valueOf(list.attr("page"));
        pagecount =  Integer.valueOf(list.attr("pagecount"));
        LOGGER.info("currentPage[{}],pagecount[{}],pagesize[{}],recordcount[{}]",currentPage,pagecount,list.attr("pagesize"),pagecount,list.attr("pagecount"));
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
        	d.setVPublisharea(e.selectFirst("area").toString().replace("<area>", ""));
        	d.setVPublishyear(Integer.valueOf(e.selectFirst("year").text()));
//        	d.setstate(e.selectFirst("state").text());
        	d.setVNote(e.selectFirst("note").text());
        	d.setVActor(e.selectFirst("actor").text());
        	if(d.getVActor().length()>200) {
        		LOGGER.error(d.getVActor());
        		d.setVActor(d.getVActor().substring(0, 200));
        	}
        	d.setVDirector(e.selectFirst("director").text());
        	d.setBody("酷M3U8$$"+e.selectFirst("dl>dd").text());
        	d.setDesc(e.selectFirst("des").text());
   
        	Data t = dataDao.selectOneByCondition(new Data() {{setVName(d.getVName());}});
        	if(t!=null) {
        		if(d.getVAddtime()>t.getVAddtime()) {
        			result.append("<a href='playlist.html?vid="+t.getVId()+"'>"+d.getVName()+"</a>=已存在，只更新数据<br>");
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
	private static String replace(String str) {
		char[] chars = str.toCharArray(); 
		StringBuffer buffer=new StringBuffer();
		for(int i = 0; i < chars.length; i ++) { 
			if((chars[i] >= 19968 && chars[i] <= 40869) || (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90)) { 
				buffer.append(chars[i]);
			} 
		} 
		return buffer.toString();
	}
	
	public String spiderNews(UrlVo vo) throws IOException {
		StringBuilder result = new StringBuilder("ok");
		String lastTitle = newsDao.selectTitleByTid(vo.getId());
		boolean startFlag = false;
		Document book = Jsoup.connect(vo.getUrl()).get();
		Elements list = book.select("div.box_con>div#list>dl>dd>a");
		if(list==null || list.isEmpty()) {
			result.append("目录不存在");
		}
		for (Element e : list) {
			String link = e.attr("abs:href").replace("\r\n", "").replace("\t", "");
			String title = e.text();
			if (lastTitle != null && title!=null && replace(lastTitle).equals(replace(title))) {
					startFlag = true;
			} else if ((lastTitle == null) || (startFlag)) {
				LOGGER.info("title:[{}],link:[{}]",title,link);
				Document page = Jsoup.connect(link).get();
				Elements content = page.select("#content");

				String ctx = content.html();
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
				LOGGER.debug("title:[{}] lastTitle:[{}]", title,lastTitle);
			}
		}
		return result.toString();
	}
}
