package com.dduongdev.repositories;

import java.util.List;

import com.dduongdev.entities.UserFollow;

public interface UserFollowRepository {
	List<UserFollow> findByFollowerId(int followerId);
	void save(UserFollow userFollow);
	void delete(int id);
}
