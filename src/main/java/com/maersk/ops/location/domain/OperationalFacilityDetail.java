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
@Table(name = "OP_FACILITY_DETAIL")
public class OperationalFacilityDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String weightLimitCraneKg;
	private String weightLimitYardKg;
	private String vesselAgent;
	private String gpsFlag;
	private String gsmFlag;
	private String oceanFreightPricing;
	
	@OneToOne(mappedBy = "facilityDetail")
	private OperationalFacility operationalFacility;
	
}
