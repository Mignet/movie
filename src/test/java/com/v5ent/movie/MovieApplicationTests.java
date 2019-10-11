package com.v5ent.movie;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.v5ent.movie.controller.NewsController;
import com.v5ent.movie.entity.Data;
import com.v5ent.movie.entity.vo.UrlVo;
import com.v5ent.movie.mapper.DataMapper;
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
	private NewsController spiderController;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieApplicationTests.class);
 
	@Test
	public void testSearch() throws Exception {
		LOGGER.info("[]",dataDao.search("哈利"));
	}
	
	@Test
	public void testSpider() throws Exception {
		UrlVo vo = new UrlVo();
		vo.setUrl("https://www.xiangcunxiaoshuo.la/html/617693/");
		vo.setId("21");
		LOGGER.info(spiderController.spiderNews(vo));
	}
	
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
	
	

}
