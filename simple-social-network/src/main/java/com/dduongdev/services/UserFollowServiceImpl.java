package com.dduongdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dduongdev.entities.UserFollow;
import com.dduongdev.repositories.UserFollowRepository;

@Service
public class UserFollowServiceImpl implements UserFollowService {
	private UserFollowRepository userFollowRepository;
	
	@Autowired
	public UserFollowServiceImpl(UserFollowRepository userFollowRepository) {
		this.userFollowRepository = userFollowRepository;
	}
	
	@Override
	public void create(UserFollow userFollow) {
		userFollowRepository.save(userFollow);
	}

	@Override
	public void delete(int id) {
		userFollowRepository.delete(id);
	}

}
