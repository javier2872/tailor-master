package com.fitting.sastreria.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitting.sastreria.model.pojo.Tailor;


/**
 * searches in the jpaRepository
 */

public interface TailorRepository extends JpaRepository<Tailor, Long> {
		
	Long deleteById(long id);
	
	Tailor getReferenceById(Long id);
		
	List<Tailor> findByName(String name);
	
	List<Tailor> findBySpecialtiesName(String name);
	
	List<Tailor> findAllByAvailability(String availability);
}
