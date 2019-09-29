package com.v5ent.movie.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.Type;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.mapper.DescMapper;
import com.v5ent.movie.mapper.PlaydataMapper;
import com.v5ent.movie.mapper.TypeMapper;
import com.v5ent.movie.service.MovieService;

@RestController
public class MovieController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	
	@Resource
	private MovieService movieService;
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
		LOGGER.info("/movie/{}",vid);
		Data d = dataDao.selectByPrimaryKey(vid);
		d.setBody(playDao.selectByPrimaryKey(vid).getBody());
		d.setDesc(descDao.selectByPrimaryKey(vid).getBody());
		return d;
	}
	
	@GetMapping("/type/{tid}/movie")
	public List<Data> selectByType(@PathVariable("tid") long tid) {
		return dataDao.selectTop60ByTid(tid);
	}
	
	@GetMapping("/movie")
	public Map<String, List<Data>> index() {
		Map<String,List<Data>> map = new TreeMap<>();
		for(Type type:typeDao.selectTop()) {
			map.put(type.getTid()+","+type.getTname(), dataDao.top9(type.getTid()));
		}
		return map;
	}
	
	@PostMapping("movie")
	public String spiderMovies() throws IOException {
		StringBuilder result = new StringBuilder("ok");
		movieService.collect(result,1);
		return result.toString();
	}
	
}
