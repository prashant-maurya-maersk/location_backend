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
public class OpsFacilityParentDetails {
	 private String  Name;
	 private String  Type;
	 private List<OpsFacilityParentAlternateCode> OpsFacilityParentAlternateCodeDetails;
}
