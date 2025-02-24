package com.dduongdev.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dduongdev.configs.JwtConfig;
import com.dduongdev.entities.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtServiceImpl implements JwtService {
	private JwtConfig jwtConfig;
	private SecretKey secretKey;
	private static final String BEARER_PREFIX = "Bearer ";
	
	@Autowired
	public JwtServiceImpl(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
		
		secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
	}
	
	
	@Override
	public Optional<String> parseTokenFrom(HttpServletRequest request) {
		String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
			return Optional.of(headerAuth.substring(7));
		}
		
		return Optional.empty();
	}

	@Override
	public boolean validateToken(String token) {
		try {
			Jwts
			.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token);
			
			return true;
		} catch (MalformedJwtException | 
				ExpiredJwtException | 
				UnsupportedJwtException | 
				IllegalArgumentException ex) {
			return false;
		}
	}

	@Override
	public Optional<String> extractUsernameFrom(String token) {
		Jws<Claims> jwsClaims = Jwts
									.parser()
									.verifyWith(secretKey)
									.build()
									.parseSignedClaims(token);
		Claims claims = jwsClaims.getPayload();
					
		return Optional.ofNullable(claims.getSubject());
	}

	@Override
	public Optional<Integer> extractUserIdFrom(String token) {
		Jws<Claims> jwsClaims = Jwts
									.parser()
									.verifyWith(secretKey)
									.build()
									.parseSignedClaims(token);
		Claims claims = jwsClaims.getPayload();
		return Optional.ofNullable(claims.get("userId", Integer.class));
	}

	@Override
	public List<String> extractAuthoritiesFrom(String token) {
		Jws<Claims> jwsClaims = Jwts
									.parser()
									.verifyWith(secretKey)
									.build()
									.parseSignedClaims(token);
		Claims claims = jwsClaims.getPayload();
		
		List<?> rawAuthorities = claims.get("Authorities", List.class);
		
		return rawAuthorities.stream()
							.map(Object::toString)
							.toList();
	}


	@Override
	public String generateToken(UserDetails userDetails) {
		List<String> authorities = userDetails.getAuthorities()
												.stream()
												.map(GrantedAuthority::getAuthority)
												.toList();
		Map<String, Object> claims = new HashMap<>(); 
		claims.put("Authorities", authorities);
		claims.put("userId", ((CustomUserDetails) userDetails).getUserId());
		
		return Jwts.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
				.signWith(secretKey)
				.compact();
	}

}
