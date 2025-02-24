package com.dduongdev.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dduongdev.dtos.FollowRequest;
import com.dduongdev.dtos.UnfollowRequest;
import com.dduongdev.entities.CustomUserDetails;
import com.dduongdev.entities.UserFollow;
import com.dduongdev.services.UserFollowService;

@RestController
@RequestMapping(value = "/api/follows")
@PreAuthorize("isAuthenticated()")
public class UserFollowController {
	private UserFollowService userFollowService;
	
	@Autowired
	public UserFollowController(UserFollowService userFollowService) {
		this.userFollowService = userFollowService;
	}
	
	@PostMapping(value = "/follow")
	public ResponseEntity<?> follow(@RequestBody FollowRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			userFollowService.create(new UserFollow(userId, request.getTargetUserId(), LocalDateTime.now()));
			return ResponseEntity.ok().build();
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
	
	@PostMapping(value = "/unfollow")
	public ResponseEntity<?> unfollow(@RequestBody UnfollowRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			userFollowService.create(new UserFollow(userId, request.getTargetUserId(), LocalDateTime.now()));
			return ResponseEntity.ok().build();
		} catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
}
