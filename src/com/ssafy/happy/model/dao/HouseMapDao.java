package com.ssafy.happy.model.dao;

import java.util.List;

import com.ssafy.happy.model.DealDto;
import com.ssafy.happy.model.HouseInfoDto;
import com.ssafy.happy.model.SidoGugunCodeDto;

public interface HouseMapDao {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<DealDto> getAptInDong(String dong, String aptname, String sort) throws Exception;
	List<DealDto> getDeal(String dong, String aptname, String sort) throws Exception;
	
}
