package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommercialFacilityDetailDomain {

	private Long rowid;
	
	private String facilityBrand;
	private String facilityBrandCode;
	private String commercialFacilityType;
	private String internationalDialingCode;
	private String phoneNumber;
	private String commercialFacilityFunction;
	private String exportEnquiriesEmail;
	private String importEnquiriesEmail;
}
