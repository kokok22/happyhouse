package com.ssafy.happy.model.dao;

import java.util.List;

import com.ssafy.happy.model.HouseInfoDto;
import com.ssafy.happy.model.SidoGugunCodeDto;

public interface HouseMapDao {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
}
