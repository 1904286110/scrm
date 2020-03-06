package com.situ.scrm.ods.charts.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.ods.charts.domain.ChartsData;
import com.situ.scrm.ods.charts.service.ChartsService;
@RestController
@RequestMapping("/charts")
public class ChartsController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX = "charts"; 
    @Autowired
	private ChartsService chartsService;
    @GetMapping("/conttype")
    public List<ChartsData> findBarData4ContType() {
    	return chartsService.findBarData4ContType();
    }
    @GetMapping("/contstatus")
    public List<ChartsData> findBarData4ContStatus() {
    	return chartsService.findBarData4ContStatus();
    }
    /**
	 * @Title: goIndex
	 * @Description:(进首页)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping
	public ModelAndView goIndex(ModelAndView modelAndView) {
		modelAndView.setViewName(PAGE_INDEX);
		return modelAndView;
	}
}
