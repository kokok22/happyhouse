package com.ssafy.happy.model;

public class MemberDetailDto extends MemberDto {
	private String postcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private String joindate;
	
	public MemberDetailDto() {
		super();
	};
	
	public MemberDetailDto(String userId, String lastName, String firstName, String userPwd, String email,
			String postcode, String address, String detailAddress, String extraAddress) {
		
		super(userId, lastName, firstName, userPwd, email);
		this.postcode = postcode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.extraAddress = extraAddress;
	}

	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDetailAddress() {
		return detailAddress;
	}
	
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	public String getExtraAddress() {
		return extraAddress;
	}
	
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
	
	public String getJoindate() {
		return joindate;
	}
	
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
}
