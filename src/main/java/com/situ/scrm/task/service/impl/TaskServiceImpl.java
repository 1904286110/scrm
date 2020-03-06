package com.situ.scrm.task.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.situ.scrm.ods.contact.dao.ContactDao;
import com.situ.scrm.sys.customer.dao.CustomerDao;
import com.situ.scrm.sys.syscount.dao.SysCountDao;
import com.situ.scrm.sys.syscount.domain.SysCount;
import com.situ.scrm.task.service.TaskService;
import com.situ.scrm.utils.AppConfig;
@Service
@EnableScheduling
public class TaskServiceImpl implements Serializable, TaskService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private SysCountDao sysCountDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private ContactDao contactDao;
	@Override
	@Transactional
	@Scheduled(cron = "0 0 0 * * ?")
	public void autoJob4Day() {
		sysCountDao.updatePlus(SysCount.INDEX4, 0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -AppConfig.CUST_PUBLIC_DAY);
		Date checkDate=cal.getTime();
		customerDao.updateByLastCurrent(checkDate);
		contactDao.updateBycheckDate(checkDate);
		System.out.println("这是通过注释由任务调度自动调用的" + new Date().toLocaleString());

	
	}

}
