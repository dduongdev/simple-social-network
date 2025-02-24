package com.dduongdev.repositories.inmemory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dduongdev.entities.User;
import com.dduongdev.entities.UserRole;
import com.dduongdev.repositories.UserRepository;

@Repository
public class InMemoryUserRepository implements UserRepository {
	private static Map<String, User> storedUsers;
	
	static {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		storedUsers = new HashMap<>();
		
		User user1 = new User(1, "dduongdev", passwordEncoder.encode("password"), UserRole.ADMIN, LocalDateTime.now());
		User user2 = new User(2, "dduongdev2", passwordEncoder.encode("password"), UserRole.CUSTOMER, LocalDateTime.now());
		
		storedUsers.put(user1.getUsername(), user1);
		storedUsers.put(user2.getUsername(), user2);
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return Optional.ofNullable(storedUsers.get(username));
	}

	@Override
	public void save(User user) {
		storedUsers.put(user.getUsername(), user);
	}

}
