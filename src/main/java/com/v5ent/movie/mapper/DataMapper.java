package com.v5ent.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.v5ent.movie.entity.Data;

@Mapper
public interface DataMapper {

	List<Data> search(String keywords);
	
	List<Data> top9(long tid);

}
