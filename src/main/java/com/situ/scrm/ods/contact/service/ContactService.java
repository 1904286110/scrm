package com.situ.scrm.ods.contact.service;

import javax.servlet.http.HttpSession;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contact.domain.Contact;

public interface ContactService {
    
	/**
	 * @Title: getCount
	 * @Description:(查询出数据的数量)
	 * @return
	 */
	Integer getCount(Contact searchContact);
	
	/**
	 * @Title: findContactByPage
	 * @Description:(根据分页查询数据)
	 * @param page
	 * @param limit
	 * @param searchContact
	 * @return
	 */
	LayResult findContactByPage(Integer page, Integer limit,Contact searchContact,HttpSession session);


	Contact  getContactById(Long rowId);
	/**
	 * @Title: saveContact
	 * @Description:(新增功能)
	 * @param contact
	 * @return
	 */
	Long saveContact (Contact contact);

	Integer getToDoCount(HttpSession session);
}
