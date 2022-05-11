package com.maersk.ops.location.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.BdaTypeDomain;
import com.maersk.ops.location.domain.CodeTypeDomain;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.model.EntityType;

@Service
public interface GeoServices {
	
	public List<AlternateNameDomain> createAltName(List<AlternateNameDomain> altNameDomainList, String rowid, EntityType entityType);
	public List<AlternateNameDomain> updateAltName(List<AlternateNameDomain> altNameDomainList, String rowid, EntityType entityType);
	
	public List<AlternateCodeDomain> createAltCode(List<AlternateCodeDomain> altCodeDomainList, String rowid, EntityType entityType);
	public List<AlternateCodeDomain> updateAltCode(List<AlternateCodeDomain> altCodeDomainList, String rowid, EntityType entityType);
	
	public List<ParentDetail> createParents(List<ParentDetail> parentDomainList, String rowid, EntityType entityType);
	public List<ParentDetail> updateParents(List<ParentDetail> parentDomainList, String rowid, EntityType entityType);
	
	public List<BdaDomainRelation> createBdaRelation(List<BdaDomainRelation> bdaList, String rowid, EntityType entityType); 
	public List<BdaDomainRelation> updateBdaRelation(List<BdaDomainRelation> bdaList, String rowid, EntityType entityType);
	
	public BdaDomain createBDA(BdaDomain bdaDomain);
	public BdaDomain updateBDA(BdaDomain bdaDomain);
	
	public CodeTypeDomain createCodeType(CodeTypeDomain domainCodeType);
	public CodeTypeDomain updateCodeType(CodeTypeDomain domainCodeType);
	
	public BdaTypeDomain createBdaType(BdaTypeDomain domainBdaType);
	public BdaTypeDomain updateBdaType(BdaTypeDomain domainBdaType);
	
	

}
