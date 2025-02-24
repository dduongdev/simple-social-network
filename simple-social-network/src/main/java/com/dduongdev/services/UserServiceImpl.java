package com.dduongdev.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dduongdev.entities.User;
import com.dduongdev.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
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
				.ifPresent(_ -> {
					throw new ResponseStatusException(HttpStatus.CONFLICT,
							"User with username " + user.getUsername() + " is already exists.");
				});
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
