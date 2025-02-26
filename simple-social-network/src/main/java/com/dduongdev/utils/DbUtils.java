package com.dduongdev.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtils {
	private static DbUtils instance = null;
	private Connection connection;
	
	private DbUtils() {
		Properties properties = new Properties();
		
		try {
			properties.load(DbUtils.class.getResourceAsStream("/dbConfig.properties"));
			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");
			
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url, username, password);
		} catch (IOException | ClassNotFoundException | SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public static DbUtils getInstance() throws SQLException {
		if (instance == null || instance.getConnection().isClosed()) {
			instance = new DbUtils();
		}

		return instance;
	}
}
