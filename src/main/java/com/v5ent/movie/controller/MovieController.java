package com.v5ent.movie.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.Playdata;
import com.v5ent.movie.entity.Type;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.mapper.PlaydataMapper;
import com.v5ent.movie.mapper.TypeMapper;

@RestController
public class MovieController {
	@Resource
	private DataMapper dataDao;
	@Resource
	private TypeMapper typeDao;
	@Resource
	private PlaydataMapper playDao;
	
	@PostMapping("/movie/search")
	public List<Data> search(@RequestBody String keywords) {
		if(keywords == null || "".equals(keywords.trim())) {
			return new ArrayList<>();
		}
		return dataDao.search(keywords);
	}
	
	@PostMapping("/movie/{vid}")
	public Playdata top9(@PathVariable("vid") long vid) {
		return playDao.selectByPrimaryKey(vid);
	}
	
	@PostMapping("/movie/home")
	public Map<String, List<Data>> index() {
		Map<String,List<Data>> map = new HashMap<>();
		for(Type type:typeDao.selectTop()) {
			map.put(type.getTid()+","+type.getTname(), dataDao.top9(type.getTid()));
		}
		return map;
	}
	
}
