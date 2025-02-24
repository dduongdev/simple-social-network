package com.dduongdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {
	Optional<String> parseTokenFrom(HttpServletRequest request);
	boolean validateToken(String token);
	Optional<String> extractUsernameFrom(String token);
	Optional<Integer> extractUserIdFrom(String token);
	List<String> extractAuthoritiesFrom(String token);
	String generateToken(UserDetails userDetails);
}
