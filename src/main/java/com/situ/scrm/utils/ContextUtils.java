package com.situ.scrm.utils;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.situ.scrm.sys.user.domain.User;

public class ContextUtils implements Serializable {

	private static final long serialVersionUID = 1L;
     
	public static String getUserCode( HttpSession session) {
		
		User loginUser = (User) session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		
		return loginUser.getUserCode();
		
	}
	
	public static Integer getUserLevel( HttpSession session) {
		
		User loginUser = (User) session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		
		return loginUser.getUserLevel();
		
	}
} 
