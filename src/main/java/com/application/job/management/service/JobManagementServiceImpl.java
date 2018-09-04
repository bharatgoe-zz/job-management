package com.application.job.management.service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.application.job.management.dao.JobManagementDao;
import com.application.job.management.dao.JobQuery;
import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.Job;
import com.application.job.management.model.JobStatus;
import com.application.job.management.util.JobManagementConstants;

@Component
public class JobManagementServiceImpl implements JobManagementService {
	
	@Autowired
	JobManagementDao jobManagementDao;
	
	@Autowired
	@Qualifier("jobTemplate")
	TransactionTemplate template;
	
	PriorityQueue<Job> pq = new PriorityQueue<>(10, new JobComparator());

	public synchronized int addJob(Job job) {
		return template.execute(status -> {
			try {
				addNewJob(job);
				return getNewJobID(job);
			} catch (JobManagementException e) {
				status.setRollbackOnly();
				return 0;
			}
		});
	}
	
	private void addNewJob(Job job) throws JobManagementException {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put(JobManagementConstants.JOB_NAME, job.getName());
		paramMap.put(JobManagementConstants.JOB_DESCRIPTION, job.getDescription());
		paramMap.put(JobManagementConstants.JOB_PRIORITY, job.getPriority().toString());
		paramMap.put(JobManagementConstants.EXECUTE_NOW, job.isExecuteNow());
		paramMap.put(JobManagementConstants.JOB_STATUS, getJobStatus(job).toString());
		
		jobManagementDao.addNewJob(paramMap, JobQuery.ADD_JOB);
	}
	
	private int getNewJobID(Job job) throws JobManagementException {
		Map<String, Object> idParamMap = new HashMap<>();
		idParamMap.put(JobManagementConstants.JOB_NAME, job.getName());
		
		return jobManagementDao.getJobID(idParamMap, JobQuery.GET_ID);
	}
	
	private JobStatus getJobStatus(Job job) {
		pq.add(job);
		
		return JobStatus.WAITING;
	}

	public JobStatus getStatus(int jobID) throws JobManagementException {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", jobID);
		
		return jobManagementDao.getJobStatus(paramMap, JobQuery.GET_STATUS);
	}

}
