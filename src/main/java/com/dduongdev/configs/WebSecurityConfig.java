package com.dduongdev.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dduongdev.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private CustomUserDetailsService customUserDetailsService;
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public WebSecurityConfig(
		CustomUserDetailsService customUserDetailsService,
		JwtRequestFilter jwtRequestFilter
	) {
		this.customUserDetailsService = customUserDetailsService;
		this.jwtRequestFilter = jwtRequestFilter;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
		AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		/**
		 * Reconfig authorizeHttpRequests.
		 */
		httpSecurity
			.csrf(csrf -> csrf.disable())
			.headers(headersConfigurer -> headersConfigurer
					.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
			.authorizeHttpRequests(requestConfigurer -> requestConfigurer
					.requestMatchers("/api/auth/**").permitAll()
					.anyRequest().authenticated()
			)
			.userDetailsService(customUserDetailsService)
			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement(sessionConfigurer -> sessionConfigurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return httpSecurity.build();
	}
}
