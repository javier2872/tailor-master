package com.fitting.trabajo.service;

import java.util.List;

import com.fitting.trabajo.model.pojo.Job;
import com.fitting.trabajo.model.request.CreateJobRequest;

public interface JobService {

	/**
	 * save a Job and update the availability of a tailor
	 * 
	 * @param request of type CreateJobRequest
	 * @return a Job if request is not null, and CreateJobRequest attributes are not
	 *         null .
	 */
	Job createJob(CreateJobRequest jobRequest);

	/**
	 * delete a Job
	 * 
	 * @param id of the Job
	 * @return true if the Job is deleted, else false
	 */
	void deleteAJob(Long jobId);

	/**
	 * get a list with all Job
	 * 
	 * @return a list with all entities, if not return null
	 */
	List<Job> getAllJob();

	/**
	 * get a Job
	 * 
	 * @param id of the Job
	 * @return a Job if it found it else return null.
	 */
	Job getAJob(String jobId);

	/**
	 * get all Job of a Tailor
	 * 
	 * @param id of the Tailor
	 * @return a list of Job
	 */
	List<Job> getAllJobATailor(String tailorId);

	/**
	 * Update a Job attributes Order or date
	 * 
	 * @param string  of the Job to update
	 * @param changes in order or date
	 * @return a Job updated, if not return null
	 */
	Job updatePartialJob(Job jobToUpdate, CreateJobRequest featuresUpdated);
}
