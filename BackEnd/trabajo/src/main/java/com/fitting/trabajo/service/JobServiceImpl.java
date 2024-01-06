package com.fitting.trabajo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.utils.Specialties;
import com.fitting.trabajo.data.JobRepository;
import com.fitting.trabajo.facade.TailorFacade;
import com.fitting.trabajo.model.pojo.Job;
import com.fitting.trabajo.model.request.CreateJobRequest;
import com.fitting.trabajo.utils.Item;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private TailorFacade tailorFacade;

	@Autowired
	private JobRepository repository;

	@Override
	public Job createJob(CreateJobRequest jobRequest) {
		Tailor tailor = tailorFacade.getTailor(jobRequest.getIdTailor());
		List<Item> item = jobRequest.getItem();
		List<Specialties> specialties = tailor.getSpecialties();
		double totalPrice = 0.0;
		for (Item one : item) {
			for (Specialties two : specialties) {
				if (one.getName().equals(two.getName())) {
					totalPrice = (totalPrice + Double.valueOf(two.getPrice().replace(",", ".")))
							* Integer.parseInt(one.getNumber());
				}
			}
		}
		String dateToReserve = jobRequest.getDate();
		// tailor.getAvailability() -> es una list<availability>
		List<String> tailorAvailability = tailor.getAvailability();
		for (String dateA : tailorAvailability) {
			if (dateA.contains(dateToReserve) && totalPrice != 0.0) {
				Job job = Job.builder().tailorNumber(jobRequest.getIdTailor()).date(dateToReserve)
						.totalPrice(
								String.format("%.2f", totalPrice ))
						.item(item)
						.client(jobRequest.getClient())
						.build();
				tailorAvailability.remove(dateA);
				CreateTailorRequest tailorRequest = new CreateTailorRequest();
				tailorRequest.setAvailability(tailorAvailability);
				tailorFacade.updateATailor(jobRequest.getIdTailor(), tailorRequest);
				return repository.save(job);
			}
		}
		return null;

	}

	@Override
	public void deleteAJob(Long jobId) {
		
			repository.deleteById(jobId);

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
		List<Job> allJob = repository.findByTailorNumber(tailorId);
		return allJob.isEmpty() ? null : allJob;
	}

	@Override
	public Job updatePartialJob(Job jobToUpdate, CreateJobRequest featuresUpdated) {

		if(featuresUpdated.getDate()!=null) {
			jobToUpdate.setDate(featuresUpdated.getDate());
		}			
		if(featuresUpdated.getItem()!=null) {
			jobToUpdate.setItem(featuresUpdated.getItem());
		}
		
		return repository.save(jobToUpdate);

	}

}
