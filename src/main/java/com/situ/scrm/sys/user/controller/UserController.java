/**
 * @Company:中享思途   
 * @Title:UserController.java 
 * @Author:wxinpeng   
 * @Date:2020年1月8日 上午9:54:17     
 */
package com.situ.scrm.sys.user.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.role.service.RoleService;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;

/** 
 * @ClassName:UserController 
 * @Description:(用户的Controller层)  
 */
@RestController
@RequestMapping("/user")
public class UserController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_USER_INDEX = "sys/user/user_index";
	private static final String PAGE_USER_ADD_EDIT = "sys/user/user_add_edit";
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * @Title: goIdnex 
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping
	public ModelAndView goIdnex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_USER_INDEX);
		return modelAndView;
	}

	/**
	 * @Title: getForm 
	 * @Description:(得到表单)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView getForm(ModelAndView modelAndView) {
		modelAndView.addObject("roleList", roleService.findAllRole());
		modelAndView.addObject("userList", userService.findAllUser());
		modelAndView.setViewName(PAGE_USER_ADD_EDIT);
		return modelAndView;
	}

	/**
	 * @Title: checkUserCode 
	 * @Description:(检测CODE唯一)
	 * @param userCode
	 * @return
	 */
	@GetMapping("/checkcode")
	public Integer checkUserCode(String userCode) {
		return userService.checkUserCode(userCode);
	}
	
	/**
	 * @Title: doAddUser
	 * @Description:(执行新增功能)
	 * @param user
	 * @return
	 */
	@PostMapping
	public Long doAddUser(User user) {
		return userService.saveUser(user);
	}
	
	/**
	 * @Title: findUserByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchUser 查询的条件
	 * @return
	 */

	@GetMapping("/{page}/{limit}")
	public LayResult findUserByPage(@PathVariable Integer page, @PathVariable Integer limit, User searchUser) {
		return userService.findUserByPage(page, limit, searchUser);
	}
	
	
	/**
	 * @Title: goEditUser
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public User goEditUser(@PathVariable Long rowId) {
		return userService.getUser(rowId);
	}
	
	/**
	 * @Title: doEditUser
	 * @Description:(执行修改)
	 * @param user
	 * @return
	 */
	@PutMapping
	public Integer doEditUser(User user) {
		return userService.doEditUser(user);
		
	}
	
	/**
	 * @Title: doDeleteRole
	 * @Description:(执行删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteUser(@PathVariable Long rowId) {
		return userService.doDeleteUser(rowId);
	}
}
