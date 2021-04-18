package com.ssafy.happy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happy.model.DealDto;
import com.ssafy.happy.model.HouseInfoDto;
import com.ssafy.happy.model.SidoGugunCodeDto;
import com.ssafy.happy.model.service.HouseMapServiceImpl;


@WebServlet("/map")
public class HouseMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		String act = request.getParameter("act");
		if("sido".equals(act))
			sido(request, response);
		else if("gugun".equals(act))
			gugun(request, response);
		else if("dong".equals(act))
			dong(request, response);
		else if("apt".equals(act))
			apt(request, response);
	}

	private void apt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String dong = request.getParameter("dong");
		String aptname = request.getParameter("aptname");
		String sort = request.getParameter("sort");
		
		PrintWriter out = response.getWriter();
		List<DealDto> list = null;
		JSONArray arr = new JSONArray();
		try {
			list = HouseMapServiceImpl.getHouseMapService().getDeal(dong, aptname, sort);
			for(DealDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("no", dto.getNo());
				obj.put("aptName", dto.getAptName());
				obj.put("jibun", dto.getJibun());
				obj.put("price", dto.getDealAmount());
				obj.put("Date", dto.getDealYear()+"/"+dto.getDealMonth()+"/"+dto.getDealDay());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
	}

	private void dong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gugun = request.getParameter("gugun");
		PrintWriter out = response.getWriter();
		List<HouseInfoDto> list = null;
		JSONArray arr = new JSONArray();
		try {
			list = HouseMapServiceImpl.getHouseMapService().getDongInGugun(gugun);
			for(HouseInfoDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("code", dto.getCode());
				obj.put("dong", dto.getDong());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
	}

	private void gugun(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sido = request.getParameter("sido");
		PrintWriter out = response.getWriter();
		List<SidoGugunCodeDto> list = null;
		JSONArray arr = new JSONArray();
		try {
			list = HouseMapServiceImpl.getHouseMapService().getGugunInSido(sido);
			for(SidoGugunCodeDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("gugun_code", dto.getGugunCode());
				obj.put("gugun_name", dto.getGugunName());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}	
	}

	private void sido(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		List<SidoGugunCodeDto> list = null;
		JSONArray arr = new JSONArray();
		try {
			list = HouseMapServiceImpl.getHouseMapService().getSido();
			for(SidoGugunCodeDto dto : list) {
				JSONObject obj = new JSONObject();
				obj.put("sido_code", dto.getSidoCode());
				obj.put("sido_name", dto.getSidoName());
				arr.add(obj);
			}
		} catch (Exception e) {
			arr = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("message_code", "-1");
			arr.add(obj);
			e.printStackTrace();
		} finally {
			out.print(arr.toJSONString());
			out.close();
		}
	}

}