package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.CountryDomain;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.model.Country;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.Timezone;
import com.maersk.ops.location.repository.CountryRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.TimezoneRepo;
import com.maersk.ops.location.services.CountryService;
import com.maersk.ops.location.services.GeoServices;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private GeoServices geoServices;
	
	@Autowired
	private TimezoneRepo timeRepo;
	
	@Override 
	public CountryDomain createCountry(CountryDomain domainCountry) {
		Country country = countryMapper(domainCountry, new Country());
		country = countryRepo.save(country);
		EntityType entityType = entityRepo.findByEntityName("COUNTRY").get(0);
		if(domainCountry.getAlternateNames()!=null && domainCountry.getAlternateNames().size()>0) {
			List<AlternateNameDomain> altNameDomainList = geoServices.createAltName(domainCountry.getAlternateNames(), country.getRowid().toString(), entityType);
			domainCountry.setAlternateNames(altNameDomainList);
		}
		if(domainCountry.getAlternateCodes()!=null && domainCountry.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = geoServices.createAltCode(domainCountry.getAlternateCodes(), country.getRowid().toString(), entityType);
			domainCountry.setAlternateCodes(altCodeDomainList);
		}
		if(domainCountry.getParentDetails()!=null && domainCountry.getParentDetails().size()>0) {
			List<ParentDetail> parentList = geoServices.createParents(domainCountry.getParentDetails(), country.getRowid().toString(), entityType);
			domainCountry.setParentDetails(parentList);
		}
		if(domainCountry.getBdaDetails()!=null && domainCountry.getBdaDetails().size()>0) {
			List<BdaDomainRelation> bdaList = geoServices.createBdaRelation(domainCountry.getBdaDetails(), country.getRowid().toString(), entityType);
			domainCountry.setBdaDetails(bdaList);
		}
		domainCountry.setRowid(country.getRowid());
		return domainCountry;
	}
	
	@Override
	public CountryDomain updateCountry(CountryDomain domainCountry) {
		Optional<Country> countryDB = null;
		try {
			countryDB = countryRepo.findById(domainCountry.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(countryDB.isPresent()) {
			Country updatedCountry = countryMapper(domainCountry, countryDB.get());
			countryRepo.save(updatedCountry);
			EntityType entityType = entityRepo.findByEntityName("COUNTRY").get(0);
			if(domainCountry.getAlternateNames()!=null && domainCountry.getAlternateNames().size()>0) {
				List<AlternateNameDomain> altNameDomainList = geoServices.updateAltName(domainCountry.getAlternateNames(), updatedCountry.getRowid().toString(), entityType);
				domainCountry.setAlternateNames(altNameDomainList);
			}
			if(domainCountry.getAlternateCodes()!=null && domainCountry.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeDomainList = geoServices.updateAltCode(domainCountry.getAlternateCodes(), updatedCountry.getRowid().toString(), entityType);
				domainCountry.setAlternateCodes(altCodeDomainList);
			}
			if(domainCountry.getParentDetails()!=null && domainCountry.getParentDetails().size()>0) {
				List<ParentDetail> parentList = geoServices.updateParents(domainCountry.getParentDetails(), updatedCountry.getRowid().toString(), entityType);
				domainCountry.setParentDetails(parentList);
			}
			if(domainCountry.getBdaDetails()!=null && domainCountry.getBdaDetails().size()>0) {
				List<BdaDomainRelation> bdaList = geoServices.updateBdaRelation(domainCountry.getBdaDetails(), updatedCountry.getRowid().toString(), entityType);
				domainCountry.setBdaDetails(bdaList);
			}
			domainCountry.setRowid(updatedCountry.getRowid());
		}
		return domainCountry;
	}
	
	private Country countryMapper(CountryDomain domainCountry, Country country) {
		if(domainCountry!=null) {
			Optional<Timezone> timeDB = timeRepo.findById(domainCountry.getTimezone().getRowid());
			if(timeDB.isPresent()) {
				return country.builder().description(domainCountry.getDescription()).name(domainCountry.getName())
						.postalCodeMandatory(domainCountry.getPostalCodeMandatory()).restricted(domainCountry.getRestricted())
						.stateMandatory(domainCountry.getStateMandatory()).status(domainCountry.getStatus()).timezone(timeDB.get())
						.validFrom(domainCountry.getValidFrom()).validTo(domainCountry.getValidTo()).workaroundReason(domainCountry.getWorkaroundReason())
						.rowid(domainCountry.getRowid()==null?null:domainCountry.getRowid()).build();
			}
		}
		return null;
	}
}
