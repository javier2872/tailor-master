package com.fitting.sastreria.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
	public Tailor updateATailor(Tailor findTailor , CreateTailorRequest tailorToUpdate) {	
		
			Tailor tailors = repository.getReferenceById(findTailor.getId());	
			tailors.setName(tailorToUpdate.getName());
			tailors.setDescription(tailorToUpdate.getDescription());
			tailors.setSpecialties(tailorToUpdate.getSpecialties());
			tailors.setAvailability(tailorToUpdate.getAvailability());

			return repository.save(tailors);
	
	}

	@Override
	public Tailor updatePartialTailor(String tailorId, Map<String, Object> tailorToUpdate) {

		if (getATailor(tailorId)!=null ) {					
			Tailor tailor = repository.getReferenceById(Long.valueOf(tailorId));	
	        for (Entry<String, Object> entry : tailorToUpdate.entrySet()) {
	        	if(entry.getKey().equals("availability")) {
	        		tailor.setAvailability((List<String>) entry.getValue());
	        	}
	        	if(entry.getKey().equals("specialties")) {
	        		List<Map<String, String>> source = (List<Map<String, String>>) entry.getValue();
	        		List<Specialties> result = source.stream()
	        	            .map(map -> new Specialties(map.get("name"), map.get("price")))
	        	            .collect(Collectors.toList());
	        		tailor.setSpecialties(result);       			
	        	}
	        }
			return repository.save(tailor);
		} else {
			return null;
		}
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
