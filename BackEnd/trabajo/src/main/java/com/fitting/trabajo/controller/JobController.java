package com.fitting.trabajo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.fitting.trabajo.model.pojo.Job;
import com.fitting.trabajo.model.request.CreateJobRequest;
import com.fitting.trabajo.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JobController {
	
	private final JobService service;
	
	
	@PostMapping("/job")
	public ResponseEntity<Job> postJob(@RequestBody CreateJobRequest jobRequest) {
		log.debug("Ejecucion postJob");
		Job createdJob = service.createJob(jobRequest);

		if (createdJob != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}


	}
	
	@DeleteMapping("/job/{jobId}")
	public ResponseEntity<Job> deleteAJob(@PathVariable String jobId) {
		log.debug("Ejecucion deleteAJob");
		if (service.deleteAJob(jobId)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/job")
	public ResponseEntity<List<Job>> getAllJob() {
		log.debug("Ejecucion getAllTailors");
		List<Job> tailorCurrently = service.getAllJob();

		if (tailorCurrently != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorCurrently);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@GetMapping("/job/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable String jobId) {
		log.debug("Ejecucion getTailor");
		Job job = service.getAJob(jobId);

		if (job != null) {
			return ResponseEntity.status(HttpStatus.OK).body(job);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@GetMapping("/job/tailor/{tailorId}")
	public ResponseEntity<List<Job>> getJobOfTailor(@PathVariable String tailorId) {
		log.debug("Ejecucion getTailor");
		List<Job> jobAll = service.getAllJobATailor(tailorId);

		if (jobAll != null) {
			return ResponseEntity.status(HttpStatus.OK).body(jobAll);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@PatchMapping("/job/{tailorId}")
	public ResponseEntity<Job> updateParcialJob(@PathVariable String jobId, @RequestBody Map<String, Object> jobToUpdate) {
		log.debug("Ejecucion updateParcialTailor");
		Job modifiedJob = service.updatePartialJob(jobId, jobToUpdate);

		if (modifiedJob != null) {
			return ResponseEntity.status(HttpStatus.OK).body(modifiedJob);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}		
	
	

	

}
