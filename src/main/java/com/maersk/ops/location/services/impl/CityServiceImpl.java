package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.CityDomain;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.model.City;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.OlsonTimezone;
import com.maersk.ops.location.model.Timezone;
import com.maersk.ops.location.repository.CityRepository;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.OlsonTimeRepo;
import com.maersk.ops.location.repository.TimezoneRepo;
import com.maersk.ops.location.services.CityService;
import com.maersk.ops.location.services.GeoServices;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private GeoServices geoServices;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private TimezoneRepo timeRepo;
	
	@Autowired
	private OlsonTimeRepo olsonRepo;
	
	@Override
	public CityDomain createCity(CityDomain domainCity) {
		City city = cityMapper(domainCity, new City());
		city = cityRepo.save(city);
		EntityType entityType = entityRepo.findByEntityName("CITY").get(0);
		if(domainCity.getAlternateNames()!=null && domainCity.getAlternateNames().size()>0) {
			List<AlternateNameDomain> altNameDomainList = geoServices.createAltName(domainCity.getAlternateNames(), city.getRowid().toString(), entityType);
			domainCity.setAlternateNames(altNameDomainList);
		}
		if(domainCity.getAlternateCodes()!=null && domainCity.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = geoServices.createAltCode(domainCity.getAlternateCodes(), city.getRowid().toString(), entityType);
			domainCity.setAlternateCodes(altCodeDomainList);
		}
		if(domainCity.getParentDetails()!=null && domainCity.getParentDetails().size()>0) {
			List<ParentDetail> parentDomainList = geoServices.createParents(domainCity.getParentDetails(), city.getRowid().toString(), entityType);
			domainCity.setParentDetails(parentDomainList);
		}
		if(domainCity.getBdaDetails()!=null && domainCity.getBdaDetails().size()>0) {
			List<BdaDomainRelation> bdaRelations = geoServices.createBdaRelation(domainCity.getBdaDetails(), city.getRowid().toString(), entityType);
			domainCity.setBdaDetails(bdaRelations);
		}
		domainCity.setRowid(city.getRowid());
		return domainCity;
	}
	
	@Override
	public CityDomain updateCity(CityDomain domainCity) {
		Optional<City> cityModel = null;
		try {
			cityModel = cityRepo.findById(domainCity.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(cityModel.isPresent()) {
			City updatedCity = cityMapper(domainCity,cityModel.get());
			cityRepo.save(updatedCity);
			EntityType entityType = entityRepo.findByEntityName("CITY").get(0);
			if(domainCity.getAlternateNames()!=null && domainCity.getAlternateNames().size()>0) {
				List<AlternateNameDomain> altNames= geoServices.updateAltName(domainCity.getAlternateNames(), updatedCity.getRowid().toString(), entityType);
				domainCity.setAlternateNames(altNames);
			}
			if(domainCity.getAlternateCodes()!=null && domainCity.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodes = geoServices.updateAltCode(domainCity.getAlternateCodes(), updatedCity.getRowid().toString(), entityType);
				domainCity.setAlternateCodes(altCodes);
			}
			if(domainCity.getParentDetails()!=null && domainCity.getParentDetails().size()>0) {
				List<ParentDetail> parent = geoServices.updateParents(domainCity.getParentDetails(), updatedCity.getRowid().toString(), entityType);
				domainCity.setParentDetails(parent);
			}
			if(domainCity.getBdaDetails()!=null && domainCity.getBdaDetails().size()>0) {
				List<BdaDomainRelation> bdaRel = geoServices.updateBdaRelation(domainCity.getBdaDetails(), updatedCity.getRowid().toString(), entityType);
				domainCity.setBdaDetails(bdaRel);
			}
			domainCity.setRowid(updatedCity.getRowid());
		}
		return domainCity;
	}
	
	private City cityMapper(CityDomain domainCity, City city) {
		if(domainCity != null) {
			Optional<Timezone> timezoneDB = timeRepo.findById(domainCity.getTimezone().getRowid());
			Optional<OlsonTimezone> olsonDB = olsonRepo.findById(domainCity.getOlsonTimezone().getRowid());
			if(timezoneDB.isPresent() && olsonDB.isPresent()) {
				return city.builder().description(domainCity.getDescription()).isMaerskCity(domainCity.getIsMaerskCity())
						.latitude(domainCity.getLatitude()).longitude(domainCity.getLongitude()).name(domainCity.getName())
						.olsonTimezone(olsonDB.get()).portFlag(domainCity.getPortFlag()).status(domainCity.getStatus())
						.timezone(timezoneDB.get()).validFrom(domainCity.getValidFrom()).validTo(domainCity.getValidTo())
						.workaroundReason(domainCity.getWorkaroundReason())
						.rowid(domainCity.getRowid()==null?null:Long.valueOf(domainCity.getRowid())).build();
			}
		}
		return null;
	}
}
