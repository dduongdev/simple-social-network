package com.dduongdev.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
	@Value("${jwt.secret}")
	private String secretKey;

	private long expirationTime = 24 * 60 * 60 * 1000L;

	public String getSecretKey() {
		return secretKey;
	}

	public long getExpirationTime() {
		return expirationTime;
	}
}
