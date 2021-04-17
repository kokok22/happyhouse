package com.ssafy.happy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.happy.model.HouseInfoDto;
import com.ssafy.happy.model.MemberDetailDto;
import com.ssafy.happy.model.MemberDto;
import com.ssafy.happy.model.SidoGugunCodeDto;
import com.ssafy.happy.model.service.HouseMapServiceImpl;
import com.ssafy.happy.model.service.MemberServiceImpl;

@WebServlet("/main")
public class MainController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String root = request.getContextPath();
		
		if("login".equals(act))
			login(request, response);
		else if("mvlogin".equals(act))
			response.sendRedirect(root+"/user/login.jsp");
		else if("logout".equals(act))
			logout(request, response);
		else if("mvsignup".equals(act))
			response.sendRedirect(root+"/user/signup.jsp");
		else if("signup".equals(act))
			signup(request, response);
		else if("idcheck".equals(act))
			idcheck(request, response);
		else if("mvmodify".equals(act))
			response.sendRedirect(root+"/user/modify.jsp");
		else if("modify".equals(act))
			modify(request, response);
		else if("mvdelete".equals(act))
			response.sendRedirect(root+"/user/secession.jsp");
		else if("delete".equals(act))
			delete(request, response);
		else if("mvforgot".equals(act))
			response.sendRedirect(root+"/user/forgot.jsp");
		else if("find".equals(act))
			find(request, response);
		else if("news".equals(act))
			response.sendRedirect(root+"/board/news.jsp");
		else if("mvsearch".equals(act))
			response.sendRedirect(root+"/deal/search.jsp");
	}

	private void idcheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("id");

		JSONArray arr = new JSONArray();
		PrintWriter out = response.getWriter();
		try {
			boolean flag = MemberServiceImpl.getMemberService().chkId(userId);
			if(flag) {
				JSONObject obj = new JSONObject();
				obj.put("chk", true);
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

	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		
		String userPwd = MemberServiceImpl.getMemberService().find(userId, email);
		String path = "/user/forgot.jsp";
		
		if(userPwd == null)
			request.setAttribute("msg", "아이디 또는 이메일이 존재하지 않습니다.");
		else
			request.setAttribute("msg", "찾고자하는 아이디의 비밀번호는 '"+userPwd+"'입니다.");

		request.getRequestDispatcher(path).forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto)session.getAttribute("userinfo");
		
		String userId = memberDto.getUserId();
		String userPwd = request.getParameter("userPwd");
		
		String path = "/index.jsp";
		
		if(!MemberServiceImpl.getMemberService().deleteMember(userId, userPwd)) {
			request.setAttribute("msg", "비밀번호가 다르거나, 삭제 도중 문제가 발생했습니다.");
			path = "/user/secession.jsp";
		}else {
			session.invalidate();
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	private MemberDto getInfo(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		
		MemberDetailDto memberDetailDto = new MemberDetailDto(userId, lastName, firstName, userPwd, email, postcode,
				address, detailAddress, extraAddress);
		
		return memberDetailDto;
	}

	// 개인정보 수정
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDetailDto memberDetailDto = (MemberDetailDto)getInfo(request, response);
		
		String path = "/index.jsp";
		
		if(!MemberServiceImpl.getMemberService().modifyMember(memberDetailDto)) {
			request.setAttribute("msg", "정보수정에 실패했습니다. 빈칸이 없는지 확인해주세요.");
			path = "/user/modify.jsp";
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", memberDetailDto);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	// 회원 가입
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDetailDto memberDetailDto = (MemberDetailDto)getInfo(request, response);
		
		String path = "/index.jsp";
		
		// 실패
		if(!MemberServiceImpl.getMemberService().registerMember(memberDetailDto)) {
			request.setAttribute("msg", "가입에 실패하셨습니다. 아이디 중복여부나 빈칸이 없는지 확인해주세요.");
			path = "/user/signup.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");			// 사용자 아이디
		String userPwd = request.getParameter("userPwd");		// 사용자 비밀번호
		
		String path = "/index.jsp";
		
		// DB에 저장된 정보, 입력 값이 DB에 존재하면 해당하는 계정의 사용자 정보가 Return 된다.
		// 존재하지 않으면 null값이 return된다.
		MemberDetailDto memberDetailDto =  (MemberDetailDto) MemberServiceImpl.getMemberService().login(userId, userPwd);
		
		// 로그인 성공
		if(memberDetailDto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", memberDetailDto);
			
			String isdv = request.getParameter("idsave");
			
			// 아이디 저장
			if("saveok".equals(isdv)) {
				Cookie cookie = new Cookie("save_id", userId);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				
				response.addCookie(cookie);
			
			// 아이디 저장 x
			}else {
				Cookie cookies[] = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("save_id")) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					}
				}
			}	
		
		// 로그인 실패
		} else {
			request.setAttribute("msg", "가입하지 않은 아이디거나, 잘못된 비밀번호입니다");
			path = "/user/login.jsp";
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}
	
}
