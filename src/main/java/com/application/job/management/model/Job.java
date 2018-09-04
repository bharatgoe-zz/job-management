package com.application.job.management.model;

public class Job {
	
	private int id;
	private String name;
	private String description;
	private JobPriority priority;
	private String executeNow;
	private JobStatus status;
	
	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public JobPriority getPriority() {
		return priority;
	}
	
	public void setPriority(JobPriority priority) {
		this.priority = priority;
	}
	
	public String isExecuteNow() {
		return executeNow;
	}
	
	public void setExecuteNow(String executeNow) {
		this.executeNow = executeNow;
	}

}
