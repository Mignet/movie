package com.v5ent.movie.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2019年09月17日 11:48:35
 * 
 * Copyright (c) 深圳米格云链网络科技有限公司-版权所有
 */
public class News {
	
    /**
     *
     */
	private Integer nId;
	
    /**
     *
     */
	private Short tid;
	
    /**
     *
     */
	private String nTitle;
	
    /**
     *
     */
	private String nPic;
	
    /**
     *
     */
	private Integer nHit;
	
    /**
     *
     */
	private Short nMoney;
	
    /**
     *
     */
	private Short nRank;
	
    /**
     *
     */
	private Short nDigg;
	
    /**
     *
     */
	private Short nTread;
	
    /**
     *
     */
	private Short nCommend;
	
    /**
     *
     */
	private String nAuthor;
	
    /**
     *
     */
	private String nColor;
	
    /**
     *
     */
	private Integer nAddtime;
	
    /**
     *
     */
	private Short nNote;
	
    /**
     *
     */
	private String nLetter;
	
    /**
     *
     */
	private Short nIsunion;
	
    /**
     *
     */
	private Short nRecycled;
	
    /**
     *
     */
	private String nEntitle;
	
    /**
     *
     */
	private String nOutline;
	
    /**
     *
     */
	private String nKeyword;
	
    /**
     *
     */
	private String nFrom;
	
    /**
     *
     */
	private Long nScore;
	
    /**
     *
     */
	private Integer nScorenum;
	
    /**
     *
     */
	private String nContent;
	


	public void setNId(Integer nId){
		this.nId = nId;
	}
	
	public Integer getNId(){
		return nId;
	}
	
	public void setTid(Short tid){
		this.tid = tid;
	}
	
	public Short getTid(){
		return tid;
	}
	
	public void setNTitle(String nTitle){
		this.nTitle = nTitle;
	}
	
	public String getNTitle(){
		return nTitle;
	}
	
	public void setNPic(String nPic){
		this.nPic = nPic;
	}
	
	public String getNPic(){
		return nPic;
	}
	
	public void setNHit(Integer nHit){
		this.nHit = nHit;
	}
	
	public Integer getNHit(){
		return nHit;
	}
	
	public void setNMoney(Short nMoney){
		this.nMoney = nMoney;
	}
	
	public Short getNMoney(){
		return nMoney;
	}
	
	public void setNRank(Short nRank){
		this.nRank = nRank;
	}
	
	public Short getNRank(){
		return nRank;
	}
	
	public void setNDigg(Short nDigg){
		this.nDigg = nDigg;
	}
	
	public Short getNDigg(){
		return nDigg;
	}
	
	public void setNTread(Short nTread){
		this.nTread = nTread;
	}
	
	public Short getNTread(){
		return nTread;
	}
	
	public void setNCommend(Short nCommend){
		this.nCommend = nCommend;
	}
	
	public Short getNCommend(){
		return nCommend;
	}
	
	public void setNAuthor(String nAuthor){
		this.nAuthor = nAuthor;
	}
	
	public String getNAuthor(){
		return nAuthor;
	}
	
	public void setNColor(String nColor){
		this.nColor = nColor;
	}
	
	public String getNColor(){
		return nColor;
	}
	
	public void setNAddtime(Integer nAddtime){
		this.nAddtime = nAddtime;
	}
	
	public Integer getNAddtime(){
		return nAddtime;
	}
	
	public void setNNote(Short nNote){
		this.nNote = nNote;
	}
	
	public Short getNNote(){
		return nNote;
	}
	
	public void setNLetter(String nLetter){
		this.nLetter = nLetter;
	}
	
	public String getNLetter(){
		return nLetter;
	}
	
	public void setNIsunion(Short nIsunion){
		this.nIsunion = nIsunion;
	}
	
	public Short getNIsunion(){
		return nIsunion;
	}
	
	public void setNRecycled(Short nRecycled){
		this.nRecycled = nRecycled;
	}
	
	public Short getNRecycled(){
		return nRecycled;
	}
	
	public void setNEntitle(String nEntitle){
		this.nEntitle = nEntitle;
	}
	
	public String getNEntitle(){
		return nEntitle;
	}
	
	public void setNOutline(String nOutline){
		this.nOutline = nOutline;
	}
	
	public String getNOutline(){
		return nOutline;
	}
	
	public void setNKeyword(String nKeyword){
		this.nKeyword = nKeyword;
	}
	
	public String getNKeyword(){
		return nKeyword;
	}
	
	public void setNFrom(String nFrom){
		this.nFrom = nFrom;
	}
	
	public String getNFrom(){
		return nFrom;
	}
	
	public void setNScore(Long nScore){
		this.nScore = nScore;
	}
	
	public Long getNScore(){
		return nScore;
	}
	
	public void setNScorenum(Integer nScorenum){
		this.nScorenum = nScorenum;
	}
	
	public Integer getNScorenum(){
		return nScorenum;
	}
	
	public void setNContent(String nContent){
		this.nContent = nContent;
	}
	
	public String getNContent(){
		return nContent;
	}
	

	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

