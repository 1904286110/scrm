/**
 * @Company:中享思途   
 * @Title:IndexController.java 
 * @Author:wxinpeng   
 * @Date:2020年2月8日 下午3:23:18     
 */
package com.situ.scrm.index.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contact.service.ContactService;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;

/** 
 * @ClassName:IndexController 
 * @Description:(Index的Controller层)  
 */
@RestController
public class IndexController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX = "index";
	private static final String PAGE_LOGIN = "login";
    @Autowired
	private UserService userService;
    @Autowired
   	private ContactService contactService;
	/**
	 * @Title: goIndex 
	 * @Description:(进系统首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(path = { "/index", "/" })
	public ModelAndView goIndex(ModelAndView modelAndView,HttpSession session) {
		modelAndView.addObject("resourceList", userService.findAuthResourceList(session));
		
		modelAndView.addObject("todoCount", contactService.getToDoCount(session));
		modelAndView.setViewName(PAGE_INDEX);
		return modelAndView;
	}
	
	/**
	 * @Title: goLogin 
	 * @Description:(进登录页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping(path = { "/login"})
	public ModelAndView goLogin(ModelAndView modelAndView) {
		
		
		
		modelAndView.setViewName(PAGE_LOGIN);
		return modelAndView;
	}
	@GetMapping(path = { "/exit"})
	public ModelAndView goExit(ModelAndView modelAndView) {
		
		
		
		modelAndView.setViewName(PAGE_LOGIN);
		return modelAndView;
	}
	@GetMapping("/dologin")
	public LayResult doLogin(User loginUserParam,HttpSession session, HttpServletResponse response) {
		return userService.doUserLogin(loginUserParam, session, response);
		
	}
}
