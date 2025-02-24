package com.dduongdev.entities;

import java.time.LocalDateTime;

public class UserFollow {
	private int id;
	private int followerId;
	private int followingId;
	private LocalDateTime createdAt;

	public UserFollow() {
		super();
	}

	public UserFollow(int followerId, int followingId, LocalDateTime createdAt) {
		super();
		this.followerId = followerId;
		this.followingId = followingId;
		this.createdAt = createdAt;
	}

	public UserFollow(int id, int followerId, int followingId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.followerId = followerId;
		this.followingId = followingId;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public int getFollowingId() {
		return followingId;
	}

	public void setFollowingId(int followingId) {
		this.followingId = followingId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
