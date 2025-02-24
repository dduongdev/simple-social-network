package com.dduongdev.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dduongdev.dtos.AuthenticateRequest;
import com.dduongdev.dtos.AuthenticateResponse;
import com.dduongdev.dtos.SignupRequest;
import com.dduongdev.entities.User;
import com.dduongdev.entities.UserRole;
import com.dduongdev.services.JwtService;
import com.dduongdev.services.UserService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	private UserService userService;
	
	@Autowired
	public AuthController(
		AuthenticationManager authenticationManager,
		JwtService jwtService,
		UserService userService
	) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userService = userService;
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<?> authenticate(
			@RequestBody AuthenticateRequest authenticateRequest) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(
									authenticateRequest.getUsername(), 
									authenticateRequest.getPassword())
							);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String jwtToken = jwtService.generateToken(userDetails);
			
			return ResponseEntity.ok(new AuthenticateResponse(jwtToken)); 
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Wrong username or password.");
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
		try {
			User newUser = new User(request.getUsername(), request.getPassword(), UserRole.CUSTOMER, LocalDateTime.now());
			userService.signup(newUser);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Signup successfully.");
		} catch (ResponseStatusException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getBody().getDetail());
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ex.getMessage());
		}
	}
}
