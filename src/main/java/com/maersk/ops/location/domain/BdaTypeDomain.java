package com.maersk.ops.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BdaTypeDomain {
	private Long rowid;
	private String name;
	private String type;
	private String code;
	private String owner;
	private String bdaGroupType;
}
