package com.fitting.sastreria.controller;

import java.util.List;

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
import com.fitting.sastreria.utils.Specialties;

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
			Tailor findTailor = service.getATailor(tailorId);
			if (findTailor!= null) {
					service.deleteATailor(findTailor.getId());
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
		
		@PutMapping("/tailor/{tailorId}")
		public ResponseEntity<Tailor> updateTailor(@PathVariable String tailorId, @RequestBody CreateTailorRequest tailorToUpdate) {
			log.debug("Ejecucion updateTailor");
			Tailor findTailor = service.getATailor(tailorId);

			if (findTailor != null) {
				return ResponseEntity.status(HttpStatus.OK).body(service.updateATailor(findTailor, tailorToUpdate));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
		@PatchMapping("/tailor/{tailorId}")
		public ResponseEntity<Tailor> updateParcialTailor(@PathVariable String tailorId, @RequestBody CreateTailorRequest featuresUpdated) {
			log.debug("Ejecucion updateParcialTailor");
			Tailor findTailor = service.getATailor(tailorId);

			if (findTailor != null) {				
				return ResponseEntity.status(HttpStatus.OK).body(service.updatePartialTailor(findTailor, featuresUpdated));
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
		

		@GetMapping("/tailor/{tailorId}/name")
		public ResponseEntity<String> getTailorsName(@PathVariable String tailorId) {
			log.debug("Ejecucion getTailorsName");
			Tailor tailor = service.getATailor(tailorId);

			if (tailor != null) {
				return ResponseEntity.status(HttpStatus.OK).body(tailor.getName());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		

		@GetMapping("/tailor/{tailorId}/description")
		public ResponseEntity<String> getTailorsDescription(@PathVariable String tailorId) {
			log.debug("Ejecucion getTailorsDescription");
			Tailor tailor = service.getATailor(tailorId);

			if (tailor != null) {
				return ResponseEntity.status(HttpStatus.OK).body(tailor.getDescription());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
		
		@GetMapping("/tailor/{tailorId}/specialties")
		public ResponseEntity<List<Specialties>> getTailorsSpecialties(@PathVariable String tailorId) {
			log.debug("Ejecucion getTailorSpecialties");
			Tailor tailor = service.getATailor(tailorId);

			if (tailor != null) {
				return ResponseEntity.status(HttpStatus.OK).body(tailor.getSpecialties());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}		

		@GetMapping("/tailor/{tailorId}/availability")
		public ResponseEntity<List<String>> getTailorsAvailability(@PathVariable String tailorId) {
			log.debug("Ejecucion getTailorsAvailability");
			Tailor tailor = service.getATailor(tailorId);

			if (tailor != null) {
				return ResponseEntity.status(HttpStatus.OK).body(tailor.getAvailability());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		}
		
}
