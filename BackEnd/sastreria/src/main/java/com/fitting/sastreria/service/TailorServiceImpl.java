package com.fitting.sastreria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fitting.sastreria.data.TailorRepository;
import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.utils.Specialties;



@Service
public class TailorServiceImpl implements TailorService{

	@Autowired
	private TailorRepository repository;
	
	@Override
	public Tailor createTailor(CreateTailorRequest request) {

		if (request != null 
				&& StringUtils.hasLength(request.getName().trim())
				&& StringUtils.hasLength(request.getDescription().trim()) 
				&& request.getSpecialties() != null
				&& request.getAvailability() != null
				) {

			Tailor tailor = Tailor.builder().
					name(request.getName()).
					description(request.getDescription()).
					specialties(request.getSpecialties()).
					availability(request.getAvailability()).
					build();

			return repository.save(tailor);
		} else {
			return null;
		}
	}

	@Override
	public void deleteATailor(Long tailorId) {
	
			repository.deleteById(tailorId);
	}

	@Override
	public Tailor updateATailor(Tailor tailorToUpdate , CreateTailorRequest featuresUpdated) {	
		
			Tailor tailors = repository.getReferenceById(tailorToUpdate.getId());	
			tailors.setName(featuresUpdated.getName());
			tailors.setDescription(featuresUpdated.getDescription());
			tailors.setSpecialties(featuresUpdated.getSpecialties());
			tailors.setAvailability(featuresUpdated.getAvailability());

			return repository.save(tailors);
	
	}

	@Override
	public Tailor updatePartialTailor(Tailor tailorToUpdate, CreateTailorRequest featuresUpdated) {
		if(featuresUpdated.getSpecialties()!=null) {
			tailorToUpdate.setSpecialties(featuresUpdated.getSpecialties());
		}			
		if(featuresUpdated.getAvailability()!=null) {
			tailorToUpdate.setAvailability(featuresUpdated.getAvailability());
		}
		return repository.save(tailorToUpdate);

	}

	@Override
	public Tailor getATailor(String tailorId) {
		return repository.findById(Long.valueOf(tailorId)).orElse(null);
	}
	
	@Override
	public List<Tailor> getAllTailor() {
		List<Tailor> allTailor = repository.findAll();
		return allTailor.isEmpty() ? null : allTailor;
	}
	
	@Override
	public String getNameTailor(String tailorId) {
		return repository.findById(Long.valueOf(tailorId)).orElse(null).getName();
	}

	@Override
	public String getDescriptionTailor(String tailorId) {
		return repository.findById(Long.valueOf(tailorId)).orElse(null).getDescription();
	}

	@Override
	public List<Specialties> getSpecialtiesTailor(String tailorId) {
		return repository.findById(Long.valueOf(tailorId)).orElse(null).getSpecialties();
	}

	@Override
	public List<String> getAvailabilityTailor(String tailorId) {
		return repository.findById(Long.valueOf(tailorId)).orElse(null).getAvailability();
	}
	


}
