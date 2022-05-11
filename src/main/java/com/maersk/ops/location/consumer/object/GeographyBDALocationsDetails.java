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
public class GeographyBDALocationsDetails {
	 private String name;
	 private String type;
	 private String status;
	 private List<GeographyBDALocationAlternateCodeDetails> geographyBDALocationAlternateCodeDetails;
}
