package com.ssafy.happy.model.service;

import com.ssafy.happy.model.MemberDetailDto;
import com.ssafy.happy.model.MemberDto;
import com.ssafy.happy.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	private static MemberService memberService;
	
	private MemberServiceImpl() {};
	
	public static MemberService getMemberService() {
		if(memberService==null)
			memberService = new MemberServiceImpl();
		return memberService;
	}
	
	@Override
	public boolean registerMember(MemberDto memberDto) {
		MemberDetailDto memberDetailDto = (MemberDetailDto) memberDto;
		
		if(memberDetailDto.getEmail()==null)
			return false;
		else if(memberDetailDto.getFirstName()==null)
			return false;
		else if(memberDetailDto.getLastName()==null)
			return false;
		else if(memberDetailDto.getUserId()==null)
			return false;
		else if(memberDetailDto.getUserPwd()==null)
			return false;
		else if(memberDetailDto.getPostcode()==null)
			return false;
		else if(memberDetailDto.getAddress()==null)
			return false;
		else if(memberDetailDto.getDetailAddress()==null)
			return false;
		
		return MemberDaoImpl.getMemberDao().registerMember(memberDto);
	}

	@Override
	public MemberDto login(String userId, String userPwd) {
		return MemberDaoImpl.getMemberDao().login(userId, userPwd);
	}


	@Override
	public boolean modifyMember(MemberDto memberDto) {
		MemberDetailDto memberDetailDto = (MemberDetailDto) memberDto;
		
		if(memberDetailDto.getEmail()==null)
			return false;
		else if(memberDetailDto.getFirstName()==null)
			return false;
		else if(memberDetailDto.getLastName()==null)
			return false;
		else if(memberDetailDto.getUserId()==null)
			return false;
		else if(memberDetailDto.getUserPwd()==null)
			return false;
		else if(memberDetailDto.getPostcode()==null)
			return false;
		else if(memberDetailDto.getAddress()==null)
			return false;
		else if(memberDetailDto.getDetailAddress()==null)
			return false;
		
		return MemberDaoImpl.getMemberDao().modifyMember(memberDto);
	}

	@Override
	public boolean deleteMember(String userId, String userPwd) {
		return MemberDaoImpl.getMemberDao().deleteMember(userId, userPwd);
	}
	
	@Override
	public String find(String userId, String email) {
		return MemberDaoImpl.getMemberDao().find(userId, email);
	}

}
