package com.situ.scrm.sys.sysconfig.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.sys.sysconfig.domain.SysConfig;
import com.situ.scrm.sys.sysconfig.service.SysConfigService;
@RestController
@RequestMapping("/sysconfig")
public class SysConfigController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_SYSCONFUG_INDEX = "sys/sysconfig/sysconfig_index";
	@Autowired
	private SysConfigService sysConfigService;
	
	
	/**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.addObject("sysConfig", sysConfigService.findSysConfig());
		modelAndView.setViewName(PAGE_SYSCONFUG_INDEX);
		return modelAndView;
	}
	
	/**
	 * @Title: doEditSysConfig
	 * @Description:(执行修改)
	 * @param sysConfig
	 * @return
	 */
	@PutMapping
	public Integer doEditSysConfig(SysConfig sysConfig) {
		return sysConfigService.updateSysConfig(sysConfig);
	}
	
}
