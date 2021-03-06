package com.v5ent.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.v5ent.movie.entity.Data;

@Mapper
public interface DataMapper {

	int insert(Data data);
	
	List<Data> search(String keywords);
	
	List<Data> top9(long tid);

	Data selectOneByCondition(Data d);
	
	List<Data> selectByCondition(Data d);
	
	Data selectByPrimaryKey(long vid);
	
	List<Data> selectTop60ByTid(long tid);

	@Select("SELECT max(v_id) FROM `sea_data`")
	Integer getMaxId();
	
	@Select("SELECT max(v_hit) FROM `sea_data`")
	Integer getMaxVhit();

	int updateByPrimaryKeySelective(Data data);

}
