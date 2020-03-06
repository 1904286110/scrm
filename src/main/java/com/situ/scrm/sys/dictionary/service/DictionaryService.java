package com.situ.scrm.sys.dictionary.service;

import java.util.List;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.domain.Dictionary;

public interface DictionaryService {
	/**
	 * @Title: getDictionaryById
	 * @Description:(根据主键查询Dictionary对象)
	 * @param rowId
	 * @return
	 */
	Dictionary getDictionaryById(Long rowId);
	/**
	 * @Title: getAllDictionaryById
	 * @Description:(根据主键查询Dictionary对象并给parentName赋值)
	 * @param rowId
	 * @return
	 */
	Dictionary getAllDictionaryById(Long rowId);
	
	/**
	 * @Title: findAllDictionary
	 * @Description:(查询所有的字典信息)
	 * @return
	 */
	LayResult findAllDictionary();
	
	/**
	 * @Title: saveDictionary
	 * @Description:(新增字典信息)
	 * @param dictionary
	 * @return
	 */
	Long saveDictionary(Dictionary dictionary);
	
	/**
	 * @Title: updateDictionary
	 * @Description:(修改字典信息)
	 * @param dictionary
	 * @return
	 */
	Long updateDictionary(Dictionary dictionary);
	
	/**
	 * @Title: checkDictInfo
	 * @Description:(检测名称唯一)
	 * @param dictInfo
	 * @param parentKey
	 * @return
	 */
	Integer checkDictInfo(String dictInfo, String parentKey);
	
	/**
	 * @Title: doDeleteDictionary
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteDictionary(Long rowId);
	/**
	 * 客户类调用的查询
	 * @return
	 */
	List<Dictionary> useDictionaryList();
}
