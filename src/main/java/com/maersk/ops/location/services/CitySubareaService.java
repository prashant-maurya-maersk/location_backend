package com.maersk.ops.location.services;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.CitySubAreaDomain;

@Service
public interface CitySubareaService {
	
	public CitySubAreaDomain createSubarea(CitySubAreaDomain domainSubArea);
	public CitySubAreaDomain updateSubarea(CitySubAreaDomain domainSubArea);
}
