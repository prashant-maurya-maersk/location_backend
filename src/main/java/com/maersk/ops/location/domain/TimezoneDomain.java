package com.maersk.ops.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimezoneDomain {
	private Long rowid;
	private String code;
	private String name;
	private String offsetMin;
	private String description;
	private DST dst;
}
