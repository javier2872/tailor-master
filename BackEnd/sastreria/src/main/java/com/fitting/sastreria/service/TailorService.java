package com.fitting.sastreria.service;

import java.util.List;
import java.util.Map;

import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;

public interface TailorService {

	/**
	 * save a Tailor
	 * 
	 * @param request of type CreateTailorRequest
	 * @return a Tailor if request is not null, and CreateTailorRequest attributes
	 *         are not null .
	 */
	Tailor createTailor(CreateTailorRequest tailorRequest);

	/**
	 * delete a Tailor
	 * 
	 * @param id of the Tailor
	 * @return true if the Tailor is deleted, else false
	 */
	Boolean deleteATailor(String tailorId);

	/**
	 * Update an all Tailor
	 * 
	 * @param string  of the Tailor to update
	 * @param changes
	 * @return a Tailor updated, if not return null
	 */
	Tailor updateATailor(String tailorId, CreateTailorRequest tailorToUpdate);

	/**
	 * Update Tailor the attributes availability or specialties or price
	 * 
	 * @param string  of the Tailor to update
	 * @param changes in availability, specialties, and price
	 * @return a Tailor updated, if not return null
	 */
	Tailor updatePartialTailor(String tailorId, Map<String, Object> tailorToUpdate);

	/**
	 * get a list with all Tailor
	 * 
	 * @return a list with all entities, if not return null
	 */
	List<Tailor> getAllTailor();

	/**
	 * get a Tailor
	 * 
	 * @param id of the Tailor
	 * @return a Tailor if it found it else return null.
	 */
	Tailor getATailor(String tailorId);

	/**
	 * get an list of Tailor with the same typeService
	 * 
	 * @param string to find
	 * @return a list with all Tailor, if not return null
	 */
	List<Tailor> getTailorByTypeService(String typeService);

	/**
	 * get an list of Tailor with the same name
	 * 
	 * @param string to find
	 * @return a list with all Tailor, if not return null
	 */
	List<Tailor> getTailorByName(String name);

	/**
	 * get an list of Tailor with the same price
	 * 
	 * @param string to find
	 * @return a list with all Tailor, if not return null
	 */
	List<Tailor> getTailorByPrice(String price);

	/**
	 * get an list of Tailor with the same opinion
	 * 
	 * @param string to find
	 * @return a list with all Tailor, if not return null
	 */
	List<Tailor> getTailorByOpinion(String opinion);

	/**
	 * get an list of Tailor with the same name of specialties
	 * 
	 * @param string to find
	 * @return a list with all Tailor, if not return null
	 */
	List<Tailor> getTailorBySpecialties(String nameSpecialties);

	/**
	 * get an list of Tailor with the same availability
	 * 
	 * @param string to find
	 * @return a list with all Tailor, if not return null
	 */
	List<Tailor> getTailorByAvailability(String dateAvailability);
}
