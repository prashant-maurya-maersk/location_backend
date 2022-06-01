package com.maersk.ops.location.lookups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BdaLookup {
	private Long rowid;
	private String name;
	private String status;
	private Long bdaTypeId;
	private String validTo;
}
