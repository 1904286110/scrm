package com.situ.scrm.sys.customer.service;

import java.util.List;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.area.domain.Area;
import com.situ.scrm.sys.customer.domain.Customer;

public interface CustomerService {
	/**
	 * @Title: checkCusName
	 * @Description:(检测名称唯一)
	 * @param cusName
	 * @return
	 */
	Integer checkCusName(String cusName);
	
	/**
	 * @Title: saveCustomer
	 * @Description:(新增功能)
	 * @param customer
	 * @return
	 */
	Long saveCustomer(Customer customer);
	
	/**
	 * @Title: doDeleteCustomer
	 * @Description:(删除功能)
	 * @param rowId
	 * @return
	 */
	Integer doDeleteCustomer(Long rowId);
	
	/**
	 * @Title: getCustomerById
	 * @Description:(根据id查询实例)
	 * @param rowId
	 * @return
	 */
	Customer getCustomerById(Long rowId);
	
	
	/**
	 * @Title: doEditCustomer
	 * @Description:(执行修改)
	 * @param customer
	 * @return
	 */
	Integer doEditCustomer(Customer customer);
	
	/**
	 * @Title: getCount
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	Integer getCount(Customer searchCustomer);
	
	
	/**
	 * @Title: findCustomerByPage
	 * @Description:(根据分页查询数据)
	 * @param page
	 * @param limit
	 * @param searchCustomer
	 * @return
	 */
	LayResult findCustomerByPage(Integer page, Integer limit, Customer searchCustomer);

	/**
	 * @Title: findAllCustomer
	 * @Description:(查询所有的信息)
	 * @return
	 */
	List<Customer> findAllCustomer();

	List<Area> findByCode(Integer areaCode);
	
	LayResult findPublicByPage(Integer page, Integer limit, Customer searchCustomer);

}
