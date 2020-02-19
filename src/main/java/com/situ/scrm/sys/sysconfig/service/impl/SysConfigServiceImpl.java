package com.situ.scrm.sys.sysconfig.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.scrm.sys.sysconfig.dao.SysConfigDao;
import com.situ.scrm.sys.sysconfig.domain.SysConfig;
import com.situ.scrm.sys.sysconfig.service.SysConfigService;
import com.situ.scrm.utils.DAOUtils;
@Service
public class SysConfigServiceImpl implements Serializable, SysConfigService {

	private static final long serialVersionUID = 1L;
    @Autowired
	private SysConfigDao sysConfigDao;
	
	@Override
	public Long saveSysConfig(SysConfig sysConfig) {
		sysConfig.setActiveFlag(1);
		sysConfig.setCreateBy("sys");
		sysConfig.setCreateDate(new Date());
		sysConfigDao.save(sysConfig);
		return sysConfig.getRowId();
	}

	@Override
	public Integer updateSysConfig(SysConfig sysConfig) {
		Long rowId=sysConfig.getRowId();
		SysConfig editSysConfig=sysConfigDao.get(rowId);
		editSysConfig=DAOUtils.buildEditData(editSysConfig, sysConfig);
		editSysConfig.setUpdateBy("sys");
		editSysConfig.setUpdateDate(new Date());
		sysConfigDao.update(editSysConfig);
		return 1;
	}

	@Override
	public SysConfig findSysConfig() {
		
		return sysConfigDao.get(1L);
	}
	/**
	 * @Title: initSysConfigData 
	 * @Description:(初始化数据)
	 */
	@Override
	@PostConstruct
	public void initSysConfigData() {
		List<SysConfig> sysConfigList=sysConfigDao.find();
		//判断如果没有数据，则写入一条数据
				if (sysConfigList == null || sysConfigList.isEmpty()) {
					SysConfig sysConfig=new SysConfig();
					sysConfig.setActiveFlag(1);
					sysConfig.setCreateBy("sys");
					sysConfig.setCreateDate(new Date());
					sysConfig.setComName("situ");
					sysConfig.setCusDay(0);
					sysConfig.setOrderDay(0);
					sysConfigDao.save(sysConfig);
				}
		
	}

}
