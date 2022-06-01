package com.maersk.ops.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationalFacilityDomain {

//	private Long rowid;
//	
//	private String name;
//	private String extOwned;
//	private String status;
//	private String extExposed;
//	private String facilityUrl;
	
	private FacilitySummaryDomain facilitySummary;
	
	private FacilityAddressDomain facilityAddress;
	
	private OperationalFacilityDetailDomain facilityDetail;
	
	private List<AlternateCodeDomain> alternateCodes;
	
	private List<FacilityOpeningHoursDomain> facilityOpeningHours;
	
	private List<FacilityServiceRelationDomain> facilityServices;
	
	private List<FacilityTransportModeDomain> facilityTransportMode;
	
	private List<FacilityContactDetailDomain> facilityContactDetails;
}
