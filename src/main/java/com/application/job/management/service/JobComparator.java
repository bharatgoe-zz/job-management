package com.application.job.management.service;

import java.util.Comparator;

import com.application.job.management.model.Job;
import com.application.job.management.model.JobPriority;

public class JobComparator implements Comparator<Job> {

	public int compare(Job o1, Job o2) {
		if (o1.getPriority().equals(JobPriority.URGENT)) {
			return 1;
		} else {
			return -1;
		}
	
	}

}
