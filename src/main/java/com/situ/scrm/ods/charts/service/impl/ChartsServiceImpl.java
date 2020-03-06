package com.situ.scrm.ods.charts.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.ods.charts.domain.ChartsData;
import com.situ.scrm.ods.charts.service.ChartsService;
import com.situ.scrm.ods.contact.dao.ContactDao;
import com.situ.scrm.sys.dictionary.util.DictionaryUtil;
@Service
public class ChartsServiceImpl implements Serializable, ChartsService {

	private static final long serialVersionUID = 1L;
	 @Autowired
	private ContactDao contactDao;
	 @Autowired
		private DictionaryUtil dictionaryUtil;
	@Override
	public List<ChartsData> findBarData4ContType() {
		List<ChartsData> barDataList = contactDao.findBarData4ContType( );
		if (barDataList != null) {
			Map<String,String> dataMap = dictionaryUtil.getStringDataMap("contType");
			for (ChartsData barData : barDataList) {
				barData.setKeyVal(dataMap.get (barData.getKeyCode()));
			}
		}
		System.out.println(barDataList.toString());
		return barDataList;
	}
	@Override
	public List<ChartsData> findBarData4ContStatus() {
		List<ChartsData> barDataList = contactDao.findBarData4ContStatus();
		if (barDataList != null) {
			Map<String,String> dataMap = dictionaryUtil.getStringDataMap("contStatus");
			for (ChartsData barData : barDataList) {
				barData.setKeyVal(dataMap.get (barData.getKeyCode()));
			}
		}
		System.out.println(barDataList.toString());
		return barDataList;
	}

}
