package com.dduongdev.services;

import java.util.Optional;

import com.dduongdev.entities.User;

public interface UserService {
	Optional<User> getByUsername(String username);
	void signup(User user);
}
