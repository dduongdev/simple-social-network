package com.dduongdev.services;

import com.dduongdev.entities.UserFollow;

public interface UserFollowService {
	void create(UserFollow userFollow);
	void delete(int id);
}
