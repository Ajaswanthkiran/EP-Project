package com.controller;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entity.Login;
import com.model.DataBase;



@ManagedBean( name = "login", eager = true)
@SessionScoped
public class LoginBean {
    private String username;
    private String password;
    
    
    
    @EJB(lookup = "java:global/AjkProject/LoginModel!com.model.DataBase")
    DataBase db;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        Login l=new Login();
        
        l.setUsername(getUsername());
        l.setPassword(getPassword());
        
        String a=db.login(l);
        System.out.println(a);
        if(a==null)
        {
        	 setUsername(null);
        	 setPassword(null);
        	return null;
        }
        return  a;

    }
    public String logout()
    {
    	setUsername(null);
    	setPassword(null);
    	
    	return "home.xhtml";
    }
}
