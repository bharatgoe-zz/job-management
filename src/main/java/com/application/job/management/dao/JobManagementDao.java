package com.application.job.management.dao;

import java.util.Map;

import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.JobStatus;

public interface JobManagementDao {
	
	public boolean addNewJob(Map<String, Object> paramMap, String query) throws JobManagementException;
	
	public JobStatus getJobStatus(Map<String, Object> paramMap, String query) throws JobManagementException;
	
	public int getJobID(Map<String, Object> paramMap, String query) throws JobManagementException;

}
