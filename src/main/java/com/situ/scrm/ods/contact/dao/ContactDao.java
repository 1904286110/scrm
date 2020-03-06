package com.situ.scrm.ods.contact.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.ods.charts.domain.ChartsData;
import com.situ.scrm.ods.contact.domain.Contact;
@Repository
public interface ContactDao extends BaseDao<Contact> {
	void changeByCode(String contCode);
	void updateBycheckDate(Date checkDate);
	Integer getToDoCount(String userCode,Date checkDate);
	/**/
	List<ChartsData> findBarData4ContType();
	List<ChartsData> findBarData4ContStatus();
}
