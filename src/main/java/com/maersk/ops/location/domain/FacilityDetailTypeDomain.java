package com.maersk.ops.location.domain;

import com.maersk.ops.location.model.FacilityType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityDetailTypeDomain {
	private Long rowid;
	private FacilityType facilityType;
	private String relationshipValidThrough;
}
