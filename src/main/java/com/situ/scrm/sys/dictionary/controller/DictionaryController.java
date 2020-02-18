package com.situ.scrm.sys.dictionary.controller;

import java.io.Serializable;

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
import com.situ.scrm.sys.dictionary.domain.Dictionary;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.sysresource.domain.SysResource;
@RestController
@RequestMapping("/dictionary")
public class DictionaryController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_INDEX_DICTIONARY = "sys/dictionary/dictionary_index";
	private static final String PAGE_ADD_EDIT_DICTIONARY = "sys/dictionary/dictionary_add_edit";
	
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
		modelAndView.setViewName(PAGE_INDEX_DICTIONARY);
		return modelAndView;
	}
    
	/**
	 * @Title: findAllDictionary
	 * @Description:(查询出所有的资源信息)
	 * @return
	 */
	@GetMapping("/list")
	public LayResult findAllDictionary() {
		return dictionaryService.findAllDictionary();
	}
    
	/**
	 * @Title: /form
	 * @Description:(进新增或修改页面)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/form")
	public ModelAndView goAddOrEdit(ModelAndView modelAndView) {
		// 默认的父类CODE
		modelAndView.addObject("parentKey", SysResource.DEFAULT_PARENT_CODE);
		modelAndView.setViewName(PAGE_ADD_EDIT_DICTIONARY);
		return modelAndView;
	}
	
	/**
	 * @Title: saveDictionary
	 * @Description:(新增资源信息)
	 * @param dictionary
	 * @return
	 */
	@PostMapping
	public Long saveDictionary(Dictionary dictionary) {
		return dictionaryService.saveDictionary(dictionary);
	}
	
	/**
	 * @Title: checkDictInfo
	 * @Description:(检测名称唯一)
	 * @return
	 */
	@GetMapping("/checkname")
	public Integer checkDictInfo(String dictInfo, String parentKey) {
		return dictionaryService.checkDictInfo(dictInfo, parentKey);
	}
	
	/**
	 * @Title: goAddChildForm
	 * @Description:(进新增自资源)
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/child/{parentId}")
	public ModelAndView goAddChildForm(@PathVariable Long parentId, ModelAndView modelAndView) {
		// 默认的父类CODE
		
		Dictionary parentDictionary =dictionaryService.getDictionaryById(parentId);
		modelAndView.addObject("parentKey", parentDictionary.getDictKey());
		modelAndView.addObject("parentName", parentDictionary.getDictInfo());
		modelAndView.setViewName(PAGE_ADD_EDIT_DICTIONARY);
		return modelAndView;
	}
	
	/**
	 * @Title: getDictionaryById
	 * @Description:(进修改)
	 * @param rowId
	 * @return
	 */
	@GetMapping("/{rowId}")
	public Dictionary getDictionaryById(@PathVariable Long rowId) {
		Dictionary dictionary= dictionaryService.getAllDictionaryById(rowId);
		return dictionary;
	}
	
	/**
	 * @Title: updateDictionary
	 * @Description:(修改资源)
	 * @param dictionary
	 * @return
	 */
	@PutMapping
	public Long updateDictionary(Dictionary dictionary) {
		return dictionaryService.updateDictionary(dictionary);
	}
	
	/**
	 * @Title: doDeleteDictionary
	 * @Description:(删除)
	 * @param rowId
	 * @return
	 */
	@DeleteMapping("/{rowId}")
	public Integer doDeleteDictionary(@PathVariable Long rowId) {
		return dictionaryService.doDeleteDictionary(rowId);
	}
}
