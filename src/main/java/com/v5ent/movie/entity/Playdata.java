package com.v5ent.movie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Integer;
 import java.lang.Short;
 import java.lang.String;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2019年09月12日 16:53:40
 * 
 * Copyright (c) 深圳米格云链网络科技有限公司-版权所有
 */
public class Playdata implements Serializable {
	
	private static final long serialVersionUID = -4780743337060214380L;
	
    /**
     *
     */
	private Integer vId;
	
    /**
     *
     */
	private Short tid;
	
    /**
     *
     */
	private String body;
	
    /**
     *
     */
	private String body1;
	


	public void setVId(Integer vId){
		this.vId = vId;
	}
	
	public Integer getVId(){
		return vId;
	}
	
	public void setTid(Short tid){
		this.tid = tid;
	}
	
	public Short getTid(){
		return tid;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public String getBody(){
		return body;
	}
	
	public void setBody1(String body1){
		this.body1 = body1;
	}
	
	public String getBody1(){
		return body1;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

