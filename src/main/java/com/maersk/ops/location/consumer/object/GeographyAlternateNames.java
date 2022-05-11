package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeographyAlternateNames {
	private String name;
	private String description;
	private String status;
}
