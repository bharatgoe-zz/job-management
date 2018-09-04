package com.application.job.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.application.job.management.exception.JobManagementException;
import com.application.job.management.model.Job;
import com.application.job.management.service.JobManagementService;

@RestController
public class JobManagementController {

	@Autowired
	JobManagementService jobManagementService;
	
	@RequestMapping(path = "/job/schedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> addJob(@RequestBody Job job) {
		Object result = null;
		ResponseEntity<Object> response;
		HttpStatus status = HttpStatus.OK;
		
		int jobID = jobManagementService.addJob(job);
		
		if (jobID != 0) {
			result = "Your new Job - " + job.getName() + " with ID - " + jobID + " has been scheduled";
		} else {
			result = "Could not add new job!";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response = new ResponseEntity<>(result, status);
		return response;
	}
	
	@RequestMapping(path = "/job/status", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> getJobStatus(@RequestBody String jobID) {
		Object result = null;
		ResponseEntity<Object> response;
		HttpStatus status = HttpStatus.OK;
		
		try {
			result = jobManagementService.getStatus(Integer.parseInt(jobID));
		} catch (NumberFormatException | JobManagementException e) {
			result = "Error in fetching status";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		response = new ResponseEntity<>(result, status);
		return response;
	}
}