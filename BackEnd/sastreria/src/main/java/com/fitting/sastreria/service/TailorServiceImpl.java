package com.fitting.sastreria.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fitting.sastreria.data.TailorRepository;
import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.utils.Availability;
import com.fitting.sastreria.utils.Specialties;

@Service
public class TailorServiceImpl implements TailorService {

	@Autowired
	private TailorRepository repository;

	@Override
	public Tailor createTailor(CreateTailorRequest request) {

		if (request != null && StringUtils.hasLength(request.getTypeService().trim())
				&& StringUtils.hasLength(request.getName().trim()) && StringUtils.hasLength(request.getPrice().trim())
				&& StringUtils.hasLength(request.getOpinion().trim()) && request.getSpecialties() != null
				&& request.getAvailability() != null) {

			Tailor tailor = Tailor.builder().typeService(request.getTypeService()).name(request.getName())
					.price(request.getPrice()).opinion(request.getOpinion()).specialties(request.getSpecialties())
					.availability(request.getAvailability()).build();

			return repository.save(tailor);
		} else {
			return null;
		}
	}

	@Override
	public Boolean deleteATailor(String tailorId) {
		try {
			repository.deleteById(Long.valueOf(tailorId));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Tailor updateATailor(String tailorId, CreateTailorRequest tailorToUpdate) {

		if (StringUtils.hasLength(tailorToUpdate.getTypeService().trim())
				&& StringUtils.hasLength(tailorToUpdate.getName().trim())
				&& StringUtils.hasLength(tailorToUpdate.getPrice().trim())
				&& StringUtils.hasLength(tailorToUpdate.getOpinion().trim()) && tailorToUpdate.getSpecialties() != null
				&& tailorToUpdate.getAvailability() != null && getATailor(tailorId) != null) {
			Tailor tailor = repository.findById(Long.valueOf(tailorId)).get();
			tailor.setTypeService(tailorToUpdate.getTypeService());
			tailor.setName(tailorToUpdate.getName());
			tailor.setPrice(tailorToUpdate.getPrice());
			tailor.setOpinion(tailorToUpdate.getOpinion());
			tailor.setAvailability(tailorToUpdate.getAvailability());
			tailor.setSpecialties(tailorToUpdate.getSpecialties());
			return repository.save(tailor);
		} else {
			return null;
		}
	}

	@Override
	public Tailor updatePartialTailor(String tailorId, Map<String, Object> tailorToUpdate) {

		if (getATailor(tailorId) != null) {
			Tailor tailor = repository.findById(Long.valueOf(tailorId)).get();
			for (Entry<String, Object> entry : tailorToUpdate.entrySet()) {
				if (entry.getKey().equals("price")) {
					tailor.setPrice((String) entry.getValue());
				}
				if (entry.getKey().equals("availability")) {
					tailor.setAvailability((Set<Availability>) entry.getValue());
				}
				if (entry.getKey().equals("specialties")) {
					List<Map<String, String>> source = (List<Map<String, String>>) entry.getValue();
					Set<Specialties> result = source.stream()
							.map(map -> new Specialties(map.get("name"), map.get("price"))).collect(Collectors.toSet());
					tailor.setSpecialties(result);
				}
			}
			return repository.save(tailor);
		} else {
			return null;
		}
	}

	@Override
	public List<Tailor> getAllTailor() {
		List<Tailor> allTailor = repository.findAll();
		return allTailor.isEmpty() ? null : allTailor;
	}

	@Override
	public Tailor getATailor(String tailorId) {
		return repository.findById(Long.valueOf(tailorId)).orElse(null);
	}

	@Override
	public List<Tailor> getTailorByTypeService(String typeService) {
		List<Tailor> allTailor = repository.findByTypeService(typeService);
		return allTailor.isEmpty() ? null : allTailor;
	}

	@Override
	public List<Tailor> getTailorByName(String name) {
		List<Tailor> allTailor = repository.findByName(name);
		return allTailor.isEmpty() ? null : allTailor;
	}

	@Override
	public List<Tailor> getTailorByPrice(String price) {
		List<Tailor> allTailor = repository.findByPrice(price);
		return allTailor.isEmpty() ? null : allTailor;
	}

	@Override
	public List<Tailor> getTailorByOpinion(String opinion) {
		List<Tailor> allTailor = repository.findByOpinion(opinion);
		return allTailor.isEmpty() ? null : allTailor;
	}

	@Override
	public List<Tailor> getTailorBySpecialties(String nameSpecialties) {
		List<Tailor> allTailor = repository.findBySpecialtiesName(nameSpecialties);
		return allTailor.isEmpty() ? null : allTailor;
	}

	@Override
	public List<Tailor> getTailorByAvailability(String dateAvailability) {
		List<Tailor> allTailor = repository.findAllByAvailabilityAvailability(dateAvailability);
		return allTailor.isEmpty() ? null : allTailor;
	}

}
