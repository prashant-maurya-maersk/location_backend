package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.ContinentDomain;
import com.maersk.ops.location.model.Continent;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.repository.ContinentRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.services.ContinentService;
import com.maersk.ops.location.services.GeoServices;

@Service
public class ContinentServiceImpl implements ContinentService {
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private ContinentRepo continentRepo;
	
	@Autowired
	private GeoServices geoServices;

	@Override
	public ContinentDomain createContinent(ContinentDomain domainContinent) {
		Continent continent = continentMapper(domainContinent, new Continent());
		continent = continentRepo.save(continent);
		EntityType entityType = entityRepo.findByEntityName("CONTINENT").get(0);
		if(domainContinent.getAlternateNames()!=null && domainContinent.getAlternateNames().size()>0) {
			List<AlternateNameDomain> altNameDomainList = geoServices.createAltName(domainContinent.getAlternateNames(), continent.getRowid().toString(), entityType);
			domainContinent.setAlternateNames(altNameDomainList);
		}
		if(domainContinent.getAlternateCodes()!=null && domainContinent.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = geoServices.createAltCode(domainContinent.getAlternateCodes(), continent.getRowid().toString(), entityType);
			domainContinent.setAlternateCodes(altCodeDomainList);
		}
		domainContinent.setRowid(continent.getRowid());
		return domainContinent;
	}
	
	@Override
	public ContinentDomain updateContinent(ContinentDomain domainContinent) {
		Optional<Continent> continentDB = null;
		try {
			continentDB = continentRepo.findById(domainContinent.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(continentDB.isPresent()) {
			Continent updatedContinent = continentMapper(domainContinent, continentDB.get());
			continentRepo.save(updatedContinent);
			EntityType entityType = entityRepo.findByEntityName("CONTINENT").get(0);
			if(domainContinent.getAlternateNames()!=null && domainContinent.getAlternateNames().size()>0) {
				List<AlternateNameDomain> altNameDomainList = geoServices.updateAltName(domainContinent.getAlternateNames(), updatedContinent.getRowid().toString(), entityType);
				domainContinent.setAlternateNames(altNameDomainList);
			}
			if(domainContinent.getAlternateCodes()!=null && domainContinent.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeDomainList = geoServices.updateAltCode(domainContinent.getAlternateCodes(), updatedContinent.getRowid().toString(), entityType);
				domainContinent.setAlternateCodes(altCodeDomainList);
			}
			domainContinent.setRowid(updatedContinent.getRowid());
		}
		return domainContinent;
	}
	
	private Continent continentMapper(ContinentDomain domainContinent, Continent continent) {
		if(domainContinent!=null) {
			return continent.builder().description(domainContinent.getDescription()).name(domainContinent.getName())
					.status(domainContinent.getStatus()).validFrom(domainContinent.getValidFrom()).validTo(domainContinent.getValidTo())
					.workaroundReason(domainContinent.getWorkaroundReason()).rowid(domainContinent.getRowid()==null?null:domainContinent.getRowid())
					.build();
		}
		return null;
	}
}
