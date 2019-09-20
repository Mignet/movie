package com.v5ent.movie.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.v5ent.movie.entity.Desc;

@Mapper
public interface DescMapper {

	@Insert("INSERT INTO sea_content(`v_id`, `tid`, `body`) VALUES (#{vId},#{tid},#{body})")
	int insert(Desc desc);


}
