package com.maersk.ops.location.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContinentDomain {
	
    private Long rowid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String description;
	private String workaroundReason;	
	
    private List<AlternateNameDomain> alternateNames;
	
	private List<AlternateCodeDomain> alternateCodes;

}
