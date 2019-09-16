package com.v5ent.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.v5ent.movie.entity.Data;

@Mapper
public interface DataMapper {

//	@Select("SELECT * FROM sea_data WHERE v_name LIKE CONCAT('%','${keywords}','%' )")
	List<Data> search(String keywords);
	
	List<Data> top9(long tid);

}
