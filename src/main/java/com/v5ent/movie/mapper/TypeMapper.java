package com.v5ent.movie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.v5ent.movie.entity.Type;

@Mapper
public interface TypeMapper {
	List<Type> selectTop();
	
	@Select("SELECT * FROM sea_type WHERE tid BETWEEN 21 AND 26")
	List<Type> selectBooks();

	@Select("select tid , unionid from sea_type where unionid <> ''")
	List<Map<String,String>> selectUnionTypes();
}
