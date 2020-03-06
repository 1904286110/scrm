package com.situ.scrm.sys.dictionary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
import com.situ.scrm.sys.sysresource.domain.SysResource;
@Repository
public interface DictionaryDao extends BaseDao<Dictionary> {
	/**
	 * @Title: getMaxOrder
	 * @Description:(根据parentKey得到最大的排序)
	 * @param parentCode
	 * @return
	 */
	Integer getMaxOrder(String parentKey);
	

	/**
	 * @Title: findByParent
	 * @Description:(根据parentKey查询子类数据)
	 * @param parentKey
	 * @return
	 */
	List<Dictionary> findByParent(String parentKey);
	/**
	 * @Title: updateHasChild
	 * @Description:(更新是否有子元素)
	 * @param hasChild
	 */
	void updateHasChild(@Param("dictKey") String dictKey, @Param("hasChild") Integer hasChild);
	
	/**
	 * @Title: getByNameAndParent
	 * @Description:(根据Name和parentKey查询实例)
	 * @param dictInfo
	 * @param parentKey
	 * @return
	 */
	Dictionary getByNameAndParent(@Param("dictInfo") String dictInfo, @Param("parentKey") String parentKey);
	
	
	/**
	 * @Title: getByKey
	 * @Description:(根据Key查询实例)
	 * @param dictKey
	 * @return
	 */
	Dictionary getByKey(String dictKey);
	
	Dictionary findByValue(String dictValue);

	
}
