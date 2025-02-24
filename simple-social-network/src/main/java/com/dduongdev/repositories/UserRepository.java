package com.dduongdev.repositories;

import java.util.Optional;

import com.dduongdev.entities.User;

public interface UserRepository {
	Optional<User> findByUsername(String username);
	void save(User user);
}
