package com.dduongdev.entities;

import java.time.LocalDateTime;

public class User {
	private int id;
	private String username;
	private String password;
	private UserRole role;
	private LocalDateTime createdAt;

	public User() {
		super();
	}

	public User(String username, String password, UserRole role, LocalDateTime createdAt) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
	}

	public User(int id, String username, String password, UserRole role, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
