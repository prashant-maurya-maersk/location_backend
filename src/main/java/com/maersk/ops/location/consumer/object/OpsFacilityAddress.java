package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityAddress {
	  private String HouseNumber;
	  private String Street;
	  private String City;
	  private String PostalCode;
	  private String PoBox;
	  private String District;
	  private String Territory;
	  private String CountryName;
	  private String CountryCode;
	  private String AddressLine2;
	  private String AddressLine3;
	  private String Latitude;
	  private String Longitude;
	  private String AddressQualityCheckIndicator;
}
