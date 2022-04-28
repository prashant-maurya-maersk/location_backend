package com.maersk.ops.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FAC_ADDRESS")
public class FacilityAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String houseNumber;
	private String street;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String postalCode;
	private String district;
	private String territory;
	private String country;
	private String latitude;
	private String longitude;
	
	@OneToOne(mappedBy = "facilityAddress")
	private OperationalFacility operationalFacility;
	
	@OneToOne(mappedBy = "facilityAddress")
	private CommercialFacility commFacility;
}
