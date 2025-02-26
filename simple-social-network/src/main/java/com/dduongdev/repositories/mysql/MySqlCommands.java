package com.dduongdev.repositories.mysql;

public class MySqlCommands {
	public static final String USER_FIND_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
	public static final String USER_SAVE = "INSERT INTO users(username, password, role, created_at) VALUES (?, ?, ?, ?)";
	public static final String POST_FIND_LASTEST_APPROVED_BY_FOLLOWING_USERS_PAGED = "SELECT * FROM posts "
			+ "WHERE user_id IN (SELECT following_user_id FROM follows WHERE follower_user_id = ?) "
			+ "AND status = 'APPROVED' "
			+ "ORDER BY created_at DESC " 
			+ "LIMIT ? OFFSET ?";
	public static final String USER_FIND_FOLLOWINGS_PAGED = "SELECT u.* "
			+ "FROM users as u "
			+ "JOIN follows as f "
			+ "ON u.id = f.following_user_id "
			+ "WHERE f.follower_user_id = ? "
			+ "LIMIT ? OFFSET ?";
	public static final String USER_FIND_NOT_FOLLOWED_PAGED = "SELECT * "
			+ "FROM users "
			+ "WHERE id <> ? "
			+ "AND id NOT IN (SELECT following_user_id FROM follows WHERE follower_user_id = ?) "
			+ "LIMIT ? OFFSET ?";
	public static final String USER_FIND_ALL_FOLLOWINGS = "SELECT users.* \r\n"
			+ "FROM users \r\n"
			+ "JOIN follows ON users.id = follows.following_user_id \r\n"
			+ "WHERE follows.follower_user_id = ?";
	public static final String USERFOLLOW_SAVE = "INSERT INTO follows VALUES (?, ?, ?)";
	public static final String USERFOLLOW_DELETE = "DELETE FROM follows WHERE follower_user_id = ? AND following_user_id = ?";
}
