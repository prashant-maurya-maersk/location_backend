package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityTransportModeDomain {

	private Long rowid;
	private String transportMode; 
	private String transportCode; 
	private String transportDescription; 
	private String validThroughDate; 
}
