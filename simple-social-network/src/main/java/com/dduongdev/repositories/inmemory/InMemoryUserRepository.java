package com.dduongdev.repositories.inmemory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.dduongdev.entities.User;
import com.dduongdev.entities.UserFollow;
import com.dduongdev.repositories.UserRepository;

//@Repository
@SuppressWarnings("unused")
public class InMemoryUserRepository implements UserRepository {
    @Override
    public Optional<User> findByUsername(String username) {
        return InMemoryDatabase.users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public void save(User user) {
    	user.setId(InMemoryDatabase.currentUserIndex);
    	InMemoryDatabase.currentUserIndex++;
    	InMemoryDatabase.users.add(user);
    }

    @Override
    public List<User> findAllFollowings(int followerUserId) {
        List<Integer> followingIds = InMemoryDatabase.userFollows.stream()
                .filter(follow -> follow.getFollowerId() == followerUserId)
                .map(UserFollow::getFollowingId)
                .collect(Collectors.toList());

        return InMemoryDatabase.users.stream()
                .filter(user -> followingIds.contains(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getNotFollowedUsersPaged(int userId, int pageIndex, int pageSize) {
        Set<Integer> followingIds = InMemoryDatabase.userFollows.stream()
                .filter(follow -> follow.getFollowerId() == userId)
                .map(UserFollow::getFollowingId)
                .collect(Collectors.toSet());

        List<User> notFollowedUsers = InMemoryDatabase.users.stream()
                .filter(user -> user.getId() != userId && !followingIds.contains(user.getId()))
                .collect(Collectors.toList());

        return getPagedList(notFollowedUsers, pageIndex, pageSize);
    }

    @Override
    public List<User> getFollowingUsersPaged(int userId, int pageIndex, int pageSize) {
        List<User> followingUsers = findAllFollowings(userId);
        return getPagedList(followingUsers, pageIndex, pageSize);
    }

    private List<User> getPagedList(List<User> list, int pageIndex, int pageSize) {
        int fromIndex = pageIndex * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, list.size());

        if (fromIndex >= list.size()) {
            return Collections.emptyList();
        }
        return list.subList(fromIndex, toIndex);
    }

	@Override
	public void follow(UserFollow userFollow) {
		userFollow.setId(InMemoryDatabase.currentUserFollowIndex);
		InMemoryDatabase.currentUserFollowIndex++;
		InMemoryDatabase.userFollows.add(userFollow);
	}

	@Override
	public void unfollow(int followerUserId, int followingUserId) {
		InMemoryDatabase.userFollows.removeIf(f -> f.getFollowerId() == followerUserId && f.getFollowingId() == followingUserId);
	}
}
