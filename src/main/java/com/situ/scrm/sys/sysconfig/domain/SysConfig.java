package com.situ.scrm.sys.sysconfig.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("SysConfig")
public class SysConfig extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cusDay;// 公海天数
	private Integer orderDay;// 跟单天数
	private String comName;//公司名称
	public Integer getCusDay() {
		return cusDay;
	}
	public void setCusDay(Integer cusDay) {
		this.cusDay = cusDay;
	}
	public Integer getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(Integer orderDay) {
		this.orderDay = orderDay;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}

	
	
}
