package com.ssafy.happy.model.service;

import java.util.List;

import com.ssafy.happy.model.DealDto;
import com.ssafy.happy.model.HouseInfoDto;
import com.ssafy.happy.model.SidoGugunCodeDto;

public interface HouseMapService {
	
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<DealDto> getAptInDong(String dong) throws Exception;
	
}
