package com.maersk.ops.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilitySummaryDomain {
	private Long rowid;
	private String name;
	private String extOwned;
	private String status;
	private String extExposed;
	private String facilityUrl;
}
