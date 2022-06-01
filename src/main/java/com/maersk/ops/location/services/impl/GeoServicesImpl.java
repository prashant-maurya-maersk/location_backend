package com.maersk.ops.location.services.impl;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.AlternateNameDomain;
import com.maersk.ops.location.domain.BdaDomain;
import com.maersk.ops.location.domain.BdaDomainRelation;
import com.maersk.ops.location.domain.BdaTypeDomain;
import com.maersk.ops.location.domain.CodeTypeDomain;
import com.maersk.ops.location.domain.ParentDetail;
import com.maersk.ops.location.model.AlternateCode;
import com.maersk.ops.location.model.AlternateName;
import com.maersk.ops.location.model.BDA;
import com.maersk.ops.location.model.BDAType;
import com.maersk.ops.location.model.BdaRelation;
import com.maersk.ops.location.model.CodeType;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.ParentDetailRelation;
import com.maersk.ops.location.repository.AlternateCodeRepo;
import com.maersk.ops.location.repository.AlternateNameRepo;
import com.maersk.ops.location.repository.BdaRelationRepo;
import com.maersk.ops.location.repository.BdaRepo;
import com.maersk.ops.location.repository.BdaTypeRepo;
import com.maersk.ops.location.repository.CodeTypeRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.ParentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.services.GeoServices;

@Service
public class GeoServicesImpl implements GeoServices{
	
	@Autowired
	private AlternateNameRepo altNameRepo;
	
	@Autowired
	private AlternateCodeRepo altCodeRepo;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private ParentRepo parentRepo;
	
	@Autowired
	private BdaRelationRepo bdaRelRepo;
	
	@Autowired
	private BdaRepo bdaRepo;
	
	@Autowired
	private CodeTypeRepo codeTypeRepo;
	
	@Autowired
	private BdaTypeRepo bdaTypeRepo;
	
	@Override
	public List<AlternateNameDomain> createAltName(List<AlternateNameDomain> altNameDomainList, String rowid, EntityType entityType){
		List<AlternateName> altNameList = altNameModelMapper(altNameDomainList, rowid, entityType);
		altNameList = altNameRepo.saveAll(altNameList);
		altNameDomainList = altNameDomainMapper(altNameList);
		return altNameDomainList;
	}
	
	@Override
	public List<AlternateNameDomain> updateAltName(List<AlternateNameDomain> altNameDomainList, String rowid, EntityType entityType){
		List<AlternateName> altNameList = altNameModelMapper(altNameDomainList, rowid, entityType);
		altNameList = altNameRepo.saveAll(altNameList);
		altNameDomainList = altNameDomainMapper(altNameList);
		return altNameDomainList;
	}
	
	private List<AlternateName> altNameModelMapper(List<AlternateNameDomain> domainAltNameList, String rowid, EntityType entityType){
		List<AlternateName> altNameList = new ArrayList<AlternateName>();
		for(AlternateNameDomain domainAltName : domainAltNameList) {
			altNameList.add(AlternateName.builder().description(domainAltName.getDescription()).entityId(rowid).entityTypeId(entityType).name(domainAltName.getName())
					.status(domainAltName.getStatus()).rowid(domainAltName.getRowid()==null?null:domainAltName.getRowid()).build());
		}
		return altNameList;
	}
	
	private List<AlternateNameDomain> altNameDomainMapper(List<AlternateName> altNameList){
		List<AlternateNameDomain> domainAltNameList = new ArrayList<AlternateNameDomain>();
		for(AlternateName altName : altNameList) {
			domainAltNameList.add(AlternateNameDomain.builder().description(altName.getDescription()).name(altName.getName()).rowid(altName.getRowid())
					.status(altName.getStatus()).build());
		}
		return domainAltNameList;
	}
	
	@Override
	public List<AlternateCodeDomain> createAltCode(List<AlternateCodeDomain> altCodeDomainList, String rowid, EntityType entityType){
		List<AlternateCode> altCodeList = altCodeModelMapper(altCodeDomainList, rowid, entityType);
		altCodeList = altCodeRepo.saveAll(altCodeList);
		altCodeDomainList = altCodeDomainMapper(altCodeList);
		return altCodeDomainList;
	}
	
	@Override
	public List<AlternateCodeDomain> updateAltCode(List<AlternateCodeDomain> altCodeDomainList, String rowid, EntityType entityType){
		List<AlternateCode> altCodeList = altCodeModelMapper(altCodeDomainList, rowid, entityType);
		altCodeList = altCodeRepo.saveAll(altCodeList);
		altCodeDomainList = altCodeDomainMapper(altCodeList);
		return altCodeDomainList;
	}
	
	private List<AlternateCode> altCodeModelMapper(List<AlternateCodeDomain> domainAltCodeList, String rowid, EntityType entityType){
		List<AlternateCode> altCodeList = new ArrayList<AlternateCode>();
		for(AlternateCodeDomain domainAltCode : domainAltCodeList) {
			Optional<CodeType> codeTypeDB = codeTypeRepo.findById(domainAltCode.getCodetype().getRowid());
			if(codeTypeDB.isPresent()) {
				altCodeList.add(AlternateCode.builder().code(domainAltCode.getCode()).codetype(codeTypeDB.get())
						.entityId(rowid).entityTypeId(entityType).rowid(domainAltCode.getRowid()==null?null:domainAltCode.getRowid())
						.build());
			}
			
		}
		return altCodeList;
	}
	
	private List<AlternateCodeDomain> altCodeDomainMapper(List<AlternateCode> altCodeList){
		List<AlternateCodeDomain> domainAltCode = new ArrayList<AlternateCodeDomain>();
		for(AlternateCode altCode : altCodeList) {
			domainAltCode.add(AlternateCodeDomain.builder().code(altCode.getCode()).codetype(altCode.getCodetype())
					.rowid(altCode.getRowid()).build());
		}
		return domainAltCode;
	}
	
	@Override
	public List<ParentDetail> createParents(List<ParentDetail> parentDomainList, String rowid, EntityType entityType){
		List<ParentDetailRelation> parentRelation = parentDetailModelMapper(parentDomainList, rowid, entityType);
		parentRepo.saveAll(parentRelation);
		return parentDomainList;
	}
	
	@Override
	public List<ParentDetail> updateParents(List<ParentDetail> parentDomainList, String rowid, EntityType entityType){
		List<ParentDetailRelation> parentDetail = parentDetailModelMapper(parentDomainList, rowid, entityType);
		parentRepo.saveAll(parentDetail);
		return parentDomainList;
	}
	
	private List<ParentDetailRelation> parentDetailModelMapper(List<ParentDetail> domainnParentList, String childId, EntityType childType){
		List<ParentDetailRelation> parentRelations = new ArrayList<ParentDetailRelation>();
		for(ParentDetail parent: domainnParentList) {
			EntityType parentType = entityRepo.findByEntityName(parent.getType().toUpperCase()).get(0);
			parentRelations.add(ParentDetailRelation.builder().childId(childId).childType(childType).parentName(parent.getName())
					.parentId(parent.getParentId().toString()).parentType(parentType).build());
		}
		return parentRelations;
	}
	
	@Override
	public List<BdaDomainRelation> createBdaRelation(List<BdaDomainRelation> bdaList, String rowid, EntityType entityType){
		List<BdaRelation> bdaRelation = bdaRelationModelMapper(bdaList, rowid, entityType);
		bdaRelRepo.saveAll(bdaRelation);
		return bdaList;
	}
	
	@Override
	public List<BdaDomainRelation> updateBdaRelation(List<BdaDomainRelation> bdaList, String rowid, EntityType entityType){
		List<BdaRelation> bdaRelation = bdaRelationModelMapper(bdaList, rowid, entityType);
		bdaRelRepo.saveAll(bdaRelation);
		return bdaList;
	}
	
	private List<BdaRelation> bdaRelationModelMapper(List<BdaDomainRelation> domainBdaRelationList, String rowid, EntityType entityType){
		List<BdaRelation> bdaRelation = new ArrayList<BdaRelation>();
		for(BdaDomainRelation domainBdaRel : domainBdaRelationList) {
			Optional<BDA> bdaDB = bdaRepo.findById(domainBdaRel.getBda().getRowid());
			if(bdaDB.isPresent()) {
				bdaRelation.add(BdaRelation.builder().bda(domainBdaRel.getBda()).entityId(rowid).bda(bdaDB.get())
						.entityTypeId(entityType).rowid(domainBdaRel.getRowid()==null?null:domainBdaRel.getRowid())
						.build());
			}
		}
		return bdaRelation;
	}
	
	@Override
	public BdaDomain createBDA(BdaDomain bdaDomain) {
		BDA bda = bdaModelMapper(bdaDomain, new BDA());
		bda = bdaRepo.save(bda);
		EntityType entityType = entityRepo.findByEntityName("BDA").get(0);
		if(bdaDomain.getAlternateCodes()!=null && bdaDomain.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeDomainList = createAltCode(bdaDomain.getAlternateCodes(), bda.getRowid().toString(), entityType);
			bdaDomain.setAlternateCodes(altCodeDomainList);
		}
		bdaDomain.setRowid(bda.getRowid());
		return bdaDomain;
	}
	
	@Override
	public BdaDomain updateBDA(BdaDomain bdaDomain) {
		Optional<BDA> bdaDB = null;
		try {
			bdaDB = bdaRepo.findById(bdaDomain.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(bdaDB.isPresent()) {
			BDA bda = bdaModelMapper(bdaDomain, bdaDB.get());
			bda = bdaRepo.save(bda);
			EntityType entityType = entityRepo.findByEntityName("BDA").get(0);
			if(bdaDomain.getAlternateCodes()!=null && bdaDomain.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeDomainList = updateAltCode(bdaDomain.getAlternateCodes(), bda.getRowid().toString(), entityType);
				bdaDomain.setAlternateCodes(altCodeDomainList);
			}
			bdaDomain.setRowid(bda.getRowid());
			return bdaDomain;
		}
		return null;
	}
	
	private BDA bdaModelMapper(BdaDomain bdaDomain, BDA bda) {
		if(bdaDomain!=null) {
		    Optional<BDAType> bdaTypeDB = bdaTypeRepo.findById(bdaDomain.getBdaType().getRowid());
		    if(bdaTypeDB.isPresent()) {
		    	return bda.builder().bdaType(bdaTypeDB.get()).name(bdaDomain.getName())
						.status(bdaDomain.getStatus()).validFrom(bdaDomain.getValidFrom())
						.validTo(bdaDomain.getValidTo()).rowid(bdaDomain.getRowid()==null?null:bdaDomain.getRowid())
						.build();
		    }
		}
		return null;
	}
	
	@Override
	public CodeTypeDomain createCodeType(CodeTypeDomain domainCodeType) {
		CodeType codeType = codeTypeModelMapper(domainCodeType, new CodeType());
		codeType = codeTypeRepo.save(codeType);
		domainCodeType.setRowid(codeType.getRowid());
		return domainCodeType;
	}
	
	@Override
	public CodeTypeDomain updateCodeType(CodeTypeDomain domainCodeType) {
		Optional<CodeType> codeTypeDB = null;
		try {
			codeTypeDB = codeTypeRepo.findById(domainCodeType.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(codeTypeDB.isPresent()) {
			CodeType codeType = codeTypeModelMapper(domainCodeType, codeTypeDB.get());
			codeType = codeTypeRepo.save(codeType);
			domainCodeType.setRowid(codeType.getRowid());
			return domainCodeType;
		}
		return null;
	}
	
	private CodeType codeTypeModelMapper(CodeTypeDomain domainCodetype, CodeType codeType) {
		if(domainCodetype!=null) {
			return codeType.builder().description(domainCodetype.getDescription()).status(domainCodetype.getStatus())
					.typeCode(domainCodetype.getTypeCode()).typeName(domainCodetype.getTypeName())
					.rowid(domainCodetype.getRowid()==null?null:domainCodetype.getRowid()).build();
		}
		return null;
	}
	
	@Override
	public BdaTypeDomain createBdaType(BdaTypeDomain domainBdaType) {
		BDAType bdaType = bdaTypeModelMapper(domainBdaType, new BDAType());
		bdaType = bdaTypeRepo.save(bdaType);
		domainBdaType.setRowid(bdaType.getRowid());
		return domainBdaType;
	}
	
	@Override
	public BdaTypeDomain updateBdaType(BdaTypeDomain domainBdaType) {
		Optional<BDAType> bdaTypeDB = null;
		try {
			bdaTypeDB = bdaTypeRepo.findById(domainBdaType.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(bdaTypeDB.isPresent()) {
			BDAType bdaType = bdaTypeModelMapper(domainBdaType, bdaTypeDB.get());
			bdaType = bdaTypeRepo.save(bdaType);
			domainBdaType.setRowid(bdaType.getRowid());
			return domainBdaType;
		}
		return null;
	}
	
	private BDAType bdaTypeModelMapper(BdaTypeDomain domainBdaType, BDAType bdaType) {
		if(domainBdaType!=null) {
			return bdaType.builder().bdaGroupType(domainBdaType.getBdaGroupType()).code(domainBdaType.getCode())
					.name(domainBdaType.getName()).owner(domainBdaType.getOwner()).type(domainBdaType.getType())
					.rowid(domainBdaType.getRowid()==null?null:domainBdaType.getRowid()).build();
		}
		return null;
	}

}
