package com.situ.scrm.sys.dictionary.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.dictionary.dao.DictionaryDao;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.sys.sysresource.domain.SysResource;
import com.situ.scrm.utils.DAOUtils;

@Service
public class DictionaryServiceImpl implements Serializable, DictionaryService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private SysCountUtils sysCountUtils;

	/**
	 * @Title: getDictionaryById
	 * @Description:(根据主键查询Dictionary对象)
	 * @param rowId
	 * @return
	 */
	@Override
	public Dictionary getDictionaryById(Long rowId) {

		return dictionaryDao.get(rowId);
	}

	/**
	 * @Title: getAllDictionaryById
	 * @Description:(根据主键查询Dictionary对象并给parentName赋值)
	 * @param rowId
	 * @return
	 */
	@Override
	public Dictionary getAllDictionaryById(Long rowId) {

		Dictionary dictionary = dictionaryDao.get(rowId);

		String parentKey = dictionary.getParentKey();
		if (!parentKey.equals(SysResource.DEFAULT_PARENT_CODE)) {
			Dictionary parentDictionary = dictionaryDao.getByKey(parentKey);
			dictionary.setParentName(parentDictionary.getDictInfo());
		}
		return dictionary;
	}

	/**
	 * @Title: findAllDictionary
	 * @Description:(查询所有的字典信息)
	 * @return
	 */
	@Override
	public LayResult findAllDictionary() {
		List<Dictionary> allDictionaryList = new ArrayList<Dictionary>();
		Map<String, List<Dictionary>> dictionaryMap = buildDictionaryMap();
		if (dictionaryMap != null) {
			List<Dictionary> dictionaryList = dictionaryMap.get(SysResource.DEFAULT_PARENT_CODE);
			if (dictionaryList != null) {
				for (Dictionary dictionary : dictionaryList) {
					// 判断是否有子数据
					Integer hasChild = dictionary.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(dictionary, dictionaryMap);
					}
					allDictionaryList.add(dictionary);
				}
			}

		}
		return new LayResult(200, "ok", allDictionaryList);
	}

	/**
	 * @Title: saveDictionary
	 * @Description:(新增字典信息)
	 * @param dictionary
	 * @return
	 */
	@Override
	public Long saveDictionary(Dictionary dictionary) {
		// 得到资源编号
		String dictKey = sysCountUtils.buildDictKey();
		dictionary.setDictKey(dictKey);
		String parentKey = dictionary.getParentKey();
		Integer maxOrder = dictionaryDao.getMaxOrder(parentKey);
		maxOrder = maxOrder == null ? 0 : maxOrder;
		dictionary.setDictOrder(maxOrder + 1);
		// 如果不是默认的parentKey，则需要对parent类更新有子类数据信息
		if (!SysResource.DEFAULT_PARENT_CODE.equals(parentKey)) {
			dictionaryDao.updateHasChild(parentKey, 1);
		}
		dictionary.setCreateBy("sys");
		dictionary.setCreateDate(new Date());
		dictionary.setActiveFlag(1);
		dictionary.setHasChild(0);
		dictionaryDao.save(dictionary);
		return dictionary.getRowId();
	}

	/**
	 * @Title: updateDictionary
	 * @Description:(修改字典信息)
	 * @param dictionary
	 * @return
	 */
	@Override
	public Long updateDictionary(Dictionary dictionary) {
		Long rowId = dictionary.getRowId();
		Dictionary editDictionary = dictionaryDao.get(rowId);
		editDictionary = DAOUtils.buildEditData(editDictionary, dictionary);
		editDictionary.setUpdateBy("sys");
		editDictionary.setUpdateDate(new Date());
		dictionaryDao.update(editDictionary);
		return rowId;
	}

	/**
	 * @Title: checkDictInfo
	 * @Description:(检测名称唯一)
	 * @param dictInfo
	 * @param parentKey
	 * @return
	 */
	@Override
	public Integer checkDictInfo(String dictInfo, String parentKey) {
		Dictionary dictionary = dictionaryDao.getByNameAndParent(dictInfo, parentKey);
		return dictionary == null ? 0 : 1;
	}

	/**
	 * @Title: doDeleteDictionary
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	@Override
	public Integer doDeleteDictionary(Long rowId) {
		Dictionary deleteDictionary = dictionaryDao.get(rowId);
		String parentKey = deleteDictionary.getParentKey();
		dictionaryDao.delete(rowId);
		handlerDeleteDictionary(deleteDictionary);
		// 如果不是默认的父类KEY hasChild就可以修改
		if (!parentKey.equals(SysResource.DEFAULT_PARENT_CODE)) {

			Integer hasChild = 0;

			List<Dictionary> childDictionaryList = dictionaryDao.findByParent(parentKey);

			if (childDictionaryList != null && !childDictionaryList.isEmpty()) {
				hasChild = 1;
			}
			// 修改父类的资源是否有子元素
			dictionaryDao.updateHasChild(parentKey, hasChild);
		}
		return 1;
	}

	/**
	 * @Title: handlerDeleteResource
	 * @Description:(回调删除子类数据)
	 * @param parentResoruce
	 */
	private void handlerDeleteDictionary(Dictionary parentDictionary) {
		String dictKey = parentDictionary.getDictKey();

		List<Dictionary> childDictionaryList = dictionaryDao.findByParent(dictKey);
		if (childDictionaryList != null) {
			for (Dictionary dictionary : childDictionaryList) {

				dictionaryDao.delete(dictionary.getRowId());
				if (dictionary.getHasChild() == 1) {
					handlerDeleteDictionary(dictionary);
				}
			}
		}
	}

	/**
	 * @Title: callBackChildList
	 * @Description:(递归的方式拿到子类数据,设置Children属性)
	 * @param parentKey
	 * @param dictionaryMap
	 * @return
	 */
	private void callBackChildList(Dictionary dictionary, Map<String, List<Dictionary>> dictionaryMap) {
		String parentKey = dictionary.getDictKey();
		List<Dictionary> childList = dictionaryMap.get(parentKey);
		if (childList != null) {
			for (Dictionary child : childList) {
				if (child.getHasChild() == 1) {
					callBackChildList(child, dictionaryMap);
				}
			}
			// 将拿到的子数据设置进去。
			dictionary.setChildren(childList);
		}
	}

	/**
	 * @Title: buildDictionaryMap
	 * @Description:(将资源数据转换成MAP格式的数据)
	 * @param dictionaryList
	 * @return Map<parentKey,List<Dictionary>>
	 */
	private Map<String, List<Dictionary>> buildDictionaryMap() {
		// 将系统资源所有的数据都查询出来。
		List<Dictionary> dictionaryList = dictionaryDao.find();
		Map<String, List<Dictionary>> dictionaryMap = new HashMap<String, List<Dictionary>>();
		if (dictionaryList != null) {
			for (Dictionary dictionary : dictionaryList) {

				String parentKey = dictionary.getParentKey();

				List<Dictionary> list = dictionaryMap.get(parentKey);
				if (list == null) {
					list = new ArrayList<Dictionary>();
				}
				list.add(dictionary);

				dictionaryMap.put(parentKey, list);
			}
		}
		return dictionaryMap;
	}

	@Override
	public List<Dictionary> useDictionaryList() {
		List<Dictionary> allDictionaryList = new ArrayList<Dictionary>();
		Map<String, List<Dictionary>> dictionaryMap = buildDictionaryMap();
		if (dictionaryMap != null) {
			List<Dictionary> dictionaryList = dictionaryMap.get(SysResource.DEFAULT_PARENT_CODE);
			if (dictionaryList != null) {
				for (Dictionary dictionary : dictionaryList) {
					// 判断是否有子数据
					Integer hasChild = dictionary.getHasChild();
					// 如果有子数据
					if (hasChild == 1) {
						// 通过递归方法(重复调用本身)将所有资源的子数据处理成功。
						callBackChildList(dictionary, dictionaryMap);
					}
					allDictionaryList.add(dictionary);
				}
			}

		}
		
		return allDictionaryList;
	}
}
