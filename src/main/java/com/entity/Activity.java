package com.entity;

import java.io.Serializable;

import java.sql.Blob;
import java.sql.Date;

import javax.servlet.http.Part;



public class Activity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Part img;
	private String name,dec,loc,path;

	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Part getImg() {
		return img;
	}
	public void setImg(Part img2) {
		this.img = img2;
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
	public void setImgPath(String a) {
		 this.path = a;
		 
	}
}
