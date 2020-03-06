package com.situ.scrm.sys.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.role.dao.RoleDao;
import com.situ.scrm.sys.role.dao.RoleResourceDao;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.sys.sysresource.dao.SysActionInfoDao;
import com.situ.scrm.sys.sysresource.dao.SysResourceDao;
import com.situ.scrm.sys.sysresource.domain.SysActionInfo;
import com.situ.scrm.sys.sysresource.domain.SysResource;
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
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleResourceDao roleResourceDao;
	@Autowired
	private SysActionInfoDao sysActionInfoDao;

	@Autowired
	private SysResourceDao sysResourceDao;

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
		if (user.getRoleCode().equals("R001")) {
			user.setUserKind(1);
		} else {
			user.setUserKind(0);
		}
		 String parentCode=user.getParentCode();
		 user.setUserLevel(userDao.getUserByCode(parentCode).getUserLevel()+1);
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
		Long rowId = user.getRowId();
        String parentCode=user.getParentCode();
        
		User editUser = userDao.get(rowId);
		user.setUserLevel(userDao.getUserByCode(parentCode).getUserLevel()+1);
		user.setUpdateBy("sys");
		user.setUpdateDate(new Date());
		
		userDao.update(DAOUtils.buildEditData(editUser, user));
		return 1;
	}

	@Override	public Integer getCount(User searchUser) {

		return userDao.getCount(searchUser);
	}

	@Override
	public LayResult findUserByPage(Integer page, Integer limit, User searchUser) {
		User searchParam = DAOUtils.buildSearchParam(searchUser);
		Integer count = userDao.getCount(searchParam);
		List<User> userList = userDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		for (User user : userList) {
         Role role = roleDao.getByCode(user.getRoleCode());
			user.setRoleName(role.getRoleName());
			
		}

		return new LayResult(0, "", count, userList);
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
		String msg = "";
		String userCode = loginUserParam.getUserCode();
		String userPass = loginUserParam.getUserPass();
		User loginUser = userDao.findByCodeAndPass(userCode, MD5Utils.encode(userPass));
		if (loginUser != null) {
			Integer isLock = loginUser.getIsLock();
			if (isLock == 0) {
				code = 1;
				hanlderUserLoginEdData(loginUser, session);
			} else {
				msg = "用户被锁定,请联系管理员!";
			}
		} else {
			msg = "用户名或密码错误!";
		}

		return new LayResult(code, msg, null);
	}

	private void hanlderUserLoginEdData(User loginUser, HttpSession session) {
		session.setAttribute(AppConfig.SESSION_LOGIN_USER, loginUser);

		List<SysActionInfo> actionInfoList = null;
		Integer userKind = loginUser.getUserKind();
		if (userKind == 1) {
			actionInfoList = sysActionInfoDao.find();
		} else {
			String roleCode = loginUser.getRoleCode();
			if (roleCode != null) {
				Long roleId = roleDao.getByCode(roleCode).getRowId();

				List<String> rescCodeList = roleResourceDao.findCode(roleId);
				if (rescCodeList != null) {
					actionInfoList = new ArrayList<SysActionInfo>();
					List<SysActionInfo> allActionInfoList = sysActionInfoDao.find();
					if (allActionInfoList != null) {
						for (SysActionInfo sysActionInfo : allActionInfoList) {
							if (rescCodeList.contains(sysActionInfo.getRescCode())) {
								actionInfoList.add(sysActionInfo);
							}
						}
					}

				}

			}
		}
		if (actionInfoList != null) {
			Map<String, Set<String>> actionInfoMap = new HashMap<String, Set<String>>();
			for (SysActionInfo actionInfo : actionInfoList) {
				String method = actionInfo.getMethod();

				String actionUrl = actionInfo.getActionUrl();

				Set<String> actionUrlSet = actionInfoMap.get(method);
				if (actionUrlSet == null) {
					actionUrlSet = new HashSet<String>();
				}
				actionUrlSet.add(actionUrl.replaceAll("\\{\\w*\\}", "\\\\w+"));
				actionInfoMap.put(method, actionUrlSet);
			}
			System.out.println(actionInfoMap.toString());
			session.setAttribute(AppConfig.SESSION_RESOURCE_MAP_ROLE, actionInfoMap);

		}
	}

	@Override
	public List<SysResource> findAuthResourceList(HttpSession session) {
		User loginUser = (User) session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		String roleCode = loginUser.getRoleCode();
		Integer userKind = loginUser.getUserKind();// 用户类型#1 :超级用户;0:普通用户;
		List<SysResource> resourceList = null;
		if (userKind == 1) {
			resourceList = sysResourceDao.find();
		} else {
			if (roleCode != null) {
				Long roleId = roleDao.getByCode(roleCode).getRowId();

				List<String> rescCodeList = roleResourceDao.findCode(roleId);
				resourceList = new ArrayList<SysResource>();

				List<SysResource> allResourceList = sysResourceDao.find();
				for (SysResource sysResource : allResourceList) {
					if (rescCodeList != null && rescCodeList.contains(sysResource.getRescCode())) {
						resourceList.add(sysResource);
					}
				}
			}
		}
		return bulidAuthResourceList(resourceList);
	}

	public List<SysResource> bulidAuthResourceList(List<SysResource> resourceList) {
		Map<String, List<SysResource>> resourceMap = new HashMap<String, List<SysResource>>();
		for (SysResource sysResource : resourceList) {
			String parentCode = sysResource.getParentCode();
			List<SysResource> list = resourceMap.get(parentCode);
			if (list == null) {
				list = new ArrayList<SysResource>();
			}
			list.add(sysResource);
			resourceMap.put(parentCode, list);
		}

		for (SysResource sysResource : resourceList) {
			// 如果是一级资源
			if (sysResource.getParentCode().equals(SysResource.DEFAULT_PARENT_CODE)) {
				// 查询子资源
				List<SysResource> children = resourceMap.get(sysResource.getRescCode());
				if (children != null) {
					// 设置子资源
					sysResource.setChildren(children);
				}

			}

		}

		return resourceList;

	}

}
