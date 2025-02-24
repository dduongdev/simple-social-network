package com.dduongdev.controllers;

import java.time.LocalDateTime;
import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import com.dduongdev.dtos.CreatePostRequest;
import com.dduongdev.dtos.DeletePostRequest;
import com.dduongdev.dtos.EditPostRequest;
import com.dduongdev.dtos.PostApprovalRequest;
import com.dduongdev.dtos.PostResponse;
import com.dduongdev.entities.CustomUserDetails;
import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.services.PostService;

@RestController
@RequestMapping(value = "/api/posts")
@PreAuthorize("isAuthenticated()")
public class PostController {
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping(value = "/followed")
	public ResponseEntity<?> getLatestByFollowedUsersPaged(
			@RequestParam("pageIndex") int pageIndex, 
		 	@RequestParam("pageSize") int pageSize,
		 	@AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			List<PostResponse> responsePosts = postService.getLatestApprovedByFollowingUsersPaged(userId, pageIndex, pageSize);
			
			return ResponseEntity.ok(responsePosts);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ex.getMessage());
		}
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@RequestBody CreatePostRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			Post newPost = new Post(request.getTitle(), request.getBody(), userId, PostApprovalStatus.PENDING, LocalDateTime.now());
			postService.create(newPost);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ex.getMessage());
		}
	}
	
	@PostMapping(value = "/edit")
	public ResponseEntity<?> edit(@RequestBody EditPostRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			postService.edit(userId, request);
			
			return ResponseEntity.ok().build();
		} catch (ResponseStatusException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getBody().getDetail());
		}
	}
	
	@PostMapping(value = "/approval")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> approval(@RequestBody PostApprovalRequest request) {
		try {
			postService.approvalPost(request.getPostId(), request.getApprovalStatus());
			return ResponseEntity.ok().build();
		} catch (ResponseStatusException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getBody().getDetail());
		}
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> delete(@RequestBody DeletePostRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
		try {
			int userId = ((CustomUserDetails) userDetails).getUserId();
			
			postService.delete(userId, request.getPostId());
			
			return ResponseEntity.ok().build();
		} catch (ResponseStatusException ex) {
			return ResponseEntity.status(ex.getStatusCode()).body(ex.getBody().getDetail());
		}
	}
}
