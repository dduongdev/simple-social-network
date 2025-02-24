package com.dduongdev.dtos;

public class AuthenticateResponse {
	private String token;

	public AuthenticateResponse() {
	}

	public AuthenticateResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
