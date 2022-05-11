package com.maersk.ops.location.domain;

import java.util.List;

import com.maersk.ops.location.model.OlsonTimezone;
import com.maersk.ops.location.model.Timezone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDomain {
	
	private Long rowid;
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String description;
	private String workaroundReason;
	private String portFlag;
	private String isMaerskCity;
	private String latitude;
	private String longitude;
	
	private Timezone timezone;
	
	private OlsonTimezone olsonTimezone;
	
	private List<AlternateNameDomain> alternateNames;
	
	private List<AlternateCodeDomain> alternateCodes;
	
	private List<ParentDetail> parentDetails;
	
	private List<BdaDomainRelation> bdaDetails;
	
}
