package com.v5ent.movie;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.v5ent.movie.controller.NewsController;
import com.v5ent.movie.mapper.DataMapper;
import com.v5ent.movie.vo.UrlVo;

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
 
	@Test
	public void testSearch() throws Exception {
		System.out.println(dataDao.search("哈利"));
	}
	
	@Test
	public void testSpider() throws Exception {
		UrlVo vo = new UrlVo();
		vo.setUrl("https://www.ddxs.cc/ddxs/149165/");
		vo.setId("21");
		System.out.println(spiderController.spiderNews(vo));
	}
	
	

}
