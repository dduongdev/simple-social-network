package com.dduongdev.repositories.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dduongdev.entities.Post;
import com.dduongdev.entities.PostApprovalStatus;
import com.dduongdev.repositories.PostRepository;
import com.dduongdev.utils.DbUtils;

@Repository
public class MySqlPostRepository implements PostRepository {
	private static final Logger logger = LoggerFactory.getLogger(MySqlPostRepository.class);
	
	@Override
	public List<Post> findLatestApprovedByFollowingUsersPaged(int userId, int pageIndex, int pageSize) {
		List<Post> posts = new ArrayList<>();
		try (Connection conn = DbUtils.getInstance().getConnection();
				PreparedStatement stmt = conn.prepareStatement(MySqlCommands.POST_FIND_LASTEST_APPROVED_BY_FOLLOWING_USERS_PAGED)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, pageSize);
			stmt.setInt(3, pageIndex * pageSize);
			
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Post post = new Post(
							rs.getInt("id"),
							rs.getString("title"),
							rs.getString("body"),
							rs.getInt("user_id"),
							PostApprovalStatus.valueOf(rs.getString("status")),
							rs.getTimestamp("created_at").toLocalDateTime());
					posts.add(post);
				}
			}
			
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		}
		
		return posts;
	}

	@Override
	public Optional<Post> findById(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void save(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
