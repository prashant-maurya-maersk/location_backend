package com.maersk.ops.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeTypeDomain {

	private Long rowid;
	private String typeCode;
	private String typeName;
	private String description;
	private String status;
}
