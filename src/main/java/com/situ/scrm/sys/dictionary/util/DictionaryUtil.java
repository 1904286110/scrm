package com.situ.scrm.sys.dictionary.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.dictionary.dao.DictionaryDao;
import com.situ.scrm.sys.dictionary.domain.Dictionary;
@Service
public class DictionaryUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DictionaryDao dictionaryDao;
	public Map<String,String> getStringDataMap(String contType) {
		Map<String, String> dataMap = new HashMap<String,String>();
		Dictionary dictionary=dictionaryDao.findByValue(contType);
		if(dictionary!=null) {
			
			List<Dictionary> dictionaryList=dictionaryDao.findByParent(dictionary.getDictKey());
		    for(Dictionary data:dictionaryList) {
		    	dataMap.put(data.getDictValue(), data.getDictInfo());
		    }
		
		}
		return dataMap;
	}
}
