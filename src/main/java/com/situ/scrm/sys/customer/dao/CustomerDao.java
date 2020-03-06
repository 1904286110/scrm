package com.situ.scrm.sys.customer.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.situ.scrm.commons.dao.BaseDao;
import com.situ.scrm.sys.customer.domain.Customer;
@Repository
public interface CustomerDao extends BaseDao<Customer> {
	/**
	 * @Title: getByName 
	 * @Description:(根据名称查询实例)
	 * @param cusName
	 * @return
	 */
	Customer getByName(String cusName);
	
	void updateByLastCurrent(Date checkDate);
}
