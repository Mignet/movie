package com.v5ent.movie.controller;

import java.io.IOException;
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

import com.v5ent.movie.entity.News;
import com.v5ent.movie.entity.Type;
import com.v5ent.movie.mapper.NewsMapper;
import com.v5ent.movie.mapper.TypeMapper;
import com.v5ent.movie.vo.UrlVo;

@RestController
public class NewsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);
	@Resource
	private NewsMapper newsDao;
	@Resource
	private TypeMapper typeDao;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping("book")
	public String spiderNews(@RequestBody UrlVo vo) throws IOException {
		StringBuilder result = new StringBuilder("ok");
		String lastTitle = newsDao.selectTitleByTid(vo.getId());
		boolean startFlag = false;
		Document book = Jsoup.connect(vo.getUrl()).get();
		Elements list = book.select("div.box_con>div#list>dl>dd>a");
		for (Element e : list) {
			String link = e.attr("abs:href").replace("\r\n", "").replace("\t", "");
			String title = e.text();
			LOGGER.debug("title:[" + title + "]");
			if ((lastTitle != null) && (lastTitle.equals(title))) {
				startFlag = true;
			} else if ((lastTitle == null) || (startFlag)) {
				LOGGER.info("[" + link + "]");
				result.append("\r\ntitle:[" + title + "][" + link + "]");
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
			}
		}
		return result.toString();
	}
	
	@GetMapping("book/{id}")
	public String readNews(@PathVariable("id") String id) {
		return newsDao.getContentById(id);
	}
	
	@GetMapping("books/{tid}")
	public List<News> readNewsList(@PathVariable("tid") String tid) {
		return newsDao.getListByTid(tid);
	}
	
	@GetMapping("books")
	public Map<String,List<News>> readNewsTypes() {
		Map<String,List<News>> map = new TreeMap<>();
		for(Type type:typeDao.selectBooks()) {
			map.put(type.getTid()+","+type.getTname(), newsDao.getListByTid(String.valueOf(type.getTid())));
		}
		return map;
	}

}
