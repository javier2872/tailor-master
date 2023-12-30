package com.fitting.trabajo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitting.trabajo.model.pojo.Job;

/**
 * searches in the jpaRepository
 */
public interface JobRepository  extends JpaRepository<Job, Long>{
	
	Long deleteById(long id);

	List<Job> findByIdTailor(String idTailor);
	
}
