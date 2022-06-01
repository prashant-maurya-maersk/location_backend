package com.maersk.ops.location.domain;

import java.util.List;

import com.maersk.ops.location.model.Timezone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDomain {
	private Long rowid;
	private String name;
	private String status;
	private String description;
	private String workaroundReason;
	private String validFrom;
	private String validTo;
	private String restricted;
	private String postalCodeMandatory;
	private String stateMandatory;
	private Timezone timezone;
	
    private List<AlternateNameDomain> alternateNames;
	
	private List<AlternateCodeDomain> alternateCodes;
	
	private List<ParentDetail> parentDetails;
	
	private List<BdaDomainRelation> bdaDetails;
}
