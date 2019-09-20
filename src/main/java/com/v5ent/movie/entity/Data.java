package com.v5ent.movie.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
public class Data {
	
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
	private String vName;
	
    /**
     *
     */
	private Integer vState;
	
    /**
     *
     */
	private String vPic;
	
    /**
     *
     */
	private String vSpic;
	
    /**
     *
     */
	private String vGpic;
	
    /**
     *
     */
	private Integer vHit;
	
    /**
     *
     */
	private Short vMoney;
	
    /**
     *
     */
	private Short vRank;
	
    /**
     *
     */
	private Short vDigg;
	
    /**
     *
     */
	private Short vTread;
	
    /**
     *
     */
	private Short vCommend;
	
    /**
     *
     */
	private Short vWrong;
	
    /**
     *
     */
	private Short vIsmake;
	
    /**
     *
     */
	private String vActor;
	
    /**
     *
     */
	private String vColor;
	
    /**
     *
     */
	private Integer vPublishyear;
	
    /**
     *
     */
	private String vPublisharea;
	
    /**
     *
     */
	private Integer vAddtime;
	
    /**
     *
     */
	private Integer vTopic;
	
    /**
     *
     */
	private String vNote;
	
    /**
     *
     */
	private String vTags;
	
    /**
     *
     */
	private String vLetter;
	
    /**
     *
     */
	private Short vIsunion;
	
    /**
     *
     */
	private Short vRecycled;
	
    /**
     *
     */
	private String vDirector;
	
    /**
     *
     */
	private String vEnname;
	
    /**
     *
     */
	private String vLang;
	
    /**
     *
     */
	private Integer vScore;
	
    /**
     *
     */
	private Integer vScorenum;
	
    /**
     *
     */
	private String vExtratype;
	
    /**
     *
     */
	private String vJq;
	
    /**
     *
     */
	private String vNickname;
	
    /**
     *
     */
	private String vReweek;
	
    /**
     *
     */
	private Float vDouban;
	
    /**
     *
     */
	private Float vMtime;
	
    /**
     *
     */
	private Float vImdb;
	
    /**
     *
     */
	private String vTvs;
	
    /**
     *
     */
	private String vCompany;
	
    /**
     *
     */
	private Integer vDayhit;
	
    /**
     *
     */
	private Integer vWeekhit;
	
    /**
     *
     */
	private Integer vMonthhit;
	
    /**
     *
     */
	private Integer vDaytime;
	
    /**
     *
     */
	private Integer vWeektime;
	
    /**
     *
     */
	private Integer vMonthtime;
	
    /**
     *
     */
	private String vLen;
	
    /**
     *
     */
	private String vTotal;
	
    /**
     *
     */
	private String vVer;
	
    /**
     *
     */
	private String vPsd;
	
    /**
     *
     */
	private String vLongtxt;
	
	/**
    *播放列表
    */
	private String body;
	/**
	 *播放描述
	 */
	private String desc;


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
	
	public void setVName(String vName){
		this.vName = vName;
	}
	
	public String getVName(){
		return vName;
	}
	
	public void setVState(Integer vState){
		this.vState = vState;
	}
	
	public Integer getVState(){
		return vState;
	}
	
	public void setVPic(String vPic){
		this.vPic = vPic;
	}
	
	public String getVPic(){
		return vPic;
	}
	
	public void setVSpic(String vSpic){
		this.vSpic = vSpic;
	}
	
	public String getVSpic(){
		return vSpic;
	}
	
	public void setVGpic(String vGpic){
		this.vGpic = vGpic;
	}
	
	public String getVGpic(){
		return vGpic;
	}
	
	public void setVHit(Integer vHit){
		this.vHit = vHit;
	}
	
	public Integer getVHit(){
		return vHit;
	}
	
	public void setVMoney(Short vMoney){
		this.vMoney = vMoney;
	}
	
	public Short getVMoney(){
		return vMoney;
	}
	
	public void setVRank(Short vRank){
		this.vRank = vRank;
	}
	
	public Short getVRank(){
		return vRank;
	}
	
	public void setVDigg(Short vDigg){
		this.vDigg = vDigg;
	}
	
	public Short getVDigg(){
		return vDigg;
	}
	
	public void setVTread(Short vTread){
		this.vTread = vTread;
	}
	
	public Short getVTread(){
		return vTread;
	}
	
	public void setVCommend(Short vCommend){
		this.vCommend = vCommend;
	}
	
	public Short getVCommend(){
		return vCommend;
	}
	
	public void setVWrong(Short vWrong){
		this.vWrong = vWrong;
	}
	
	public Short getVWrong(){
		return vWrong;
	}
	
	public void setVIsmake(Short vIsmake){
		this.vIsmake = vIsmake;
	}
	
	public Short getVIsmake(){
		return vIsmake;
	}
	
	public void setVActor(String vActor){
		this.vActor = vActor;
	}
	
	public String getVActor(){
		return vActor;
	}
	
	public void setVColor(String vColor){
		this.vColor = vColor;
	}
	
	public String getVColor(){
		return vColor;
	}
	
	public void setVPublishyear(Integer vPublishyear){
		this.vPublishyear = vPublishyear;
	}
	
	public Integer getVPublishyear(){
		return vPublishyear;
	}
	
	public void setVPublisharea(String vPublisharea){
		this.vPublisharea = vPublisharea;
	}
	
	public String getVPublisharea(){
		return vPublisharea;
	}
	
	public void setVAddtime(Integer vAddtime){
		this.vAddtime = vAddtime;
	}
	
	public Integer getVAddtime(){
		return vAddtime;
	}
	
	public void setVTopic(Integer vTopic){
		this.vTopic = vTopic;
	}
	
	public Integer getVTopic(){
		return vTopic;
	}
	
	public void setVNote(String vNote){
		this.vNote = vNote;
	}
	
	public String getVNote(){
		return vNote;
	}
	
	public void setVTags(String vTags){
		this.vTags = vTags;
	}
	
	public String getVTags(){
		return vTags;
	}
	
	public void setVLetter(String vLetter){
		this.vLetter = vLetter;
	}
	
	public String getVLetter(){
		return vLetter;
	}
	
	public void setVIsunion(Short vIsunion){
		this.vIsunion = vIsunion;
	}
	
	public Short getVIsunion(){
		return vIsunion;
	}
	
	public void setVRecycled(Short vRecycled){
		this.vRecycled = vRecycled;
	}
	
	public Short getVRecycled(){
		return vRecycled;
	}
	
	public void setVDirector(String vDirector){
		this.vDirector = vDirector;
	}
	
	public String getVDirector(){
		return vDirector;
	}
	
	public void setVEnname(String vEnname){
		this.vEnname = vEnname;
	}
	
	public String getVEnname(){
		return vEnname;
	}
	
	public void setVLang(String vLang){
		this.vLang = vLang;
	}
	
	public String getVLang(){
		return vLang;
	}
	
	public void setVScore(Integer vScore){
		this.vScore = vScore;
	}
	
	public Integer getVScore(){
		return vScore;
	}
	
	public void setVScorenum(Integer vScorenum){
		this.vScorenum = vScorenum;
	}
	
	public Integer getVScorenum(){
		return vScorenum;
	}
	
	public void setVExtratype(String vExtratype){
		this.vExtratype = vExtratype;
	}
	
	public String getVExtratype(){
		return vExtratype;
	}
	
	public void setVJq(String vJq){
		this.vJq = vJq;
	}
	
	public String getVJq(){
		return vJq;
	}
	
	public void setVNickname(String vNickname){
		this.vNickname = vNickname;
	}
	
	public String getVNickname(){
		return vNickname;
	}
	
	public void setVReweek(String vReweek){
		this.vReweek = vReweek;
	}
	
	public String getVReweek(){
		return vReweek;
	}
	
	public void setVDouban(Float vDouban){
		this.vDouban = vDouban;
	}
	
	public Float getVDouban(){
		return vDouban;
	}
	
	public void setVMtime(Float vMtime){
		this.vMtime = vMtime;
	}
	
	public Float getVMtime(){
		return vMtime;
	}
	
	public void setVImdb(Float vImdb){
		this.vImdb = vImdb;
	}
	
	public Float getVImdb(){
		return vImdb;
	}
	
	public void setVTvs(String vTvs){
		this.vTvs = vTvs;
	}
	
	public String getVTvs(){
		return vTvs;
	}
	
	public void setVCompany(String vCompany){
		this.vCompany = vCompany;
	}
	
	public String getVCompany(){
		return vCompany;
	}
	
	public void setVDayhit(Integer vDayhit){
		this.vDayhit = vDayhit;
	}
	
	public Integer getVDayhit(){
		return vDayhit;
	}
	
	public void setVWeekhit(Integer vWeekhit){
		this.vWeekhit = vWeekhit;
	}
	
	public Integer getVWeekhit(){
		return vWeekhit;
	}
	
	public void setVMonthhit(Integer vMonthhit){
		this.vMonthhit = vMonthhit;
	}
	
	public Integer getVMonthhit(){
		return vMonthhit;
	}
	
	public void setVDaytime(Integer vDaytime){
		this.vDaytime = vDaytime;
	}
	
	public Integer getVDaytime(){
		return vDaytime;
	}
	
	public void setVWeektime(Integer vWeektime){
		this.vWeektime = vWeektime;
	}
	
	public Integer getVWeektime(){
		return vWeektime;
	}
	
	public void setVMonthtime(Integer vMonthtime){
		this.vMonthtime = vMonthtime;
	}
	
	public Integer getVMonthtime(){
		return vMonthtime;
	}
	
	public void setVLen(String vLen){
		this.vLen = vLen;
	}
	
	public String getVLen(){
		return vLen;
	}
	
	public void setVTotal(String vTotal){
		this.vTotal = vTotal;
	}
	
	public String getVTotal(){
		return vTotal;
	}
	
	public void setVVer(String vVer){
		this.vVer = vVer;
	}
	
	public String getVVer(){
		return vVer;
	}
	
	public void setVPsd(String vPsd){
		this.vPsd = vPsd;
	}
	
	public String getVPsd(){
		return vPsd;
	}
	
	public void setVLongtxt(String vLongtxt){
		this.vLongtxt = vLongtxt;
	}
	
	public String getVLongtxt(){
		return vLongtxt;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

