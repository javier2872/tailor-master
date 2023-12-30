package com.fitting.trabajo.model.pojo;

import java.util.List;

import com.fitting.trabajo.utils.Specialties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Defining a tailor
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Tailor {

	private Long id;
	
	private String typeService;
	
	private String name;
	
	private String price;
	
	private String opinion;
	
	private List<Specialties> specialties;
	
	private List<String> availability;

}
