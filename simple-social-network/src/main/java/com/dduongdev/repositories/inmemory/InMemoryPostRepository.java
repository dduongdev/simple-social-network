package com.dduongdev.repositories.inmemory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.repositories.PostRepository;

@Repository
public class InMemoryPostRepository implements PostRepository {
    
    @Override
    public List<Post> findLatestApprovedByFollowingUsersPaged(int userId, int pageIndex, int pageSize) {
        List<Integer> followingUserIds = InMemoryDatabase.userFollows.stream()
            .filter(follow -> follow.getFollowerId() == userId)
            .map(follow -> follow.getFollowingId())
            .collect(Collectors.toList());

        List<Post> approvedPosts = InMemoryDatabase.posts.stream()
            .filter(post -> followingUserIds.contains(post.getUserId()) && post.getApprovalStatus() == PostApprovalStatus.APPROVED)
            .sorted((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt())) 
            .collect(Collectors.toList());

        int fromIndex = pageIndex * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, approvedPosts.size());

        return fromIndex < approvedPosts.size() ? approvedPosts.subList(fromIndex, toIndex) : List.of();
    }

    @Override
    public void save(Post post) {
    	post.setId(InMemoryDatabase.currentPostIndex);
    	InMemoryDatabase.currentPostIndex++;
        InMemoryDatabase.posts.add(post);
    }

    @Override
    public void update(Post post) {
        findById(post.getId()).ifPresent(existingPost -> {
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            existingPost.setApprovalStatus(post.getApprovalStatus());
        });
    }

    @Override
    public Optional<Post> findById(int id) {
        return InMemoryDatabase.posts.stream()
            .filter(post -> post.getId() == id)
            .findFirst();
    }

    @Override
    public void delete(int id) {
        InMemoryDatabase.posts.removeIf(post -> post.getId() == id);
    }
}
