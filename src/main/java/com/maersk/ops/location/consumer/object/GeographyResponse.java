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
public class GeographyResponse {
	private String geoType;
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String longitude;
	private String latitude;
	private String timeZone;
	private String daylightSavingTime;
	private String utcOffsetMinutes;
	private String daylightSavingStart;
	private String daylightSavingEnd;
	private String daylightSavingShiftMinutes;
	private String description;
	private String workaroundReason;
	private String restricted;
	private String siteType;
	private String gpsFlag;
	private String gsmFlag;
	private String streetNumber;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String postalCode;
	private String postalCodeMandatoryFlag;
	private String stateProvienceMandatory;
	private String dialingCode;
	private String dialingCodedescription;
	private String portFlag;
	private String olsonTimezone;
	private String bdaType;
	
	private String isMaerskCity;   // newly added 
	
	private List<GeographyAlternateNames> geographyAlternateNames;
	private List<GeographyAlternateCodes> geographyAlternateCodes;
	private List<GeographyFence> geographyFence;
	private List<GeographyCountryDetails> geographyCountryDetails;
	private List<GeographyParentDetails> geographyParentDetails;
	private List<GeographySubCityParentDetails> geographySubCityParentDetails;
	private List<GeographyBDADetails> geographyBDADetails;
	private List<GeographyBDALocationsDetails> geographyBDALocationsDetails;
	
}
