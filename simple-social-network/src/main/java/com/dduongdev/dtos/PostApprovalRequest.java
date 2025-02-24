package com.dduongdev.dtos;

import com.dduongdev.entities.PostApprovalStatus;

public class PostApprovalRequest {
	private int postId;
	private PostApprovalStatus approvalStatus;

	public PostApprovalRequest(int postId, PostApprovalStatus approvalStatus) {
		super();
		this.postId = postId;
		this.approvalStatus = approvalStatus;
	}

	public PostApprovalRequest() {
		super();
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public PostApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(PostApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

}
