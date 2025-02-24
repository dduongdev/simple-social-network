package com.dduongdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dduongdev.dtos.EditPostRequest;
import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public List<Post> getLatestByFollowedUsersPaged(int userId, int pageIndex, int pageSize) {
		return postRepository.findLatestByFollowedUsersPaged(userId, pageIndex, pageSize);
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
