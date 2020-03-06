package com.situ.scrm.sys.customer.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.area.dao.AreaDao;
import com.situ.scrm.sys.area.domain.Area;
import com.situ.scrm.sys.customer.dao.CustomerDao;
import com.situ.scrm.sys.customer.domain.Customer;
import com.situ.scrm.sys.customer.service.CustomerService;
import com.situ.scrm.utils.DAOUtils;
@Service
public class CustomerServiceImpl implements Serializable, CustomerService {

	private static final long serialVersionUID = 1L;
    @Autowired
	private CustomerDao customerDao;
    @Autowired
   	private AreaDao areaDao;
    
	@Override
	public Integer checkCusName(String cusName) {
		Customer customer=customerDao.getByName(cusName);
		return customer != null ? 1 : 0;
	}

	@Override
	public Long saveCustomer(Customer customer) {
		customer.setIsPublic(1);
		customer.setActiveFlag(1);
		customer.setCreateBy("sys");
		customer.setCreateDate(new Date());
		customerDao.save(customer);
		return customer.getRowId();
	}

	@Override
	public Integer doDeleteCustomer(Long rowId) {
		customerDao.delete(rowId);
		return 1;
	}

	@Override
	public Customer getCustomerById(Long rowId) {
		
		return customerDao.get(rowId);
	}

	@Override
	public Integer doEditCustomer(Customer customer) {
		Long rowId =customer.getRowId();
		Customer editCustomer=customerDao.get(rowId);
		customerDao.update(DAOUtils.buildEditData(editCustomer, customer));
		return 1;
	}

	@Override
	public Integer getCount(Customer searchCustomer) {
		
		return customerDao.getCount(searchCustomer);
	}

	@Override
	public LayResult findCustomerByPage(Integer page, Integer limit, Customer searchCustomer) {
		Customer searchParam = DAOUtils.buildSearchParam(searchCustomer);
		Integer dataCount =customerDao.getCount(searchParam);
		List<Customer> customerList=customerDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		return new LayResult(0, "", dataCount, customerList);
	}

	@Override
	public List<Customer> findAllCustomer() {
		
		return customerDao.find();
	}

	@Override
	public List<Area> findByCode(Integer areaCode) {
		
		 return areaDao.findByParent(areaCode);
	}

	@Override
	public LayResult findPublicByPage(Integer page, Integer limit, Customer searchCustomer) {
		
		searchCustomer.setIsPublic(1);
		Customer searchParam = DAOUtils.buildSearchParam(searchCustomer);
		Integer dataCount =customerDao.getCount(searchParam);
		List<Customer> customerList=customerDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		
		
		return new LayResult(0, "", dataCount, customerList);
		
	}

}
