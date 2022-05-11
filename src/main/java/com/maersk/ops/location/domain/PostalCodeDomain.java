package com.maersk.ops.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostalCodeDomain {

	private Long rowid;
	
	private String name;
	private String postalcode;
	private String status;
	private String description;
	private String validFrom;
	private String validTo;
	
    private List<AlternateCodeDomain> alternateCodes;
	
	private List<ParentDetail> parentDetails;
	
	private List<BdaDomainRelation> bdaDetails;
}
