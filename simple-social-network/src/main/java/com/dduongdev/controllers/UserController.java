package com.dduongdev.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dduongdev.dtos.FollowRequest;
import com.dduongdev.dtos.UnfollowRequest;
import com.dduongdev.dtos.UserResponse;
import com.dduongdev.entities.CustomUserDetails;
import com.dduongdev.services.UserService;

@RestController
@RequestMapping(value = "/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/follow")
	public ResponseEntity<?> follow(@RequestBody FollowRequest request,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			userService.follow(userId, request.getTargetUserId());
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	@PostMapping(value = "/unfollow")
	public ResponseEntity<?> unfollow(@RequestBody UnfollowRequest request,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			userService.unfollow(userId, request.getTargetUserId());
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	@GetMapping(value = "/not-followed")
	public ResponseEntity<?> getNotFollowedUsersPaged(@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			List<UserResponse> notFollowedUsersPaged = userService.getNotFollowedUsersPaged(userId, pageIndex,
					pageSize);
			return ResponseEntity.ok(notFollowedUsersPaged);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	@GetMapping(value = "/following")
	public ResponseEntity<?> getFollowingUsersPaged(@RequestParam("pageIndex") int pageIndex,
			@RequestParam("pageSize") int pageSize, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			List<UserResponse> followingUsersPaged = userService.getFollowingUsersPaged(userId, pageIndex, pageSize);
			return ResponseEntity.ok(followingUsersPaged);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}
}
