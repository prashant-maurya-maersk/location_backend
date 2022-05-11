package com.maersk.ops.location.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "continent")
//	private List<AlternateName> alternateNames;
//	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "continent")
//	private List<AlternateCode> alternateCodes;

}
