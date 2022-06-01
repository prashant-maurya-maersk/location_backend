package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityType {
	 private String  Code;
	 private String  Name;
	 private String  MasterType;
	 private String  ValidThroughDate;
}
