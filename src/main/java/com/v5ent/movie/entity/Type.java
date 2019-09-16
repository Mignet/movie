package com.v5ent.movie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 import java.lang.Byte;
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
public class Type implements Serializable {
	
	private static final long serialVersionUID = 4150763326651404782L;
	
    /**
     *
     */
	private Short tid;
	
    /**
     *
     */
	private Byte upid;
	
    /**
     *
     */
	private String tname;
	
    /**
     *
     */
	private String tenname;
	
    /**
     *
     */
	private Integer torder;
	
    /**
     *
     */
	private String templist;
	
    /**
     *
     */
	private String templist1;
	
    /**
     *
     */
	private String templist2;
	
    /**
     *
     */
	private String title;
	
    /**
     *
     */
	private String keyword;
	
    /**
     *
     */
	private String description;
	
    /**
     *
     */
	private Short ishidden;
	
    /**
     *
     */
	private String unionid;
	
    /**
     *
     */
	private Short tptype;
	


	public void setTid(Short tid){
		this.tid = tid;
	}
	
	public Short getTid(){
		return tid;
	}
	
	public void setUpid(Byte upid){
		this.upid = upid;
	}
	
	public Byte getUpid(){
		return upid;
	}
	
	public void setTname(String tname){
		this.tname = tname;
	}
	
	public String getTname(){
		return tname;
	}
	
	public void setTenname(String tenname){
		this.tenname = tenname;
	}
	
	public String getTenname(){
		return tenname;
	}
	
	public void setTorder(Integer torder){
		this.torder = torder;
	}
	
	public Integer getTorder(){
		return torder;
	}
	
	public void setTemplist(String templist){
		this.templist = templist;
	}
	
	public String getTemplist(){
		return templist;
	}
	
	public void setTemplist1(String templist1){
		this.templist1 = templist1;
	}
	
	public String getTemplist1(){
		return templist1;
	}
	
	public void setTemplist2(String templist2){
		this.templist2 = templist2;
	}
	
	public String getTemplist2(){
		return templist2;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	public String getKeyword(){
		return keyword;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setIshidden(Short ishidden){
		this.ishidden = ishidden;
	}
	
	public Short getIshidden(){
		return ishidden;
	}
	
	public void setUnionid(String unionid){
		this.unionid = unionid;
	}
	
	public String getUnionid(){
		return unionid;
	}
	
	public void setTptype(Short tptype){
		this.tptype = tptype;
	}
	
	public Short getTptype(){
		return tptype;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

