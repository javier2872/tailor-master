package com.fitting.sastreria.model.pojo;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ElementCollection;
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
		
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tailor_specialties", joinColumns = @JoinColumn(name = "id_tailor"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "price", column = @Column(name = "price"))
    })
	private List<Specialties> specialties;
	

    @ElementCollection
    @CollectionTable(name = "tailor_availability", joinColumns = @JoinColumn(name = "id_tailor"))
    @Column(name = "availability")
	private List<String> availability;

}
