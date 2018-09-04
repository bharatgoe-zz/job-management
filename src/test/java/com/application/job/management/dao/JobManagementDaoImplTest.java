package com.application.job.management.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.application.job.management.dao.JobManagementDaoImpl;
import com.application.job.management.dao.JobQuery;
import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.JobStatus;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JobManagementDaoImplTest {

	@InjectMocks
	JobManagementDaoImpl dao;
	
	@Mock
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private Map<String, Object> paramMap;
	private String query;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		paramMap = new HashMap<>();
	}

	@Test
	public void testAddNewJob() throws JobManagementException {
		query = JobQuery.ADD_JOB;
		Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyMap())).thenReturn(0);
		assertTrue(dao.addNewJob(paramMap, query));
	}

	@Test
	public void testGetJobStatus() throws JobManagementException {
		query = JobQuery.GET_STATUS;
		Mockito.when(jdbcTemplate.queryForObject(query, paramMap, JobStatus.class)).thenReturn(JobStatus.WAITING);
		assertTrue(dao.getJobStatus(paramMap, query).equals(JobStatus.WAITING));
	}

	@Test
	public void testGetJobID() throws JobManagementException {
		query = JobQuery.GET_ID;
		Mockito.when(jdbcTemplate.queryForObject(query, paramMap, Integer.class)).thenReturn(1);
		assertTrue(dao.getJobID(paramMap, query) == 1);
	}

}
