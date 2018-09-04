package com.application.job.management.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.JobStatus;
import com.application.job.management.util.JobManagementConstants;

@Component
public class JobManagementDaoImpl implements JobManagementDao {
	
	@Autowired
	@Qualifier("jobJdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public boolean addNewJob(Map<String, Object> paramMap, String query) throws JobManagementException {
		try {
			jdbcTemplate.update(query, paramMap);
		} catch (DataAccessException e) {
			throw new JobManagementException(
					"Unable to add new job with name - " + paramMap.get(JobManagementConstants.JOB_NAME));
		}
		return true;
	}

	@Override
	public JobStatus getJobStatus(Map<String, Object> paramMap, String query) throws JobManagementException {
		try {
			return jdbcTemplate.queryForObject(query, paramMap, JobStatus.class);
		} catch (DataAccessException e) {
			throw new JobManagementException(
					"Unable to get status for job with id - " + paramMap.get(JobManagementConstants.JOB_ID));
		}
	}

	@Override
	public int getJobID(Map<String, Object> paramMap, String query) throws JobManagementException {
		try {
			return jdbcTemplate.queryForObject(query, paramMap, Integer.class);
		} catch (DataAccessException e) {
			throw new JobManagementException(
					"Unable to get JobID for job with name - " + paramMap.get(JobManagementConstants.JOB_NAME));
		}
	}

}
