package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.ContinentDomain;

@Service
public interface ContinentService {
	
	public ContinentDomain createContinent(ContinentDomain domainContinent);
	
	public ContinentDomain updateContinent(ContinentDomain domainContinent);

}
