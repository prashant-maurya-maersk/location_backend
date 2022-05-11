package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityAddressDomain {

	private Long rowid;
	
	private String houseNumber;
	private String street;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String postalCode;
	private String district;
	private String territory;
	private String country;
	private String latitude;
	private String longitude;
}
