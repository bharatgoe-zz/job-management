package com.application.job.management.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class JobDataSource {
	
	@Value("${drivername}")
	private String driverName;

	@Value("${url}")
	private String url;

	@Value("${hostname}")
	private String hostname;

	@Value("${password}")
	private String password;
	
	DriverManagerDataSource dataSource;

	@Bean(name = "datasource")
	public DriverManagerDataSource dataSource() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(hostname);
		dataSource.setPassword(password);
		
		return dataSource;
	}

}
