package com.ssafy.happy.model.dao;

import com.ssafy.happy.model.MemberDto;

public interface MemberDao {

	// 회원가입
	boolean registerMember(MemberDto memberDto);

	// 로그인
	MemberDto login(String userId, String userPwd);

	// 회원정보 수정
	boolean modifyMember(MemberDto memberDto);
	
	// 회원탈퇴
	boolean deleteMember(String userId, String userPwd);
	
	// 비밇번호 찾기
	String find(String userId, String email);
	
	// 아이디 중복 확인
	boolean chkId(String userId);
}
