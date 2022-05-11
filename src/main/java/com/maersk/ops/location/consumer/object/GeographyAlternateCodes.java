package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeographyAlternateCodes {
	private String codeType;
	private String code;
}
