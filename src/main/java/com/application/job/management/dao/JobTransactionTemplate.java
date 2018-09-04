package com.application.job.management.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class JobTransactionTemplate {

	@Autowired
	@Qualifier("jobTransactionManager")
	DataSourceTransactionManager dataSourceTransactionManager;

	TransactionTemplate template;

	@Bean(name="jobTemplate")
	public TransactionTemplate transactionTemplate() {
		template = new TransactionTemplate(dataSourceTransactionManager);
		return template;
	}

}