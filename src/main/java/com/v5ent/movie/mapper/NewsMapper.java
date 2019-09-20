package com.v5ent.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.v5ent.movie.entity.News;

@Mapper
public interface NewsMapper{
	
	@Select("SELECT n_title FROM sea_news where n_id = (select max(n_id) from sea_news where tid=#{tid})")
	String selectTitleByTid(String tid);
	
	@Insert("INSERT INTO sea_news  " + 
			"(`tid`,`n_title`,`n_addtime`,`n_letter`,`n_isunion`,`n_recycled`,`n_entitle`,`n_outline`,`n_from`,`n_content`) " + 
			"VALUES " + 
			"(#{tid},#{nTitle},#{nAddtime},#{nLetter},#{nIsunion},#{nRecycled},#{nEntitle},#{nOutline},#{nFrom},#{nContent})")
	int insertNews(News news);

	@Select("SELECT n_content from sea_news where n_id=#{id}")
	String getContentById(String id);

	@Select("select n_id as nId,tid,n_title as nTitle from sea_news where tid=#{tid} order by n_addtime desc limit 20")
	List<News> getListByTid(String tid);
}
