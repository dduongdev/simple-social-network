package com.dduongdev.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dduongdev.entities.CustomUserDetails;
import com.dduongdev.entities.User;
import com.dduongdev.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final String ROLE_PREFIX = "ROLE_";
	private UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> foundUserOpt = userRepository.findByUsername(username);
		if (foundUserOpt.isEmpty()) {
			throw new UsernameNotFoundException("No user found with this username: " + username);
		}
		
		User user = foundUserOpt.get();
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().toString()));
		
		return new CustomUserDetails(
				user.getId(),
				user.getUsername(), 
				user.getPassword(), 
				authorities);
	}
	
}
