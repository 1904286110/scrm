package com.situ.scrm.ods.contact.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contact.domain.Contact;
import com.situ.scrm.ods.contact.service.ContactService;
import com.situ.scrm.sys.dictionary.service.DictionaryService;
import com.situ.scrm.sys.role.domain.Role;
import com.situ.scrm.utils.AppConfig;
@RestController
@RequestMapping("/contact")
public class ContactController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PAGE_CONTACT_INDEX = "ods/contact/contact_index";
	private static final String PAGE_CONTACT_ADD_EDIT = "ods/contact/contact_add_edit";
	@Autowired
	private ContactService contactService;
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
		modelAndView.setViewName(PAGE_CONTACT_INDEX);
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
		modelAndView.setViewName(PAGE_CONTACT_ADD_EDIT);
		modelAndView.addObject("dictionaryList", dictionaryService.useDictionaryList());
		return modelAndView;
	}
	
	/**
	 * @Title: findContactByPage
	 * @Description:(根据分页查询数量)
	 * @param page       页号
	 * @param limit      每页显示的数据数量
	 * @param searchContact 查询的条件
	 * @return
	 */
	
	@GetMapping("/{page}/{limit}")
	public LayResult findContactByPage(@PathVariable Integer page, @PathVariable Integer limit, Contact searchContact,HttpSession session) {
		return contactService.findContactByPage(page, limit, searchContact, session);
	}
	
	/**
	 * @Title: doAddContact
	 * @Description:(执行新增功能)
	 * @param contact
	 * @return
	 */
	@PostMapping
	public Long doAddContact(Contact contact) {
		return contactService.saveContact(contact);
	}
	@GetMapping("/data")
	public String  getDate() {
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE,AppConfig.CONT_REMIND_DAY);
		Date checkDate=cal.getTime();
		 String obj = simpleDateFormat.format(checkDate);
		return obj;
		
	}
}
