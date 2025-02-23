package com.dduongdev.configs;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dduongdev.entities.CustomUserDetails;
import com.dduongdev.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	private JwtService jwtService;
	
	@Autowired
	public JwtRequestFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("JwtRequestFilter is running for: " + request.getRequestURI());
		System.out.println("JwtRequestFilter is running for: " + request.getRequestURI());
		Optional<String> jwtTokenOpt = jwtService.parseTokenFrom(request);
		
		if (jwtTokenOpt.isPresent() && jwtService.validateToken(jwtTokenOpt.get())) {
			String jwtToken = jwtTokenOpt.get();
			Optional<String> extractedUsernameOpt = jwtService.extractUsernameFrom(jwtToken);
			Optional<Integer> extractedUserIdOpt = jwtService.extractUserIdFrom(jwtToken);
			List<SimpleGrantedAuthority> extractedAuthorities = jwtService.extractAuthoritiesFrom(jwtToken)
													.stream().map(SimpleGrantedAuthority::new)
													.toList();
			
			/**
			 * Check valid for (if necessary): 
			 * extractedUsernameOpt,
			 * extractedUserIdOpt,
			 * extractedAuthorities
			 */
			
			String extractedUsername = extractedUsernameOpt.get();
			int extractedUserId = extractedUserIdOpt.get();
			
			UserDetails userDetails = new CustomUserDetails(
					extractedUserId, 
					extractedUsername, 
					"", 
					extractedAuthorities);
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails, "", extractedAuthorities);
			usernamePasswordAuthenticationToken
				.setDetails(
					new WebAuthenticationDetailsSource()
					.buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		
		filterChain.doFilter(request, response);
	}
	
}
