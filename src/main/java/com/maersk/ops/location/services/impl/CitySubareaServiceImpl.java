package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.CitySubAreaDomain;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.model.CitySubArea;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.OlsonTimezone;
import com.maersk.ops.location.model.Timezone;
import com.maersk.ops.location.repository.CitySubareaRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.OlsonTimeRepo;
import com.maersk.ops.location.repository.TimezoneRepo;
import com.maersk.ops.location.services.CitySubareaService;
import com.maersk.ops.location.services.GeoServices;

@Service
public class CitySubareaServiceImpl implements CitySubareaService{
	
	@Autowired
	private CitySubareaRepo subAreaRepo;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private GeoServices geoServices;
	
	@Autowired
	private TimezoneRepo timeRepo;
	
	@Autowired
	private OlsonTimeRepo olsonRepo;
	
	@Override
	public CitySubAreaDomain createSubarea(CitySubAreaDomain domainSubarea) {
		CitySubArea citySubarea = citySubareaMapper(domainSubarea, new CitySubArea());
		citySubarea = subAreaRepo.save(citySubarea);
		EntityType entityType = entityRepo.findByEntityName("SUBAREA").get(0);
		if(domainSubarea.getAlternateNames()!=null && domainSubarea.getAlternateNames().size()>0) {
			List<AlternateNameDomain> altNameDomainList = geoServices.createAltName(domainSubarea.getAlternateNames(), citySubarea.getRowid().toString(), entityType);
			domainSubarea.setAlternateNames(altNameDomainList);
		}
		if(domainSubarea.getAlternateCodes()!=null && domainSubarea.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = geoServices.createAltCode(domainSubarea.getAlternateCodes(), citySubarea.getRowid().toString(), entityType);
			domainSubarea.setAlternateCodes(altCodeDomainList);
		}
		if(domainSubarea.getParentDetails()!=null && domainSubarea.getParentDetails().size()>0) {
			List<ParentDetail> parentList = geoServices.createParents(domainSubarea.getParentDetails(), citySubarea.getRowid().toString(), entityType);
			domainSubarea.setParentDetails(parentList);
		}
		if(domainSubarea.getBdaDetails()!=null && domainSubarea.getBdaDetails().size()>0) {
			List<BdaDomainRelation> bdaList = geoServices.createBdaRelation(domainSubarea.getBdaDetails(), citySubarea.getRowid().toString(), entityType);
			domainSubarea.setBdaDetails(bdaList);
		}
		domainSubarea.setRowid(citySubarea.getRowid());
		return domainSubarea;
	}
	
	@Override
	public CitySubAreaDomain updateSubarea(CitySubAreaDomain domainSubarea) {
		Optional<CitySubArea> subAreaDB = null;
		try {
			subAreaDB = subAreaRepo.findById(domainSubarea.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(subAreaDB.isPresent()) {
			CitySubArea updatedSubArea = citySubareaMapper(domainSubarea, subAreaDB.get());
			subAreaRepo.save(updatedSubArea);
			EntityType entityType = entityRepo.findByEntityName("SUBAREA").get(0);
			if(domainSubarea.getAlternateNames()!=null && domainSubarea.getAlternateNames().size()>0) {
				List<AlternateNameDomain> altNameDomainList = geoServices.updateAltName(domainSubarea.getAlternateNames(), updatedSubArea.getRowid().toString(), entityType);
				domainSubarea.setAlternateNames(altNameDomainList);
			}
			if(domainSubarea.getAlternateCodes()!=null && domainSubarea.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeDomainList = geoServices.updateAltCode(domainSubarea.getAlternateCodes(), updatedSubArea.getRowid().toString(), entityType);
				domainSubarea.setAlternateCodes(altCodeDomainList);
			}
			if(domainSubarea.getParentDetails()!=null && domainSubarea.getParentDetails().size()>0) {
				List<ParentDetail> parentList = geoServices.updateParents(domainSubarea.getParentDetails(), updatedSubArea.getRowid().toString(), entityType);
				domainSubarea.setParentDetails(parentList);
			}
			if(domainSubarea.getBdaDetails()!=null && domainSubarea.getBdaDetails().size()>0) {
				List<BdaDomainRelation> bdaList = geoServices.updateBdaRelation(domainSubarea.getBdaDetails(), updatedSubArea.getRowid().toString(), entityType);
				domainSubarea.setBdaDetails(bdaList);
			}
			domainSubarea.setRowid(updatedSubArea.getRowid());
		}
		return domainSubarea;
	}
	
	private CitySubArea citySubareaMapper(CitySubAreaDomain domainSubarea, CitySubArea subArea) {
		if(domainSubarea!=null) {
			Optional<Timezone> timezoneDB = timeRepo.findById(domainSubarea.getTimezone().getRowid());
			Optional<OlsonTimezone> olsonDB = olsonRepo.findById(domainSubarea.getOlsonTimezone().getRowid());
			if(timezoneDB.isPresent() && olsonDB.isPresent()) {
				return subArea.builder().description(domainSubarea.getDescription()).latitude(domainSubarea.getLatitude())
						.longitude(domainSubarea.getLongitude()).name(domainSubarea.getName()).olsonTimezone(olsonDB.get())
						.portFlag(domainSubarea.getPortFlag()).status(domainSubarea.getStatus()).timezone(timezoneDB.get())
						.validFrom(domainSubarea.getValidFrom()).validTo(domainSubarea.getValidTo())
						.rowid(domainSubarea.getRowid()==null?null:domainSubarea.getRowid()).build();
			}
		}
		return null;
	}

}
