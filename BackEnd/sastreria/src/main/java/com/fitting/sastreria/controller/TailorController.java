package com.fitting.sastreria.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.service.TailorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TailorController {

	private final TailorService service;

	@PostMapping("/tailor")
	public ResponseEntity<Tailor> postTailor(@RequestBody CreateTailorRequest tailor) {
		log.debug("Ejecucion postTailor");
		Tailor createdTailor = service.createTailor(tailor);

		if (createdTailor != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTailor);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	@DeleteMapping("/tailor/{tailorId}")
	public ResponseEntity<Tailor> deleteTailor(@PathVariable String tailorId) {
		log.debug("Ejecucion deleteTailor");
		if (service.deleteATailor(tailorId)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/tailor/{tailorId}")
	public ResponseEntity<Tailor> updateTailor(@PathVariable String tailorId,
			@RequestBody CreateTailorRequest tailorToUpdate) {
		log.debug("Ejecucion updateTailor");
		Tailor createdTailor = service.updateATailor(tailorId, tailorToUpdate);

		if (createdTailor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(createdTailor);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PatchMapping("/tailor/{tailorId}")
	public ResponseEntity<Tailor> updateParcialTailor(@PathVariable String tailorId,
			@RequestBody Map<String, Object> tailorToUpdate) {
		log.debug("Ejecucion updateParcialTailor");
		Tailor modifiedTailor = service.updatePartialTailor(tailorId, tailorToUpdate);

		if (modifiedTailor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(modifiedTailor);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor")
	public ResponseEntity<List<Tailor>> getAllTailors() {
		log.debug("Ejecucion getAllTailors");
		List<Tailor> tailorCurrently = service.getAllTailor();

		if (tailorCurrently != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorCurrently);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor/{tailorId}")
	public ResponseEntity<Tailor> getTailor(@PathVariable String tailorId) {
		log.debug("Ejecucion getTailor");
		Tailor tailor = service.getATailor(tailorId);

		if (tailor != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailor);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	@GetMapping("/tailor/typeService/{typeService}")
	public ResponseEntity<List<Tailor>> getListTailorTypeService(@PathVariable String typeService) {
		log.debug("Ejecucion getTailorTypeService");
		List<Tailor> tailorByTypeService = service.getTailorByTypeService(typeService);

		if (tailorByTypeService != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorByTypeService);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor/name/{name}")
	public ResponseEntity<List<Tailor>> getListTailorName(@PathVariable String name) {
		log.debug("Ejecucion getTailorName");
		List<Tailor> tailorByName = service.getTailorByName(name);

		if (tailorByName != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorByName);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor/price/{price}")
	public ResponseEntity<List<Tailor>> getListTailorPrice(@PathVariable String price) {
		log.debug("Ejecucion getTailorPrice");
		List<Tailor> tailorByPrice = service.getTailorByPrice(price);

		if (tailorByPrice != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorByPrice);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor/opinion/{opinion}")
	public ResponseEntity<List<Tailor>> getListTailorOpinion(@PathVariable String opinion) {
		log.debug("Ejecucion getTailorOpinion");
		List<Tailor> tailorByOpinion = service.getTailorByOpinion(opinion);

		if (tailorByOpinion != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorByOpinion);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor/specialties/{specialties}")
	public ResponseEntity<List<Tailor>> getListTailorSpecialties(@PathVariable String specialties) {
		log.debug("Ejecucion getTailorSpecialties");
		List<Tailor> tailorBySpecialies = service.getTailorBySpecialties(specialties);

		if (tailorBySpecialies != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorBySpecialies);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/tailor/availability/{availability}")
	public ResponseEntity<List<Tailor>> getListTailorAvailability(@PathVariable String availability) {
		log.debug("Ejecucion getTailorAvailability");
		List<Tailor> tailorByAvailability = service.getTailorByAvailability(availability);

		if (tailorByAvailability != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tailorByAvailability);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
