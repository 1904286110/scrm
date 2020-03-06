package com.situ.scrm.sys.customer.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.sys.customer.domain.Customer;
import com.situ.scrm.sys.customer.service.CustomerService;
@RestController
@RequestMapping("/public")
public class PublicController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_PUBLIC_INDEX = "sys/customer/public_index";
    
	 @Autowired
	private CustomerService customerService;
	/**
	 * @Title: goPublic
	 * @Description:(进公海)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goPublic(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_PUBLIC_INDEX);
		return modelAndView;
	}

	@GetMapping("/{page}/{limit}")
	public LayResult findPublicByPage(@PathVariable Integer page, @PathVariable Integer limit, Customer searchCustomer) {
		return customerService.findPublicByPage(page, limit, searchCustomer);
	}

}
