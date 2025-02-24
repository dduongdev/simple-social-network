package com.dduongdev.repositories;

import java.util.List;
import java.util.Optional;

import com.dduongdev.entities.Post;

public interface PostRepository {
	List<Post> findLatestApprovedByFollowingUsersPaged(int userId, int pageIndex, int pageSize);
	Optional<Post> findById(int id);
	void save(Post post);
	void update(Post post);
	void delete(int id);
}
