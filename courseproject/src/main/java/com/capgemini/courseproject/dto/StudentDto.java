package com.capgemini.courseproject.dto;

public class StudentDto {

	private Long userId;
	private String userName;

	public StudentDto(Long userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public StudentDto() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
