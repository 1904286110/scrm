package com.situ.scrm.ods.contact.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("Contact")
public class Contact extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int CONT_STATUS_CLOSE = 4;//4状态为结束跟单
	
	private String contCode;//跟单编号
	private String userCode;// 员工CODE
	private String custName;//客户名称 ;
	private String custCode;//客户CODE
	private String contType;//跟单方式
	private String contStatus;//跟单状态
	private Integer isClose;//是否结束跟单#1:是;θ:否;
	private Date nextContDate;//下次联系时间
	private Integer isRemind;//是否提醒#1:是;θ:否;
	private String contFile;//附件
	private String contInfo;//详细内容
	/*额外信息-开始*/
	private String parentCode; //员工的上级CODE
	
	private String userName ;
	private String contTypeVal;//跟单方式 信息
	private String contStatusVal;//跟单状态信息
	
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContTypeVal() {
		return contTypeVal;
	}
	public void setContTypeVal(String contTypeVal) {
		this.contTypeVal = contTypeVal;
	}
	public String getContStatusVal() {
		return contStatusVal;
	}
	public void setContStatusVal(String contStatusVal) {
		this.contStatusVal = contStatusVal;
	}
	/*额外信息-结束*/
	public String getContCode() {
		return contCode;
	}
	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getContType() {
		return contType;
	}
	public void setContType(String contType) {
		this.contType = contType;
	}
	public String getContStatus() {
		return contStatus;
	}
	public void setContStatus(String contStatus) {
		this.contStatus = contStatus;
	}
	public Integer getIsClose() {
		return isClose;
	}
	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}
	public Date getNextContDate() {
		return nextContDate;
	}
	public void setNextContDate(Date nextContDate) {
		this.nextContDate = nextContDate;
	}
	public Integer getIsRemind() {
		return isRemind;
	}
	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}
	public String getContFile() {
		return contFile;
	}
	public void setContFile(String contFile) {
		this.contFile = contFile;
	}
	public String getContInfo() {
		return contInfo;
	}
	public void setContInfo(String contInfo) {
		this.contInfo = contInfo;
	}
	@Override
	public String toString() {
		return "Contact [contCode=" + contCode + ", userCode=" + userCode + ", custName=" + custName + ", contStatus="
				+ contStatus + ", isClose=" + isClose + ", nextContDate=" + nextContDate + ", isRemind=" + isRemind
				+ ", parentCode=" + parentCode + ", userName=" + userName + "]";
	}

}
