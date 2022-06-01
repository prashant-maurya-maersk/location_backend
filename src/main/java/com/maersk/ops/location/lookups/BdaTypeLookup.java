package com.maersk.ops.location.lookups;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BdaTypeLookup {
	private Long rowid;
	private String name;
}
