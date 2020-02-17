package com.situ.scrm.sys.area.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;

@Alias("Area")
public class Area extends BaseClass implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final long DEFAULT_PARENT_CODE = -1;
	private Long parentCode;
	private Integer areaCode;
	private String areaName;
	private Integer areaRunk;
	private Integer hasChild;

	public Long getParentCode() {
		return parentCode;
	}

	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getAreaRunk() {
		return areaRunk;
	}

	public void setAreaRunk(Integer areaRunk) {
		this.areaRunk = areaRunk;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
}
