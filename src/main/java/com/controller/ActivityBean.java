package com.controller;

import java.sql.Date;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import com.entity.Activity;
import com.model.DataBase;

@ManagedBean(name = "act", eager = true)
public class ActivityBean {

	
	private Part img;
	private String name,dec,loc,path;

	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	String res;
	
	 @EJB(lookup = "java:global/AjkProject/LoginModel!com.model.DataBase")
	    DataBase db;
	public Part getImg() {
		return img;
	}
	public void setImg(Part img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDec() {
		return dec;
	}
	public void setDec(String dec) {
		this.dec = dec;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
	public void storeData()
	{
		Activity a=new Activity();
        
        a.setName(name);
        a.setDec(dec);
        
        a.setImg(img);
        a.setLoc(loc);
        res= db.activity(a);
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	
	public List<Activity> getData()
	{
		return db.getSportsActivities();
	}
	
}
