package com.maersk.ops.location.lookups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilityTypeLookup {
	private Long rowid;
	private String name;
	private String code;
	private String masterType;
}
