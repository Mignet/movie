package com.v5ent.movie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.Desc;
import com.v5ent.movie.entity.Playdata;
import com.v5ent.movie.entity.Type;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.mapper.DescMapper;
import com.v5ent.movie.mapper.PlaydataMapper;
import com.v5ent.movie.mapper.TypeMapper;

@RestController
public class MovieController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	
	@Resource
	private DataMapper dataDao;
	@Resource
	private TypeMapper typeDao;
	@Resource
	private PlaydataMapper playDao;
	@Resource
	private DescMapper descDao;
	
	@PostMapping("/movie/search")
	public List<Data> search(@RequestBody String keywords) {
		if(keywords == null || "".equals(keywords.trim())) {
			return new ArrayList<>();
		}
		return dataDao.search(keywords);
	}
	
	@GetMapping("/movie/{vid}")
	public Data selectByPrimaryKey(@PathVariable("vid") long vid) {
		Data d = dataDao.selectByPrimaryKey(vid);
		d.setBody(playDao.selectByPrimaryKey(vid).getBody());
		return d;
	}
	
	@GetMapping("/movie")
	public Map<String, List<Data>> index() {
		Map<String,List<Data>> map = new TreeMap<>();
		for(Type type:typeDao.selectTop()) {
			map.put(type.getTid()+","+type.getTname(), dataDao.top9(type.getTid()));
		}
		return map;
	}
	
	String convert(String tid) {
		for(Map<String,String> m:uniontypeList) {
			if(m.get("unionid").contains(tid)) {
				return String.valueOf(m.get("tid"));
			}
		}
		return null;
	}
	private List<Map<String, String>> uniontypeList = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	int currentPage = 1;int pagecount = 10;
	@PostMapping("movie")
	public String spiderMovies() throws IOException {
		StringBuilder result = new StringBuilder("ok");
		if(uniontypeList==null) {
			uniontypeList = typeDao.selectUnionTypes();
		}
		collect(result,1);
		return result.toString();
	}
	public void collect(StringBuilder result,int page) throws IOException {
		Document playlist = Jsoup.connect("http://api.iokzy.com/inc/apickm3u8.php?ac=videolist&t=0&h=24&&pg="+page).get();
		 //3.获取元素对象Element，返回值是存了Element对象的ArrayList集合
        Element list = playlist.getElementsByTag( "list" ).get(0);
        currentPage = Integer.valueOf(list.attr("page"));
        pagecount =  Integer.valueOf(list.attr("pagecount"));
        LOGGER.info("currentPage[{}],pagecount[{}],pagesize[{}],recordcount[{}]",currentPage,list.attr("pagesize"),list.attr("recordcount"));
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
        	d.setBody(e.selectFirst("dl>dd").text());
        	d.setDesc(e.selectFirst("des").text());
   
        	Data t = dataDao.selectOneByCondition(new Data() {{setVName(d.getVName());}});
        	if(t!=null) {
        		if(d.getVAddtime()>t.getVAddtime()) {
        			result.append(d.getVName()+"=已存在，只更新数据<br>");
        			playDao.updateByPrimaryKeySelective(new Playdata() {{
        				setVId(t.getVId());
        				setBody(d.getBody());
        			}});
        		}else {
        			result.append(d.getVName()+"=已存在，时间较早不需要更新<br>");
        		}
        	}else {
        		result.append(d.getVName()+"=不存在，新增数据<br>");
        		dataDao.insert(d);
        		Integer vid = dataDao.getMaxId();
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
        	collect(result,currentPage);
        }else {
        	LOGGER.info("OK");
        }
	}
	
}
