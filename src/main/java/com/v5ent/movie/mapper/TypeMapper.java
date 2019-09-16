package com.v5ent.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.v5ent.movie.entity.Type;

@Mapper
public interface TypeMapper {
	List<Type> selectTop();
}
