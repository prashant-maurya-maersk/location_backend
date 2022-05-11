package com.maersk.ops.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SiteDomain {

	private Long rowid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String rowIdObject;
	private String description;
	private String workaroundReason;
	private String siteType;
	private String gpsFlag;
	private String gsmFlag;
	private String street;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String postalCode;
	private String latitude;
	private String longitude;
	
    private List<AlternateNameDomain> alternateNames;
	
	private List<AlternateCodeDomain> alternateCodes;
	
	private List<ParentDetail> parentDetails;
	
	private List<BdaDomainRelation> bdaDetails;
}
