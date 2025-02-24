package com.dduongdev.dtos;

public class UserResponse {
	private int userId;
	private String username;
	
	public UserResponse(int userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
