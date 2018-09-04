package com.application.job.management.service;

import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.Job;
import com.application.job.management.model.JobStatus;

public interface JobManagementService {
	
	public int addJob(Job job);
	
	public JobStatus getStatus(int jobID) throws JobManagementException;

}
