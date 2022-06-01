package com.maersk.ops.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommercialFacilityDomain {

//	private Long rowid;
//	
//	private String name;
//	private String extOwned;
//	private String status;
//	private String extExposed;
//	private String facilityUrl;
	
	private FacilitySummaryDomain facilitySummary;
	
	List<CommercialFacilityDetailDomain> commercialFacilityDetails;
	
	FacilityAddressDomain facilityAddress;
	
    List<AlternateCodeDomain> alternateCodes;
    
    List<FacilityOpeningHoursDomain> facilityOpeningHours;
    
    List<FacilityServiceRelationDomain> facilityServices;
	
	
}
