package com.maersk.ops.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OlsonDomain {
	private Long rowid;
	private String name;
	private String description;

}
