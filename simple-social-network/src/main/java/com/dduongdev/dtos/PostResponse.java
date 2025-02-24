package com.dduongdev.dtos;

import java.time.LocalDateTime;

import com.dduongdev.entities.PostApprovalStatus;

public class PostResponse {
	private int id;
	private String title;
	private String body;
	private int userId;
	private PostApprovalStatus approvalStatus;
	private LocalDateTime createdAt;
	private boolean isMine;

	public PostResponse() {
		super();
	}

	public PostResponse(String title, String body, int userId, PostApprovalStatus approvalStatus,
			LocalDateTime createdAt, boolean isMine) {
		super();
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.approvalStatus = approvalStatus;
		this.createdAt = createdAt;
		this.isMine = isMine;
	}

	public PostResponse(int id, String title, String body, int userId, PostApprovalStatus approvalStatus,
			LocalDateTime createdAt, boolean isMine) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.approvalStatus = approvalStatus;
		this.createdAt = createdAt;
		this.isMine = isMine;
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

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
}
