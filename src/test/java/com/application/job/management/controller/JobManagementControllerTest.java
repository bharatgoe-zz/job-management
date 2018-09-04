package com.application.job.management.controller;

import java.io.IOException;

import org.beanio.BeanIOConfigurationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.application.job.management.Application;
import com.application.job.management.controller.JobManagementController;
import com.application.job.management.model.Job;
import com.application.job.management.model.JobStatus;
import com.application.job.management.service.JobManagementService;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest(value=JobManagementController.class, secure=false)
@ContextConfiguration(classes={Application.class})
public class JobManagementControllerTest extends TestCase {
	
	@InjectMocks
	JobManagementController controller;
	
	@MockBean
	JobManagementService service;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws BeanIOConfigurationException, IOException, Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testAddJob() throws Exception {
		String inputJson = "{\r\n" + 
				"	\"name\": \"Job1\",\r\n" + 
				"	\"description\": \"Print\",\r\n" + 
				"	\"priority\": \"URGENT\",\r\n" + 
				"	\"executeNow\": \"true\"\r\n" + 
				"}";
		int newJobID = 1;
		
		Mockito.when(service.addJob(Mockito.any(Job.class))).thenReturn(newJobID);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/optile/job/schedule")
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().equals("Your new Job - Job1 with ID - 1 has been scheduled"));
		assertTrue(result.getResponse().getStatus() == 200);
	}

	@Test
	public void testGetJobStatus() throws Exception {
		String inputJson = "1";
		String expectedResponse = "\"WAITING\"";
		
		Mockito.when(service.getStatus(Mockito.anyInt())).thenReturn(JobStatus.WAITING);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/optile/job/status")
				.accept(MediaType.APPLICATION_JSON)
				.content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		assertTrue(result.getResponse().getContentAsString().equals(expectedResponse));
		assertTrue(result.getResponse().getStatus() == 200);
		
	}

}
