package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.CityDomain;

@Service
public interface CityService {
	
	public CityDomain createCity(CityDomain domain);
	
	public CityDomain updateCity(CityDomain city);

}
