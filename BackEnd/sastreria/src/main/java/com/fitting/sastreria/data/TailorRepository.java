package com.fitting.sastreria.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitting.sastreria.model.pojo.Tailor;

/**
 * searches in the jpaRepository
 */

public interface TailorRepository extends JpaRepository<Tailor, Long> {

	Long deleteById(long id);

	List<Tailor> findByTypeService(String typeService);

	List<Tailor> findByName(String name);

	List<Tailor> findByPrice(String price);

	List<Tailor> findByOpinion(String opinion);

	List<Tailor> findBySpecialtiesName(String name);

	List<Tailor> findAllByAvailabilityAvailability(String availability);
}
