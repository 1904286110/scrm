package com.situ.scrm.sys.customer.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.area.domain.Area;
import com.situ.scrm.sys.customer.domain.Customer;
import com.situ.scrm.sys.customer.service.CustomerService;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.role.domain.Role;
@RestController
@RequestMapping("/customer")
public class CustomerController implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private static final String PAGE_CUSTOMER_INDEX = "sys/customer/customer_index";
	private static final String PAGE_CUSTOMER_ADD_EDIT = "sys/customer/customer_add_edit";
	
    @Autowired
	private CustomerService customerService;
    @Autowired
	private DictionaryService dictionaryService;
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_CUSTOMER_INDEX);
		return modelAndView;
	}
	
	
	
	/**
	 * @Title: /form
	 * @Description:(进新增或修改页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goAddOrEdit(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_CUSTOMER_ADD_EDIT);
		modelAndView.addObject("areaList", customerService.findByCode(-1));
		modelAndView.addObject("dictionaryList", dictionaryService.useDictionaryList());
		return modelAndView;
	}
	

	/**
	 * @Title: findCustomerByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchCustomer 查询的条件
	 * @return
	 */
	
	@GetMapping("/{page}/{limit}")
	public LayResult findCustomerByPage(@PathVariable Integer page, @PathVariable Integer limit, Customer searchCustomer) {
		return customerService.findCustomerByPage(page, limit, searchCustomer);
	}
	
	
	/**
	 * @Title: checkCustomerName
	 * @Description:(检测角色名称的唯一)
	 * @param cusName
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkCustomerName(String cusName) {
		return customerService.checkCusName(cusName);
	}
	/**
	 * @Title: findAreaByParent
	 * @Description:(三级联动)
	 * @param parentCode
	 * @return
	 */
	@GetMapping("/list/{parentCode}")
	public List<Area> findAreaByParent(@PathVariable Integer parentCode) {
		return customerService.findByCode(parentCode);
	}

	/**
	 * @Title: doAddCustomer
	 * @Description:(执行新增功能)
	 * @param customer
	 * @return
	 */
	@PostMapping
	public Long doAddCustomer(Customer customer) {
		return customerService.saveCustomer(customer);
	}
	
	/**
	 * @Title: doDeleteCustomer
	 * @Description:(执行删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteCustomer(@PathVariable Long rowId) {
		return customerService.doDeleteCustomer(rowId);
	}
	
	/**
	 * @Title: goEditRole
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public Customer goEditCustomer(@PathVariable Long rowId) {
		return customerService.getCustomerById(rowId);
	}
	
	/**
	 * @Title: doEditCustomer
	 * @Description:(执行修改)
	 * @param customer
	 * @return
	 */
	@PutMapping
	public Integer doEditCustomer(Customer customer) {
		return customerService.doEditCustomer(customer);
	}
}
