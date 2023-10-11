package com.controller;

import javax.ejb.EJB;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entity.Registration;
import com.model.DataBase;

@ManagedBean(name = "reg" , eager = true)
@SessionScoped
public class RegistrationBean {
    private String newUsername;
    private String newPassword,newEmail;

    @EJB(lookup = "java:global/AjkProject/LoginModel!com.model.DataBase")
    DataBase db;
    public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String register() {
Registration l=new 	Registration();
        
        l.setUsername(getNewUsername());
        l.setPassword(getNewPassword());
        l.setEmail(getNewEmail());
        
        return db.reg(l);
    }
}
