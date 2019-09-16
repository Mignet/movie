package com.v5ent.movie;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.v5ent.movie.mapper.DataMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Resource
	private DataMapper dataDao;
 
	@Test
	public void testSearch() throws Exception {
		System.out.println(dataDao.search("哈利"));
	}

}
