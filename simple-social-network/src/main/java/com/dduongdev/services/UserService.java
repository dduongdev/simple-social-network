package com.dduongdev.services;

import java.util.List;
import java.util.Optional;

import com.dduongdev.dtos.UserResponse;
import com.dduongdev.entities.User;

public interface UserService {
	List<UserResponse> getNotFollowedUsersPaged(int userId, int pageIndex, int pageSize);
	List<UserResponse> getFollowingUsersPaged(int userId, int pageIndex, int pageSize);
	Optional<User> getByUsername(String username);
	void signup(User user);
	void follow(int followerUserId, int followingUserId);
	void unfollow(int followerUserId, int followingUserId);
}
