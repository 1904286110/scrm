package com.situ.scrm.ods.contact.service.impl;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.commons.domain.LayResult;
import com.situ.scrm.ods.contact.dao.ContactDao;
import com.situ.scrm.ods.contact.domain.Contact;
import com.situ.scrm.ods.contact.service.ContactService;
import com.situ.scrm.sys.customer.dao.CustomerDao;
import com.situ.scrm.sys.customer.domain.Customer;
import com.situ.scrm.sys.dictionary.util.DictionaryUtil;
import com.situ.scrm.sys.syscount.util.SysCountUtils;
import com.situ.scrm.sys.user.domain.User;
import com.situ.scrm.utils.AppConfig;
import com.situ.scrm.utils.DAOUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ContactServiceImpl implements Serializable, ContactService {

	private static final long serialVersionUID = 1L;
    @Autowired
	private ContactDao contactDao;
    @Autowired
	private CustomerDao customerDao;
    @Autowired
	private SysCountUtils sysCountUtils;
	 @Autowired
		private DictionaryUtil dictionaryUtil;
	@Override
	public Integer getCount(Contact searchContact) {
		
		return contactDao.getCount(searchContact);
	}

	@Override
	public LayResult findContactByPage(Integer page, Integer limit, Contact searchContact,HttpSession session) {
		User loginUser = (User) session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		String userCode =loginUser.getUserCode();
		Integer userLevel = loginUser.getUserLevel();
		
		switch (userLevel) {
		case 1://- 级用户查看所有的数据
			break;
		case 2://二级用户查看下属业务员的数据
			searchContact.setParentCode(userCode);
			break;
		case 3://三级用户只能查看自己的数据
			searchContact.setUserCode(userCode);
			break;
	  default:
			break;
		}
		
		
		Contact searchParam = DAOUtils.buildSearchParam(searchContact);
		
		Integer dataCount =contactDao.getCount(searchParam);
		List<Contact> contactList=contactDao.findByPage(DAOUtils.buildPagination(page, limit), searchParam);
		Map<String,String> dataMap = dictionaryUtil.getStringDataMap("contType");
		Map<String,String> ststusMap = dictionaryUtil.getStringDataMap("contStatus");
		for(Contact cont:contactList) {
			cont.setContTypeVal(dataMap.get(cont.getContType()));
			cont.setContStatusVal(ststusMap.get(cont.getContStatus()));
		}
		
		return new LayResult(0, "", dataCount, contactList);
	}

	@Override
	public Contact getContactById(Long rowId) {
		
		return contactDao.get(rowId);
	}

	@Override
	public Long saveContact(Contact contact) {
		Customer customer=customerDao.getByName(contact.getCustName());
		customer.setIsPublic(0);
		customer.setLastCurrent(new Date());
		customerDao.update(customer);
		if(contact.getContCode()!=null) {
			contactDao.changeByCode(contact.getContCode());
		}
		contact.setContCode(sysCountUtils.buildContCode());
		contact.setActiveFlag(1);
		contact.setIsRemind(1);
		contact.setCreateBy("sys");
		contact.setCreateDate(new Date());
		contactDao.save(contact);
		return contact.getRowId();
	}

	@Override
	public Integer getToDoCount(HttpSession session) {
		User loginUser = (User) session.getAttribute(AppConfig.SESSION_LOGIN_USER);
		
		Integer userLevel = loginUser.getUserLevel();
		if(userLevel==3) {
			String userCode =loginUser.getUserCode();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE,AppConfig.CONT_REMIND_DAY);
			Date checkDate=cal.getTime();
			return contactDao.getToDoCount(userCode, checkDate);
		}
		return 0;
	}

}
