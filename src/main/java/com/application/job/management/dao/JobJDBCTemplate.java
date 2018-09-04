package com.application.job.management.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * JDBC Template for Job Management Application.
 * This service class is used for providing the template bean.
 * 
 * @author BharatGoel
 *
 */
@Component
public class JobJDBCTemplate {
	
	NamedParameterJdbcTemplate jdbcTemplate;

	DataSourceTransactionManager manager;

	@Autowired
	@Qualifier("datasource")
	DriverManagerDataSource dataSource;

	@Bean(name = "jobJdbcTemplate")
	public NamedParameterJdbcTemplate jdbcTemplate() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

		return jdbcTemplate;
	}

	@Bean("jobTransactionManager")
	public DataSourceTransactionManager manager() {
		manager = new DataSourceTransactionManager(dataSource);
		return manager;

	}

}
