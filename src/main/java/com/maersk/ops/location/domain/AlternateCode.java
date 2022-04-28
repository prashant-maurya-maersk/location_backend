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
@Table(name = "ALT_CODE")
public class AlternateCode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String codetype;
	
	private String code;
	
	private String type;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city_rowid",referencedColumnName = "rowid")
	private City city;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id",referencedColumnName = "rowid")
	private Country country;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bda_id",referencedColumnName = "rowid")
	private BDA bda;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "site_id",referencedColumnName = "rowid")
	private Site site;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "continent_id",referencedColumnName = "rowid")
	private Continent continent;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subarea_id",referencedColumnName = "rowid")
	private CitySubArea subarea;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id",referencedColumnName = "rowid")
	private State state;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "postal_id",referencedColumnName = "rowid")
	private PostalCode postalCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "op_fac_id",referencedColumnName = "rowid")
	private OperationalFacility operationalFacility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comm_fac_id",referencedColumnName = "rowid")
	private CommercialFacilityDetail commercialFacility;	
	
}
