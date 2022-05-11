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
public class GeographyCountryDetails {
	private String name;
	private String type;
	private List<GeographyCountryAlternateCodeDetails> geographyCountryAlternateCodeDetails;
	public String getName() {
		return name;
	}
	public String getType() {
		if(type.equalsIgnoreCase("GDA.COUNTRY")){
			type="Country";
		}
		return type;
	}
	public List<GeographyCountryAlternateCodeDetails> getGeographyCountryAlternateCodeDetails() {
		return geographyCountryAlternateCodeDetails;
	}
}
