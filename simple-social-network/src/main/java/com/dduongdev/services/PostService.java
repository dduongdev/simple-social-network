package com.dduongdev.services;

import java.util.List;

import com.dduongdev.dtos.EditPostRequest;
import com.dduongdev.dtos.PostResponse;
import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;

public interface PostService {
    List<PostResponse> getLatestApprovedByFollowingUsersPaged(int userId, int pageIndex, int pageSize);
    void create(Post post);
	void edit(int ownerId, EditPostRequest request);
	void delete(int ownerId, int postId);
    void approvalPost(int postId, PostApprovalStatus approvalStatus);
}
