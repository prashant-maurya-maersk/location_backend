package com.maersk.ops.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationalFacilityDetailDomain {

	private Long rowid;
	
	private String weightLimitCraneKg;
	private String weightLimitYardKg;
	private String vesselAgent;
	private String gpsFlag;
	private String gsmFlag;
	private String oceanFreightPricing;
	
	List<FacilityDetailTypeDomain> facilityDetailTypes;
}
