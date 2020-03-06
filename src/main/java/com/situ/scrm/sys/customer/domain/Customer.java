package com.situ.scrm.sys.customer.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("Customer")
public class Customer extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cusName;// 客户名称 #要求唯一
	private String cusPhone;// 手机号码
	private String cusContact;// 联系人
	private String cusQq;//QQ
	private String cusEmail;// 电子邮箱
	private Integer proCode;// 省CODE
	private Integer cityCode;// 市CODE
	private Integer areaCode;// 区CODE
	private String detAddress;// 详细地址
	private String cusType;// 客户类型
	private Integer cusKind;// 客户级别
	private String cusSource;// 客户来源
	private String cusIndustry;// 所属行业
	private Date lastCurrent;// 最后跟单时间
	private Integer isPublic;// 是否公海
	private String cusEnclosure;// 附件
	private String cusInfo;// 备注
	
	public String getCusContact() {
		return cusContact;
	}
	public void setCusContact(String cusContact) {
		this.cusContact = cusContact;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	
	public String getCusQq() {
		return cusQq;
	}
	public void setCusQq(String cusQq) {
		this.cusQq = cusQq;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public Integer getProCode() {
		return proCode;
	}
	public void setProCode(Integer proCode) {
		this.proCode = proCode;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}
	public String getDetAddress() {
		return detAddress;
	}
	public void setDetAddress(String detAddress) {
		this.detAddress = detAddress;
	}
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public Integer getCusKind() {
		return cusKind;
	}
	public void setCusKind(Integer cusKind) {
		this.cusKind = cusKind;
	}
	public String getCusSource() {
		return cusSource;
	}
	public void setCusSource(String cusSource) {
		this.cusSource = cusSource;
	}
	
	public String getCusIndustry() {
		return cusIndustry;
	}
	public void setCusIndustry(String cusIndustry) {
		this.cusIndustry = cusIndustry;
	}

	public Integer getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public String getCusEnclosure() {
		return cusEnclosure;
	}
	public void setCusEnclosure(String cusEnclosure) {
		this.cusEnclosure = cusEnclosure;
	}
	public String getCusInfo() {
		return cusInfo;
	}
	public void setCusInfo(String cusInfo) {
		this.cusInfo = cusInfo;
	}
	public Date getLastCurrent() {
		return lastCurrent;
	}
	public void setLastCurrent(Date lastCurrent) {
		this.lastCurrent = lastCurrent;
	}
	
	
}
