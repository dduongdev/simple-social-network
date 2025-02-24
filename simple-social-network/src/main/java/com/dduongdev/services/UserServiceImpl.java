package com.dduongdev.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dduongdev.dtos.UserResponse;
import com.dduongdev.entities.User;
import com.dduongdev.entities.UserFollow;
import com.dduongdev.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder,
			UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}
	
	@Override
	public Optional<User> getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void signup(User user) {
		userRepository.findByUsername(user.getUsername())
				.ifPresent(existingUser -> {
					throw new ResponseStatusException(HttpStatus.CONFLICT,
							"User with username " + user.getUsername() + " is already exists.");
				});
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void follow(int followerUserId, int followingUserId) {
		userRepository.follow(new UserFollow(followerUserId, followingUserId, LocalDateTime.now()));
	}

	@Override
	public void unfollow(int followerUserId, int followingUserId) {
		userRepository.unfollow(followerUserId, followingUserId);
	}

	@Override
	public List<UserResponse> getNotFollowedUsersPaged(int userId, int pageIndex, int pageSize) {
		return userRepository.getNotFollowedUsersPaged(userId, pageIndex, pageSize).stream()
				.map(user -> new UserResponse(user.getId(), user.getUsername())).toList();
	}

	@Override
	public List<UserResponse> getFollowingUsersPaged(int userId, int pageIndex, int pageSize) {
		return userRepository.getFollowingUsersPaged(userId, pageIndex, pageSize).stream()
				.map(user -> new UserResponse(user.getId(), user.getUsername())).toList();
	}

}
