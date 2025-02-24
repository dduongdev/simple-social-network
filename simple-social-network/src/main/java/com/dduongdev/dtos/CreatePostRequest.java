package com.dduongdev.dtos;

public class CreatePostRequest {
	private String title;
	private String body;

	public CreatePostRequest(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}

	public CreatePostRequest() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
