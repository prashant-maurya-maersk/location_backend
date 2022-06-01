package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityTransportModes {
	 private String  TransportMode;
	 private String  TransportCode;
	 private String  TransportDescription;
	 private String  ValidThroughDate;
}
