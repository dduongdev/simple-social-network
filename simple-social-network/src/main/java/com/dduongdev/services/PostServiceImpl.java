package com.dduongdev.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dduongdev.dtos.EditPostRequest;
import com.dduongdev.dtos.PostResponse;
import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.entities.User;
import com.dduongdev.repositories.PostRepository;
import com.dduongdev.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository,
			UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public List<PostResponse> getLatestApprovedByFollowingUsersPaged(int userId, int pageIndex, int pageSize) {
		List<Post> latestApprovedPostsOfFollowingUsers =  postRepository.findLatestApprovedByFollowingUsersPaged(userId, pageIndex, pageSize);
		List<User> followingUsers = userRepository.findAllFollowings(userId);
		Map<Integer, User> followingUsersMap = followingUsers.stream().collect(Collectors.toMap(User::getId, user -> user));
		return latestApprovedPostsOfFollowingUsers.stream()
									.map(post -> new PostResponse(
												post.getId(),
												post.getTitle(),
												post.getBody(),
												post.getUserId(),
												followingUsersMap.containsKey(post.getUserId()) ? followingUsersMap.get(post.getUserId()).getUsername() : "unknown",
												post.getApprovalStatus(),
												post.getCreatedAt()
											)).toList();
	}

	@Override
	public void create(Post post) {
		postRepository.save(post);
	}

	@Override
	public void edit(int ownerId, EditPostRequest request) {
	    Optional<Post> foundPostOpt = postRepository.findById(request.getPostId());

	    if (foundPostOpt.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
	    }

	    Post existingPost = foundPostOpt.get();

	    if (existingPost.getUserId() != ownerId) {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not the owner of this post");
	    }

	    existingPost.setTitle(request.getPostTitle());
	    existingPost.setBody(request.getPostBody());
	    existingPost.setApprovalStatus(PostApprovalStatus.PENDING);

	    postRepository.update(existingPost);
	}

	@Override
	public void approvalPost(int postId, PostApprovalStatus approvalStatus) {
		Optional<Post> foundPostOpt = postRepository.findById(postId);
		if (foundPostOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with postId " + postId + "is not found.");
		}
		
		Post post = foundPostOpt.get();
		post.setApprovalStatus(approvalStatus);
		
		postRepository.update(post);
	}

	@Override
	public void delete(int ownerId, int postId) {
		Optional<Post> foundPostOpt = postRepository.findById(postId);
		if (foundPostOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with postId " + postId + "is not found.");
		}
		
		Post existingPost = foundPostOpt.get();

	    if (existingPost.getUserId() != ownerId) {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not the owner of this post");
	    }
		
		postRepository.delete(postId);
	}
}
