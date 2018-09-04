package com.application.job.management.dao;

/**
 * Represents the queries used in this application.
 * 
 * @author BharatGoel
 *
 */
public interface JobQuery {
	
	/**
	 * Adding new job
	 */
	public static final String ADD_JOB = "INSERT INTO job_info VALUES (nextval('job_sequence_generator'),:name,:description,:priority,:execute_now,:status)";

	/**
	 * Fetches status for given job ID
	 */
	public static final String GET_STATUS = "SELECT status from job_info where id= :id";
	
	/**
	 * Fetches job ID for given job name
	 */
	public static final String GET_ID = "SELECT id from job_info where name= :name";
	

}
