package com.v5ent.movie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.v5ent.movie.entity.Playdata;

@Mapper
public interface PlaydataMapper {

	Playdata selectByPrimaryKey(long vid);

	int updateByPrimaryKeySelective(Playdata playdata);

	int insert(Playdata playdata);

}
