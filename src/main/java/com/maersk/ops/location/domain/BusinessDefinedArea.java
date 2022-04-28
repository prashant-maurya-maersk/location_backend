package com.maersk.ops.location.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BDA")
public class BusinessDefinedArea {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city_rowid",referencedColumnName = "rowid")
	private City city;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "site_id",referencedColumnName = "rowid")
	private Site site;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id",referencedColumnName = "rowid")
	private Country country;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subarea_id",referencedColumnName = "rowid")
	private CitySubArea subarea;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "postal_id",referencedColumnName = "rowid")
	private PostalCode postalCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id",referencedColumnName = "rowid")
	private State state;
}
