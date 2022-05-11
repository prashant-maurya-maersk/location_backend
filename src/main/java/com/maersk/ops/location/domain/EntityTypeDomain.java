package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntityTypeDomain {
	private Long rowid;
	private String entityName;
}
