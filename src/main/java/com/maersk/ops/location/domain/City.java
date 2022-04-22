package com.maersk.ops.location.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String description;
	private String workaroundReason;
	private String portFlag;
	private String maerskCity;
	private String latitude;
	private String longitude;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timezone_id",referencedColumnName = "rowid")
	private Timezone timezone;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
	List<AlternateName> alternateNames;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
	List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
	List<BusinessDefinedArea> bda;
}
