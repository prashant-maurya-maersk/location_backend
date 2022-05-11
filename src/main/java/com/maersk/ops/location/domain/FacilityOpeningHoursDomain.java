package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityOpeningHoursDomain {

	private Long rowid;
	
	private String openDay;
	private String openTimeHours;
	private String openTimeMinutes;
	private String closeTimeHours;
	private String closeTimeMinutes;
}
