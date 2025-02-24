package com.dduongdev.repositories.inmemory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.entities.UserFollow;
import com.dduongdev.repositories.PostRepository;
import com.dduongdev.repositories.UserFollowRepository;

@Repository
public class InMemoryPostRepository implements PostRepository {
	private static List<Post> storedPosts;
	private static UserFollowRepository userFollowRepository;
	
	static {
		storedPosts = new ArrayList<>();
		userFollowRepository = new InMemoryUserFollowRepository();
		
		for (int i = 1; i <= 50; i++) {
            storedPosts.add(new Post(
                i,
                "Post Title " + i,
                "This is the body of post number " + i + ".",
                1,
                PostApprovalStatus.APPROVED,
                LocalDateTime.now().minusDays(i)
            ));
        }
	}
	
	@Override
	public List<Post> findLatestByFollowedUsersPaged(int userId, int pageIndex, int pageSize) {
		List<Integer> followings = userFollowRepository.findByFollowerId(userId)
									.stream().map(UserFollow::getFollowingId).toList();
		return storedPosts.stream()
				.filter(post -> followings.contains(post.getUserId()) && post.getApprovalStatus() == PostApprovalStatus.APPROVED)
				.sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()))
				.skip((long) pageIndex * pageSize)
				.limit(pageSize)
				.toList();
	}

	@Override
	public void save(Post post) {
		storedPosts.add(post);
	}

	@Override
	public void update(Post post) {}

	@Override
	public Optional<Post> findById(int id) {
		return storedPosts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
	}

	@Override
	public void delete(int id) {
		Optional<Post> foundPostOpt = storedPosts.stream()
                .filter(post -> post.getId() == id)
                .findFirst();
		if (foundPostOpt.isPresent()) {
			storedPosts.remove(foundPostOpt.get());			
		}
	}
}
