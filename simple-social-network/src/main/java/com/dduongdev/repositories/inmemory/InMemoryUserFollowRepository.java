package com.dduongdev.repositories.inmemory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dduongdev.entities.UserFollow;
import com.dduongdev.repositories.UserFollowRepository;

@Repository
public class InMemoryUserFollowRepository implements UserFollowRepository {
	private static List<UserFollow> storedUserFollows;
	
	static {
		storedUserFollows = new ArrayList<>();
		storedUserFollows.add(new UserFollow(2, 1, LocalDateTime.now()));
	}
	
	@Override
	public List<UserFollow> findByFollowerId(int followerId) {
		List<UserFollow> result = new ArrayList<>();
		for (UserFollow follow : storedUserFollows) {
			if (follow.getFollowerId() == followerId) {
				result.add(follow);
			}
		}
		
		return result;
	}

	@Override
	public void save(UserFollow userFollow) {
		storedUserFollows.add(userFollow);
	}

	@Override
	public void delete(int id) {
		Optional<UserFollow> foundUserFollowOpt = storedUserFollows.stream()
															.filter(userFollow -> userFollow.getId() == id)
															.findFirst();
		if (foundUserFollowOpt.isPresent()) {
			storedUserFollows.remove(foundUserFollowOpt.get());
		}
	}
	
}
