package com.ssafy.happy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happy.model.DealDto;
import com.ssafy.happy.model.HouseInfoDto;
import com.ssafy.happy.model.SidoGugunCodeDto;
import com.ssafy.util.DBUtil;

public class HouseMapDaoImpl implements HouseMapDao {
	
	private static HouseMapDao houseMapDao;
	
	private HouseMapDaoImpl() {}
	
	public static HouseMapDao getHouseMapDao() {
		if(houseMapDao == null)
			houseMapDao = new HouseMapDaoImpl();
		return houseMapDao;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoGugunCodeDto> list = new ArrayList<SidoGugunCodeDto>();
		try {
			conn = DBUtil.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(sido_code,2) sido_code, sido_name FROM sidocode \n");
			sql.append("ORDER BY sido_code");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoGugunCodeDto dto = new SidoGugunCodeDto();
				dto.setSidoCode(rs.getString("sido_code"));
				dto.setSidoName(rs.getString("sido_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoGugunCodeDto> list = new ArrayList<SidoGugunCodeDto>();
		try {
			conn = DBUtil.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(gugun_code,5) gugun_code, gugun_name FROM guguncode \n");
			sql.append("where left(gugun_code,2) = ?");
			sql.append("ORDER BY gugun_code");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoGugunCodeDto dto = new SidoGugunCodeDto();
				dto.setGugunCode(rs.getString("gugun_code"));
				dto.setGugunName(rs.getString("gugun_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseInfoDto> list = new ArrayList<HouseInfoDto>();
		try {
			conn = DBUtil.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct dong, code FROM houseinfo\n");
			sql.append("where code = ? \n");
			sql.append("ORDER BY dong");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseInfoDto dto = new HouseInfoDto();
				dto.setCode(rs.getString("code"));
				dto.setDong(rs.getString("dong"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<DealDto> getAptInDong(String dong, String aptname, String sort) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DealDto> list = new ArrayList<DealDto>();
		try {
			conn = DBUtil.getConnect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT d.no, d.dong, d.code, d.AptName, d.dealYear, d.dealMonth, d.dealday, d.jibun, d.dealAmount\n");
			sql.append("FROM housedeal as d\n");
			if(aptname.equals(""))
				sql.append("where d.dong = ? \n");
			else
				sql.append("where d.dong = ? and d.AptName like '%"+aptname+"%'\n");
			
			if(sort.equals("default"))				// 기본 정렬
				sql.append("ORDER BY AptName, dealyear, length(dealmonth), dealmonth, length(dealday), dealday");
			else if(sort.equals("high-price"))		// 높은 가격 순
				sql.append("ORDER BY AptName, dealAmount desc, dealyear, length(dealmonth), dealmonth, length(dealday), dealday");
			else if(sort.equals("lower-price"))		// 낮은 가격 순
				sql.append("ORDER BY AptName, dealAmount, dealyear, length(dealmonth), dealmonth, length(dealday), dealday");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DealDto dto = new DealDto();
				dto.setNo(Integer.parseInt(rs.getString("no")));
				dto.setDong(rs.getString("dong"));
				dto.setCode(rs.getString("code"));
				dto.setAptName(rs.getString("AptName"));
				dto.setDealYear(rs.getString("dealYear"));
				dto.setDealMonth(rs.getString("dealMonth"));
				dto.setDealDay(rs.getString("dealday"));
				dto.setJibun(rs.getString("jibun"));
				dto.setDealAmount(rs.getString("dealAmount"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

}
