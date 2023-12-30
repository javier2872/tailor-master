package com.fitting.trabajo.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitting.trabajo.data.JobRepository;
import com.fitting.trabajo.facade.TailorFacade;
import com.fitting.trabajo.model.pojo.Job;
import com.fitting.trabajo.model.pojo.Tailor;
import com.fitting.trabajo.model.request.CreateJobRequest;
import com.fitting.trabajo.utils.Order;
import com.fitting.trabajo.utils.Specialties;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private TailorFacade tailorFacade;

	@Autowired
	private JobRepository repository;
	
	@Override
	public Job createJob(CreateJobRequest jobRequest) {		
		Tailor tailor = tailorFacade.getTailor(jobRequest.getIdTailor());
		List <Order> order = jobRequest.getOrder();
		List <Specialties> specialties = tailor.getSpecialties();
		double totalPrice = 0.0;
		for (Order one : order)  {
			for (Specialties two : specialties)    {
				if (one.getName().equals(two.getName())) {
					totalPrice = (totalPrice + Double.valueOf(two.getPrice().replace(",", ".")))*Integer.parseInt(one.getNumber());
				}
	    	}
	    }		
		String dateToReserve = jobRequest.getDate();
		if (tailor.getAvailability().contains(dateToReserve)&& totalPrice!=0.0) {
			Job job = Job.builder().idTailor(jobRequest.getIdTailor()).
											 date(dateToReserve).
											 totalPrice(String.format("%.2f", totalPrice+Double.valueOf(tailor.getPrice().replace(",", ".")))).
											 order(order).
											 build();
			tailor.getAvailability().remove(dateToReserve);
			tailorFacade.updateATailor(jobRequest.getIdTailor(), Collections.singletonMap("availability", tailor.getAvailability()));
			return repository.save(job);
		}else {
			return null;
		}
	}

	@Override
	public Boolean deleteAJob(String jobId) {
		try {
			repository.deleteById(Long.valueOf(jobId));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<Job> getAllJob() {
		List<Job> allJob = repository.findAll();
		return allJob.isEmpty() ? null : allJob;
	}

	@Override
	public Job getAJob(String jobId) {
		return repository.findById(Long.valueOf(jobId)).orElse(null);
	}

	@Override
	public List<Job> getAllJobATailor(String tailorId) {
		List<Job> allJob = repository.findByIdTailor(tailorId);
		return allJob.isEmpty() ? null : allJob;
	}

	@Override
	public Job updatePartialJob(String jobId, Map<String, Object> jobToUpdate) {
		
		if (getAJob(jobId)!=null ) {					
			Job job = repository.getReferenceById(Long.valueOf(jobId));	
	        for (Entry<String, Object> entry : jobToUpdate.entrySet()) {
	        	if(entry.getKey().equals("date")) {
	        		job.setDate((String) entry.getValue());
	        	}
	        	if(entry.getKey().equals("order")) {
	        		List<Map<String, String>> source = (List<Map<String, String>>) entry.getValue();
	        		List<Order> result = source.stream()
	        	            .map(map -> new Order(map.get("name"), map.get("number")))
	        	            .collect(Collectors.toList());
	        		job.setOrder(result);       			
	        	}
	        }
			return repository.save(job);
		} else {
			return null;
		}
	}



}
