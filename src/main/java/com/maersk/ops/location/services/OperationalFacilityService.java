package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.OperationalFacilityDomain;

@Service
public interface OperationalFacilityService {

	public OperationalFacilityDomain createOpsFacility(OperationalFacilityDomain domainOpsFacility);
	public OperationalFacilityDomain updateOpsFacility(OperationalFacilityDomain domainOpsFacility);
}
