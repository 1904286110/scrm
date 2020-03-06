package com.situ.scrm.ods.charts.service;

import java.util.List;

import com.situ.scrm.ods.charts.domain.ChartsData;

public interface ChartsService {
	 List<ChartsData> findBarData4ContType(); 
	 List<ChartsData> findBarData4ContStatus(); 
}
