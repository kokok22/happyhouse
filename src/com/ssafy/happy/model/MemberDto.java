package com.ssafy.happy.model;

public class MemberDto {
	private String userId;
	private String lastName;
	private String firstName;
	private String userPwd;
	private String email;
	
	public MemberDto() {};
	
	public MemberDto(String userId, String lastName, String firstName, String userPwd, String email) {
		this.userId = userId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.userPwd = userPwd;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
}
