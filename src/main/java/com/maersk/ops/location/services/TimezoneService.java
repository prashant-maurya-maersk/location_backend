package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.OlsonDomain;
import com.maersk.ops.location.domain.TimezoneDomain;

@Service
public interface TimezoneService {
	
	public TimezoneDomain createTimezone(TimezoneDomain timezone);
	public TimezoneDomain updateTimezone(TimezoneDomain timezone);
	
	public OlsonDomain createOlsonTimezone(OlsonDomain domainOlson);
	public OlsonDomain updateOlsonTimezone(OlsonDomain domainOlson);

}
