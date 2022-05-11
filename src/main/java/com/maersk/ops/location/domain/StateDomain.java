package com.maersk.ops.location.domain;

import java.util.List;

import com.maersk.ops.location.model.Timezone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDomain {
	
    private Long rowid;
	
	private String name;
	private String status;
	private String description;
	private String workaroundReason;
	private String validFrom;
	private String validTo;
	
	private Timezone timeZone;
	
    private List<AlternateNameDomain> alternateNames;
	
	private List<AlternateCodeDomain> alternateCodes;
	
	private List<ParentDetail> parentDetails;
	
	private List<BdaDomainRelation> bdaDetails;
}
