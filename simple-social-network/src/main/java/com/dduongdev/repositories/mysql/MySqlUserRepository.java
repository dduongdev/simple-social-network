package com.dduongdev.repositories.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dduongdev.entities.User;
import com.dduongdev.entities.UserFollow;
import com.dduongdev.entities.UserRole;
import com.dduongdev.repositories.UserRepository;
import com.dduongdev.utils.DatabaseConnectionPool;

@Repository
public class MySqlUserRepository implements UserRepository {
	private static final Logger logger = LoggerFactory.getLogger(MySqlUserRepository.class);
	
	@Override
	public Optional<User> findByUsername(String username) {
	    String query = MySqlCommands.USER_FIND_BY_USERNAME;
	    
	    try (Connection conn = DatabaseConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setString(1, username);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                User user = new User(
	                        rs.getInt("id"),
	                        rs.getString("username"),
	                        rs.getString("password"),
	                        UserRole.valueOf(rs.getString("role")),
	                        rs.getTimestamp("created_at").toLocalDateTime()
	                );
	                return Optional.of(user);
	            }
	        }
	    } catch (SQLException e) {
	        logger.error(e.getMessage());
	    }
	    
	    return Optional.empty();
	}


	@Override
	public List<User> findAllFollowings(int followerUserId) {
	    List<User> users = new ArrayList<>();
	    try (Connection conn = DatabaseConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(MySqlCommands.USER_FIND_ALL_FOLLOWINGS)) {

	        stmt.setInt(1, followerUserId);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                User user = new User(
	                        rs.getInt("id"),
	                        rs.getString("username"),
	                        rs.getString("password"),
	                        UserRole.valueOf(rs.getString("role")),
	                        rs.getTimestamp("created_at").toLocalDateTime()
	                );
	                users.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        logger.error(e.getMessage());
	    }
	    return users;
	}


	@Override
	public List<User> getNotFollowedUsersPaged(int userId, int pageIndex, int pageSize) {
	    List<User> users = new ArrayList<>();
	    try (Connection conn = DatabaseConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(MySqlCommands.USER_FIND_NOT_FOLLOWED_PAGED)) {

	        stmt.setInt(1, userId);
	        stmt.setInt(2, userId);
	        stmt.setInt(3, pageSize);
	        stmt.setInt(4, pageIndex * pageSize);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                User user = new User(
	                        rs.getInt("id"),
	                        rs.getString("username"),
	                        rs.getString("password"),
	                        UserRole.valueOf(rs.getString("role")),
	                        rs.getTimestamp("created_at").toLocalDateTime()
	                );
	                users.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        logger.error(e.getMessage());
	    }
	    return users;
	}


	@Override
	public List<User> getFollowingUsersPaged(int userId, int pageIndex, int pageSize) {
	    List<User> users = new ArrayList<>();
	    try (Connection conn = DatabaseConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(MySqlCommands.USER_FIND_FOLLOWINGS_PAGED)) {

	        stmt.setInt(1, userId);
	        stmt.setInt(2, pageSize);
	        stmt.setInt(3, pageIndex * pageSize);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                User user = new User(
	                        rs.getInt("id"),
	                        rs.getString("username"),
	                        rs.getString("password"),
	                        UserRole.valueOf(rs.getString("role")),
	                        rs.getTimestamp("created_at").toLocalDateTime()
	                );
	                users.add(user);
	            }
	        }
	    } catch (SQLException e) {
	        logger.error(e.getMessage());
	    }
	    return users;
	}


	@Override
	public void save(User user) {
		try (Connection conn = DatabaseConnectionPool.getConnection();
				PreparedStatement stmt = conn.prepareStatement(MySqlCommands.USER_SAVE)) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRole().name());
			stmt.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
			
			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
				logger.warn("Saving user failed, no rows afftected.");
			}
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public void follow(UserFollow userFollow) {
	    try (Connection conn = DatabaseConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(MySqlCommands.USERFOLLOW_SAVE)) {

	        stmt.setInt(1, userFollow.getFollowerId());
	        stmt.setInt(2, userFollow.getFollowingId());
	        stmt.setTimestamp(3, Timestamp.valueOf(userFollow.getCreatedAt()));

	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        logger.error(ex.getMessage());
	    }
	}


	@Override
	public void unfollow(int followerUserId, int followingUserId) {
	    try (Connection conn = DatabaseConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(MySqlCommands.USERFOLLOW_DELETE)) {

	        stmt.setInt(1, followerUserId);
	        stmt.setInt(2, followingUserId);

	        stmt.executeUpdate();
	    } catch (SQLException ex) {
	        logger.error(ex.getMessage());
	    }
	}

	
}
