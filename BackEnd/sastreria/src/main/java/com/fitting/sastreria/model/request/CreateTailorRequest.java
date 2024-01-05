package com.fitting.sastreria.model.request;

import java.util.List;

import com.fitting.sastreria.model.pojo.Tailor;
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
public class CreateTailorRequest extends Tailor{
	private String name;
	private String description;
	private List<Specialties> specialties;
	private List<String> availability;
}
