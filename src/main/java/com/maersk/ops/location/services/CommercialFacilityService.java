package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.CommercialFacilityDomain;

@Service
public interface CommercialFacilityService {
	
	public CommercialFacilityDomain createCommFacility(CommercialFacilityDomain domainComFac);
	public CommercialFacilityDomain updateCommFacility(CommercialFacilityDomain domainComFac);

}
