package com.maersk.ops.location.consumer.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityBDADetails {
	 private String Name;
	 private String Type;
	 private String BDAType;
	 private List<OpsFacilityBDAAlternateCode> OpsFacilityBDAAlternateCodeDetails;
}
