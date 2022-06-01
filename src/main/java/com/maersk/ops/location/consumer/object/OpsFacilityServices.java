package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityServices {
	 private String  ServiceName;
	 private String  ServiceCode;
	 private String  ServiceDescription;
	 private String  ValidThroughDate;
}
