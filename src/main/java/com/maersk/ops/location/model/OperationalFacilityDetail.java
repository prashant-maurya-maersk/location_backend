package com.maersk.ops.location.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "OPS_FACILITY_DETAIL")
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
