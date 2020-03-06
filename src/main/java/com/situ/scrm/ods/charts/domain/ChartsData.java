package com.situ.scrm.ods.charts.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
@Alias("ChartsData")
public class ChartsData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String keyVal;//
	private String keyCode ;
	private Integer dataCount;
	public String getKeyVal() {
		return keyVal;
	}
	public void setKeyVal(String keyVal) {
		this.keyVal = keyVal;
	}
	public String getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	public Integer getDataCount() {
		return dataCount;
	}
	public void setDataCount(Integer dataCount) {
		this.dataCount = dataCount;
	}
	
}
