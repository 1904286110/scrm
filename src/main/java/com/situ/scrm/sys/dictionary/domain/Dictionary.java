package com.situ.scrm.sys.dictionary.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.situ.scrm.commons.domain.BaseClass;
@Alias("Dictionary")
public class Dictionary extends BaseClass implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String parentKey;// 父类字典KEY
	private String dictKey;// 字典KEY
	private String dictInfo;// 字典的描述
	private String dictValue;// 字典编码
	private Integer dictOrder;// 排序
	private Integer hasChild;// 是否有子数据#1:是;0:否;
	/* 额外的信息 -开始*/
	private List<Dictionary> children;// 子元素的数据
	private String parentName;// 父类资源的名称
	
	public List<Dictionary> getChildren() {
		return children;
	}
	public void setChildren(List<Dictionary> children) {
		this.children = children;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/*额外的信息 -结束*/
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public String getDictInfo() {
		return dictInfo;
	}
	public void setDictInfo(String dictInfo) {
		this.dictInfo = dictInfo;
	}
	
	
	public Integer getDictOrder() {
		return dictOrder;
	}
	public void setDictOrder(Integer dictOrder) {
		this.dictOrder = dictOrder;
	}
	public Integer getHasChild() {
		return hasChild;
	}
	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	
}
