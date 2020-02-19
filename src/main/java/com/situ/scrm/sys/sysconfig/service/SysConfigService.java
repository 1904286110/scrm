package com.situ.scrm.sys.sysconfig.service;

import com.situ.scrm.sys.sysconfig.domain.SysConfig;

public interface SysConfigService {
   Long saveSysConfig(SysConfig sysConfig);
   Integer updateSysConfig(SysConfig sysConfig);
   SysConfig findSysConfig();
	/**
	 * @Title: initSysConfigData 
	 * @Description:(初始化数据)
	 */
	void initSysConfigData();
}
