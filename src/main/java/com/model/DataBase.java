package com.model;

import java.util.List;

import com.entity.Activity;
import com.entity.Login;
import com.entity.Notice;
import com.entity.Registration;

public interface DataBase {
	
	public String login(Login l);
	public String reg(Registration r);
	public String activity(Activity a);
	public String notice(Notice n);
	List<Notice> getNotices();
	List<Activity> getSportsActivities();

}
