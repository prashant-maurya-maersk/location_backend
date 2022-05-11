package com.maersk.ops.location.domain;

import com.maersk.ops.location.model.FacilityService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityServiceRelationDomain {

	private Long rowid;
	private FacilityService facilityService;
	private String validThroughDate;
}
