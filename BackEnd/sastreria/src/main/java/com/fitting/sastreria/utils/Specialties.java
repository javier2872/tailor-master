package com.fitting.sastreria.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fitting.sastreria.model.pojo.Tailor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Specialties of a tailor service
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialties")
public class Specialties {

	public Specialties(String nameArg, String priceArg) {
		this.name = nameArg;
		this.price = priceArg;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "tailorId")
	private Tailor tailorId;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private String price;

}
