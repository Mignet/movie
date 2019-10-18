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

	News selectByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(News data);
	
	@Select("select n_id as nId,tid,n_title as nTitle from sea_news where tid=#{tid} order by n_id desc limit 20")
	List<News> getListByTid(String tid);
	
	@Select("select n_id from `sea_news` where tid = (SELECT tid FROM `sea_news` where n_id = #{nid}) and n_id>#{nid} ORDER BY n_id LIMIT 1")
	Integer getNextIdByNid(String nid);

	@Select("SELECT max(n_id) FROM `sea_news`")
	Integer getMaxId();

	@Select("SELECT n_id as nId,tid,n_title as nTitle,n_content as nContent FROM `sea_news` where tid=#{tid} order by n_id")
	List<News> selectAllByTid(long tid);
}
