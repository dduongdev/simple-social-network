package com.dduongdev.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionPool {
    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig("/dbConfig.properties");
            dataSource = new HikariDataSource(config);
        } catch (Exception ex) {
            throw new RuntimeException("Error while initial HikariCP: " + ex.getMessage(), ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection(); 
    }
}
