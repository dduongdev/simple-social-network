package com.dduongdev.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.dduongdev")
@PropertySource(value = "classpath:application.properties")
public class ApplicationConfig {}
