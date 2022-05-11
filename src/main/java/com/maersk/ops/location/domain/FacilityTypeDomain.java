package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityTypeDomain {
    private Long rowid;	
	private String code;
	private String name;
	private String uniqueValueFlag;
	private String mustBeFlag;
	private String masterType;
}
