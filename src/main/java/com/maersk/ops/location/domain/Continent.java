package com.maersk.ops.location.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CONTINENT")
public class Continent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String description;
	private String workaroundReason;	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "continent")
	private List<AlternateName> alternateNames;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "continent")
	private List<AlternateCode> alternateCodes;

}
