package com.application.job.management.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.application.job.management.dao.JobManagementDao;
import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.Job;
import com.application.job.management.model.JobPriority;
import com.application.job.management.model.JobStatus;
import com.application.job.management.service.JobManagementServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JobManagementServiceImplTest {

	@InjectMocks
	JobManagementServiceImpl service;
	
	@Mock
	JobManagementDao jobDao;
	
	@Mock
	TransactionTemplate template;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		Mockito.when(template.execute(Mockito.any(TransactionCallback.class))).thenReturn(1);
	}

	@Test
	public void testAddJob() throws JobManagementException {
		Job job = new Job();
		job.setName("Job1");
		job.setDescription("First job");
		job.setExecuteNow("true");
		job.setPriority(JobPriority.URGENT);
		
		Mockito.when(jobDao.getJobID(Mockito.anyMap(), Mockito.anyString())).thenReturn(1);
		Mockito.when(jobDao.addNewJob(Mockito.anyMap(), Mockito.anyString())).thenReturn(true);
		
		int expectedResult = 1;
		
		assertTrue(service.addJob(job) == expectedResult);
		
	}

	@Test
	public void testGetStatus() throws JobManagementException {
		int jobID = 1;
		
		Mockito.when(jobDao.getJobStatus(Mockito.anyMap(), Mockito.anyString())).thenReturn(JobStatus.WAITING);
		
		assertTrue(service.getStatus(jobID).equals(JobStatus.WAITING));
	}

}
