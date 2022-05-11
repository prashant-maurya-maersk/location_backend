package com.maersk.ops.location.domain;

import com.maersk.ops.location.model.ServiceGroups;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityServiceDomain {
	
    private Long rowid;
	
	private String name;
	private String description;
	private String vasCodes;
	private String isActive;
	private ServiceGroups serviceGroupCode;
	
}
