package com.fitting.sastreria.model.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fitting.sastreria.utils.Availability;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fitting.sastreria.utils.Specialties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Defining a tailor
 */
@Entity
@Table(name = "tailor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Tailor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "typeService")
	private String typeService;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private String price;

	@Column(name = "opinion")
	private String opinion;

	@JsonManagedReference
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "tailorId")
	private Set<Availability> availability;

	@JsonManagedReference
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "tailorId")
	private Set<Specialties> specialties;

}
