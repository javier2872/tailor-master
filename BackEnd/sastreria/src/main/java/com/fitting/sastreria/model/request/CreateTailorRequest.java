package com.fitting.sastreria.model.request;

import java.util.Set;

import com.fitting.sastreria.utils.Availability;
import com.fitting.sastreria.utils.Specialties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Defining a request when tailor is created
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTailorRequest {
	private String typeService;
	private String name;
	private String price;
	private String opinion;
	private Set<Specialties> specialties;
	private Set<Availability> availability;
}
