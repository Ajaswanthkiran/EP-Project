package com.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.Activity;
import com.entity.Notice;
import com.model.DataBase;

@ManagedBean(name="notice" , eager = true)

public class NoticeBean {
	
	private String notice,des;
	String res;
	

	 @EJB(lookup = "java:global/AjkProject/LoginModel!com.model.DataBase")
	 DataBase db;
	 
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
	public void storeData()
	{
		Notice a=new Notice();
        
        a.setNotice(notice);
        a.setDes(des);
        res= db.notice(a);
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	
	public List<Notice> data()
	{
		return db.getNotices();
	}
	
}
