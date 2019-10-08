package com.v5ent.movie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.v5ent.movie.entity.News;
import com.v5ent.movie.entity.Type;
import com.v5ent.movie.entity.vo.UrlVo;
import com.v5ent.movie.mapper.NewsMapper;
import com.v5ent.movie.mapper.TypeMapper;
import com.v5ent.movie.service.SpiderService;

@RestController
public class NewsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);
	@Resource
	private SpiderService spider;
	@Resource
	private NewsMapper newsDao;
	@Resource
	private TypeMapper typeDao;
	
	@PostMapping("book")
	public String spiderNews(@RequestBody UrlVo vo) throws IOException {
		return spider.spiderNews(vo);
	}
	
	@GetMapping("book/{id}")
	public Map<String,String> readNews(@PathVariable("id") String id) {
		Map<String,String> m = new HashMap<>();
		m.put("body",newsDao.getContentById(id));
		m.put("nextId",String.valueOf(newsDao.getNextIdByTid(id)));
		return m;
	}
	
	@GetMapping("books/{tid}")
	public List<News> readNewsList(@PathVariable("tid") String tid) {
		return newsDao.getListByTid(tid);
	}
	
	@GetMapping("/type/{tid}/news")
	public List<News> selectByType(@PathVariable("tid") long tid) {
		return newsDao.selectAllByTid(tid);
	}
	
	@GetMapping("books")
	public Map<String,List<News>> home() {
		LOGGER.debug("books home");
		Map<String,List<News>> map = new TreeMap<>();
		for(Type type:typeDao.selectBooks()) {
			map.put(type.getTid()+","+type.getTname(), newsDao.getListByTid(String.valueOf(type.getTid())));
		}
		return map;
	}

}
