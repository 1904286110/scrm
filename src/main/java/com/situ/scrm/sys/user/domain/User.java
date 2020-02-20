package com.situ.scrm.sys.user.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;


@Alias("User")
public class User extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int IS_LOCK_YES = 1;
	public static final int IS_LOCK_NO = 0;
	private String userName;//用户名称
	private String userCode;//登录账号
	private String userPass;//登录密码
	private String parentCode;//上级用户账号
	
	private String roleCode;//角色编号
	
	private Integer userKind;//用户类型#1:超级用户;0:普通用户;
	private Integer isLock;//是否锁定#1:是;0:否;
	private Integer loginCount;//登陆次数
	private String loginIp;//最后登录IP
	private Date loginDate;//最后登录时间
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getUserKind() {
		return userKind;
	}
	public void setUserKind(Integer userKind) {
		this.userKind = userKind;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	
}
