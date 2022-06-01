package com.maersk.ops.location.lookups;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface LookupService {
	public List<TimezoneLookup> getTimezoneLookup();
	public List<OlsonLookup> getOlsonLookup();
	public List<ServiceGroupLookup> getServiceGroup();
	public List<CityLookup> getCity(String name, String status, String portFlag, String maerskCity);
	public List<StateLookup> getState(String name, String status, String workaroundReason);
	public List<CountryLookup> getCountry(String name, String status);
	public List<ContinentLookup> getContinent(String name, String status);
	public List<BdaLookup> getBda(String name, String status, Long bdaTypeId);
	public List<BdaTypeLookup> getBdaType();
	public List<FacilityTypeLookup> getFacType(String code, String name, String uniqueValueFlag);
	public List<FacilityServiceLookup> getFacilityServices(String name, String code, String isActive, String description, Long serGrpId);
	public List<CodeTypeLookup> getTypeCodes();
}
