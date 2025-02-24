package com.dduongdev.repositories;

import java.util.List;
import java.util.Optional;

import com.dduongdev.entities.User;
import com.dduongdev.entities.UserFollow;

public interface UserRepository {
	Optional<User> findByUsername(String username);
	List<User> findAllFollowings(int followerUserId);
	List<User> getNotFollowedUsersPaged(int userId, int pageIndex, int pageSize);
	List<User> getFollowingUsersPaged(int userId, int pageIndex, int pageSize);
	void save(User user);
	void follow(UserFollow userFollow);
	void unfollow(int followerUserId, int followingUserId);
}
