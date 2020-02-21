package com.situ.scrm.sys.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.user.dao.UserDao;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.sys.user.service.UserService;
import com.situ.scrm.utils.AppConfig;
import com.situ.scrm.utils.DAOUtils;
import com.situ.scrm.utils.MD5Utils;

@Service
public class UserServiceImpl implements UserService {
	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	/**
	 * @Title: checkUserCode
	 * @Description:(检测名称唯一)
	 * @param userCode
	 * @return
	 */
	@Override
	public Integer checkUserCode(String userCode) {
		User checkUser = userDao.getUserByCode(userCode);
		return checkUser != null ? 1 : 0;
	}

	/**
	 * @Title: saveUser
	 * @Description:(新增功能)
	 * @param User
	 * @return
	 */
	@Override
	public Long saveUser(User user) {
		user.setUserPass(MD5Utils.encode(user.getUserPass()));
		user.setIsLock(User.IS_LOCK_NO);
		user.setLoginCount(1);
		user.setCreateBy("SYS");
		user.setCreateDate(new Date());
		user.setActiveFlag(1);
		userDao.save(user);
		return user.getRowId();
	}

	@Override
	public Integer doDeleteUser(Long rowId) {
		userDao.delete(rowId);
		return 1;
	}

	@Override
	public User getUser(Long rowId) {
		
		return userDao.get(rowId);
	}

	@Override
	public Integer doEditUser(User user) {
		Long rowId=user.getRowId();
		
		User editUser =userDao.get(rowId);
		editUser.setUpdateBy("sys");
		editUser.setUpdateDate(new Date());
		userDao.update(DAOUtils.buildEditData(editUser, user));
		return 1;
	}

	@Override
	public Integer getCount(User searchUser) {
		
		return userDao.getCount(searchUser);
	}

	@Override
	public LayResult findUserByPage(Integer page, Integer limit, User searchUser) {
		User searchParam=DAOUtils.buildSearchParam(searchUser);
		Integer count=userDao.getCount(searchParam);
		List<User> userList=userDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "",count,userList);
	}

	@Override
	public List<User> findAllUser() {
		
		return userDao.find();
	}

	@Override
	public Integer update4Lock(Long rowId, Integer isLock) {
		userDao.update4Lock(rowId, isLock);
		return 1;
	}

	@Override
	public LayResult doUserLogin(User loginUserParam, HttpSession session, HttpServletResponse response) {
		Integer code = 0;
		String msg ="";
		String userCode = loginUserParam. getUserCode();
		String userPass = loginUserParam. getUserPass();
		User loginUser = userDao.findByCodeAndPass(userCode,MD5Utils.encode(userPass));
		if(loginUser!=null) {
		  Integer isLock = loginUser.getIsLock();
		  if(isLock==0) {
			  code=1;
		  }else {
			  msg ="用户被锁定,请联系管理员!";
		  }
		}else {
			msg = "用户名或密码错误!";
		}
		
		
		
		return new LayResult(code, msg, null);
	}
	
	private void hanlderUserLoginEdData(User loginUser, HttpSession session) {
		
	}
	

}
