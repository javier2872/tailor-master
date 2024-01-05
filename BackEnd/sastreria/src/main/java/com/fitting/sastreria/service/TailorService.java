package com.fitting.sastreria.service;

import java.util.List;

import com.fitting.sastreria.model.pojo.Tailor;
import com.fitting.sastreria.model.request.CreateTailorRequest;
import com.fitting.sastreria.utils.Specialties;


public interface TailorService {

	/**
	 * save a Tailor
	 * @param request of type CreateTailorRequest
	 * @return a Tailor if request is not null, and CreateTailorRequest attributes are not null .
	 */
	Tailor createTailor(CreateTailorRequest tailorRequest);
	
	/**
	 * delete a Tailor
	 * @param id of the Tailor
	 */
	void deleteATailor(Long tailorId);
	
	/**
	 * Update an all Tailor
	 * @param Tailor of the Tailor to update
	 * @param changes
	 * @return a Tailor updated, if not return null
	 */
	Tailor updateATailor(Tailor tailorToUpdate, CreateTailorRequest featuresUpdated);
	
	/**
	 * Update Tailor the attributes availability or specialties or price
	 * @param string of the Tailor to update
	 * @param changes in availability, specialties, and price
	 * @return a Tailor updated, if not return null
	 */
	Tailor updatePartialTailor(Tailor tailorToUpdate, CreateTailorRequest featuresUpdated);
	
	/**
	 * get a list with all Tailor
	 * @return a list with all entities, if not return null
	 */
	List<Tailor> getAllTailor();
	
	/**
	 * get a Tailor
	 * @param id of the Tailor
	 * @return a Tailor if it found it else return null.
	 */
	Tailor getATailor(String tailorId);	
	
		
	/**
	 * find tailor's name
	 * @param id of the Tailor
	 * @return a String with name.
	 */
	String getNameTailor(String tailorId);
	
	/**
	 * find tailor's description
	 * @param id of the Tailor
	 * @return a String with description.
	 */
	String getDescriptionTailor(String tailorId);
	
	/**
	 * find tailor's Specialties
	 * @param id of the Tailor
	 * @return a List with all Specialties.
	 */
	List<Specialties> getSpecialtiesTailor(String tailorId);
	
	/**
	 * find tailor's Availability
	 * @param id of the Tailor
	 * @return a List with all Availability.
	 */
	List<String> getAvailabilityTailor(String tailorId);	
}
