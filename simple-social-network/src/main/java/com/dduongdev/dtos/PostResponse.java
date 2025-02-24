package com.dduongdev.dtos;

import java.time.LocalDateTime;

import com.dduongdev.entities.PostApprovalStatus;

public class PostResponse {
	private int id;
	private String title;
	private String body;
	private int userId;
	private String username;
	private PostApprovalStatus approvalStatus;
	private LocalDateTime createdAt;

	public PostResponse(int id, String title, String body, int userId, String username,
			PostApprovalStatus approvalStatus, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.username = username;
		this.approvalStatus = approvalStatus;
		this.createdAt = createdAt;
	}

	public PostResponse(String title, String body, int userId, String username, PostApprovalStatus approvalStatus,
			LocalDateTime createdAt) {
		super();
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.username = username;
		this.approvalStatus = approvalStatus;
		this.createdAt = createdAt;
	}

	public PostResponse() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public PostApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(PostApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
