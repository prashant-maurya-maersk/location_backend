package com.maersk.ops.location.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.domain.PostalCodeDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.PostalCode;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.PostalCodeRepo;
import com.maersk.ops.location.services.GeoServices;
import com.maersk.ops.location.services.PostalCodeService;

@Service
public class PostalCodeServiceImpl implements PostalCodeService {
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private PostalCodeRepo postalRepo;
	
	@Autowired
	private GeoServices geoServices;
	
	@Override
	public PostalCodeDomain createPostalCode(PostalCodeDomain domainPostal) {
		PostalCode postal = postalCodeMapper(domainPostal, new PostalCode());
		postal = postalRepo.save(postal);
		EntityType entityType = entityRepo.findByEntityName("POSTAL_CODE").get(0);
		if(domainPostal.getAlternateCodes()!=null && domainPostal.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = geoServices.createAltCode(domainPostal.getAlternateCodes(), postal.getRowid().toString(), entityType);
			domainPostal.setAlternateCodes(altCodeDomainList);
		}
		if(domainPostal.getParentDetails()!=null && domainPostal.getParentDetails().size()>0) {
			List<ParentDetail> parentDomainList = geoServices.createParents(domainPostal.getParentDetails(), postal.getRowid().toString(), entityType);
			domainPostal.setParentDetails(parentDomainList);
		}
		if(domainPostal.getBdaDetails()!=null && domainPostal.getBdaDetails().size()>0) {
			List<BdaDomainRelation> bdaRelations = geoServices.createBdaRelation(domainPostal.getBdaDetails(), postal.getRowid().toString(), entityType);
			domainPostal.setBdaDetails(bdaRelations);
		}
		domainPostal.setRowid(postal.getRowid());
		return domainPostal;
	}
	
	@Override
	public PostalCodeDomain updatePostalCode(PostalCodeDomain domainPostal) {
		Optional<PostalCode> postalDB = null;
		try {
			postalDB = postalRepo.findById(domainPostal.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(postalDB.isPresent()) {
			PostalCode updatedPostal = postalCodeMapper(domainPostal, postalDB.get());
			postalRepo.save(updatedPostal);
			EntityType entityType = entityRepo.findByEntityName("POSTAL_CODE").get(0);
			if(domainPostal.getAlternateCodes()!=null && domainPostal.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeDomainList = geoServices.updateAltCode(domainPostal.getAlternateCodes(), updatedPostal.getRowid().toString(), entityType);
				domainPostal.setAlternateCodes(altCodeDomainList);
			}
			if(domainPostal.getParentDetails()!=null && domainPostal.getParentDetails().size()>0) {
				List<ParentDetail> parentDomainList = geoServices.updateParents(domainPostal.getParentDetails(), updatedPostal.getRowid().toString(), entityType);
				domainPostal.setParentDetails(parentDomainList);
			}
			if(domainPostal.getBdaDetails()!=null && domainPostal.getBdaDetails().size()>0) {
				List<BdaDomainRelation> bdaRelations = geoServices.updateBdaRelation(domainPostal.getBdaDetails(), updatedPostal.getRowid().toString(), entityType);
				domainPostal.setBdaDetails(bdaRelations);
			}
			domainPostal.setRowid(updatedPostal.getRowid());
		}
		return domainPostal;
	}
	
	private PostalCode postalCodeMapper(PostalCodeDomain domainPostal, PostalCode postal) {
		if(domainPostal != null) {
			return postal.builder().description(domainPostal.getDescription()).name(domainPostal.getName())
					.postalcode(domainPostal.getPostalcode()).status(domainPostal.getStatus()).validFrom(domainPostal.getValidFrom())
					.validTo(domainPostal.getValidTo()).rowid(domainPostal.getRowid()==null?null:domainPostal.getRowid()).build();
		}
		return null;
	}

}
