package com.maersk.ops.location.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.FacilityAddressDomain;
import com.maersk.ops.location.domain.FacilityDetailTypeDomain;
import com.maersk.ops.location.domain.FacilityOpeningHoursDomain;
import com.maersk.ops.location.domain.FacilityServiceDomain;
import com.maersk.ops.location.domain.FacilityServiceRelationDomain;
import com.maersk.ops.location.domain.FacilityTypeDomain;
import com.maersk.ops.location.domain.ServiceGroupDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.FacilityAddress;
import com.maersk.ops.location.model.FacilityDetailTypeRelation;
import com.maersk.ops.location.model.FacilityOpeningHours;
import com.maersk.ops.location.model.FacilityService;
import com.maersk.ops.location.model.FacilityServiceRelation;
import com.maersk.ops.location.model.FacilityType;
import com.maersk.ops.location.model.OperationalFacilityDetail;
import com.maersk.ops.location.model.ServiceGroups;
import com.maersk.ops.location.repository.FacilityAddressRepo;
import com.maersk.ops.location.repository.FacilityDetailTypeRepo;
import com.maersk.ops.location.repository.FacilityServiceRelationRepo;
import com.maersk.ops.location.repository.FacilityServiceRepo;
import com.maersk.ops.location.repository.FacilityTypeRepo;
import com.maersk.ops.location.repository.OpeningHourRepo;
import com.maersk.ops.location.repository.ServiceGrpRepo;
import com.maersk.ops.location.services.FacilityServices;

@Service
public class FacilityServicesImpl implements FacilityServices {
	
	@Autowired
	private ServiceGrpRepo serviceGrpRepo;
	
	@Autowired
	private FacilityServiceRepo facServiceRepo;
	
	@Autowired
	private FacilityTypeRepo facTypeRepo;
	
	@Autowired
	private FacilityAddressRepo addressRepo;
	
	@Autowired
	private OpeningHourRepo openHrsRepo;
	
	@Autowired
	private FacilityServiceRelationRepo facSerRelRepo;
	
	@Autowired
	private FacilityDetailTypeRepo facDetTypeRepo;
	
	@Override
	public ServiceGroupDomain createServiceGroup(ServiceGroupDomain domainSerGrp) {
		ServiceGroups serGrp = serviceGrpMapper(domainSerGrp, new ServiceGroups());
		serGrp = serviceGrpRepo.save(serGrp);
		domainSerGrp.setRowid(serGrp.getRowid());
		return domainSerGrp;
	}
	
	@Override
	public ServiceGroupDomain updateServiceGroup(ServiceGroupDomain domainSerGrp) {
		Optional<ServiceGroups> serGrpDB = null;
		try {
			serGrpDB = serviceGrpRepo.findById(domainSerGrp.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(serGrpDB.isPresent()) {
			ServiceGroups updatedGrp = serviceGrpMapper(domainSerGrp, serGrpDB.get());
			serviceGrpRepo.save(updatedGrp);
			domainSerGrp.setRowid(updatedGrp.getRowid());
			return domainSerGrp;
		}
		return null;
	}
	
	private ServiceGroups serviceGrpMapper(ServiceGroupDomain domainSerGrp, ServiceGroups serGrp) {
		if(domainSerGrp!=null) {
			return serGrp.builder().classificationCode(domainSerGrp.getClassificationCode()).code(domainSerGrp.getCode())
					.description(domainSerGrp.getDescription()).isActive(domainSerGrp.getIsActive()).name(domainSerGrp.getName())
					.rowid(domainSerGrp.getRowid()==null?null:domainSerGrp.getRowid()).build();
		}
		return null;
	}
	
	@Override
	public FacilityServiceDomain createFacService(FacilityServiceDomain domainService) {
		FacilityService facService = facServiceMapper(domainService, new FacilityService());
		facService = facServiceRepo.save(facService);
		domainService.setRowid(facService.getRowid());
		return domainService;
	}
	
	@Override
	public FacilityServiceDomain updateFacService(FacilityServiceDomain domainService) {
		Optional<FacilityService> facServiceDB = null;
		try {
			facServiceDB = facServiceRepo.findById(domainService.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(facServiceDB.isPresent()) {
			FacilityService facService = facServiceMapper(domainService, facServiceDB.get());
			facServiceRepo.save(facService);
			domainService.setRowid(facService.getRowid());
			return domainService;
		}
		return null;
	}
	
	private FacilityService facServiceMapper(FacilityServiceDomain domainService, FacilityService facService) {
		if(domainService!=null) {
			Optional<ServiceGroups> serGrpDB = serviceGrpRepo.findById(domainService.getServiceGroupCode().getRowid());
			if(serGrpDB.isPresent()) {
				return facService.builder().description(domainService.getDescription()).isActive(domainService.getIsActive())
						.name(domainService.getName()).serviceGroupCode(serGrpDB.get()).vasCodes(domainService.getVasCodes())
						.rowid(domainService.getRowid()==null?null:domainService.getRowid()).build();
			}
		}
		return null;
	}
	
	@Override
	public FacilityTypeDomain createFacType(FacilityTypeDomain domainFacType) {
		FacilityType facType = facTypeMapper(domainFacType, new FacilityType());
		facType = facTypeRepo.save(facType);
		domainFacType.setRowid(facType.getRowid());
		return domainFacType;
	}
	
	@Override
	public FacilityTypeDomain updateFacType(FacilityTypeDomain domainFacType) {
		Optional<FacilityType> facTypeDB = null;
		try {
			facTypeDB = facTypeRepo.findById(domainFacType.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(facTypeDB.isPresent()) {
			FacilityType updatedFacType = facTypeMapper(domainFacType, facTypeDB.get());
			facTypeRepo.save(updatedFacType);
			domainFacType.setRowid(updatedFacType.getRowid());
			return domainFacType;
		}
		return null;
	}
	
	private FacilityType facTypeMapper(FacilityTypeDomain domainFacType, FacilityType facType) {
		if(domainFacType!=null) {
			return facType.builder().code(domainFacType.getCode()).masterType(domainFacType.getMasterType()).mustBeFlag(domainFacType.getMustBeFlag())
					.name(domainFacType.getName()).uniqueValueFlag(domainFacType.getUniqueValueFlag())
					.rowid(domainFacType.getRowid()==null?null:domainFacType.getRowid()).build();
		}
		return null;
	}
	
	@Override
	public FacilityAddressDomain createFacAddress(FacilityAddressDomain domainAddress, String rowid, EntityType entityType) {
		FacilityAddress facAddress = facAddressMapper(domainAddress, new FacilityAddress(), rowid, entityType);
		facAddress = addressRepo.save(facAddress);
		domainAddress.setRowid(facAddress.getRowid());
		return domainAddress;
	}
	
	@Override
	public FacilityAddressDomain updateFacAddress(FacilityAddressDomain domainAddress, String rowid, EntityType entityType) {
		Optional<FacilityAddress> addressDB = null;
		try {
			addressDB = addressRepo.findById(domainAddress.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(addressDB.isPresent()) {
			FacilityAddress updatedAddress = facAddressMapper(domainAddress, addressDB.get(), rowid, entityType);
			addressRepo.save(updatedAddress);
			domainAddress.setRowid(updatedAddress.getRowid());
			return domainAddress;
		}
		return null;
	}
	
	private FacilityAddress facAddressMapper(FacilityAddressDomain domainAddress, FacilityAddress address, String rowid, EntityType entityType) {
		if(domainAddress!=null) {
			return address.builder().addressLine2(domainAddress.getAddressLine2()).addressLine3(domainAddress.getAddressLine3())
					.city(domainAddress.getCity()).country(domainAddress.getCountry()).district(domainAddress.getDistrict()).entityId(rowid)
					.entityTypeId(entityType).houseNumber(domainAddress.getHouseNumber()).latitude(domainAddress.getLatitude())
					.longitude(domainAddress.getLongitude()).postalCode(domainAddress.getPostalCode()).street(domainAddress.getStreet())
					.territory(domainAddress.getTerritory()).rowid(domainAddress.getRowid()==null?null:domainAddress.getRowid()).build();
		}
		return null;
	}
	
	@Override
	public List<FacilityOpeningHoursDomain> createOpeningHours(List<FacilityOpeningHoursDomain> domainOpeningHrs, String rowid, EntityType entityType) {
		List<FacilityOpeningHours> openHrs = openingHoursMapper(domainOpeningHrs, rowid, entityType);
		openHrs = openHrsRepo.saveAll(openHrs);
		return domainOpeningHrs;
	}
	
	@Override
	public List<FacilityOpeningHoursDomain> updateOpeningHours(List<FacilityOpeningHoursDomain> domainOpeningHrs, String rowid, EntityType entityType) {
		List<FacilityOpeningHours> openHrs = openingHoursMapper(domainOpeningHrs, rowid, entityType);
		openHrs = openHrsRepo.saveAll(openHrs);
		return domainOpeningHrs;
	}
	
	private List<FacilityOpeningHours> openingHoursMapper(List<FacilityOpeningHoursDomain> domainOpenHrsList, String rowid, EntityType entityType) {
		List<FacilityOpeningHours> facOpenHrsList = new ArrayList<FacilityOpeningHours>();
		for(FacilityOpeningHoursDomain domainOpenHr : domainOpenHrsList) {
			facOpenHrsList.add(FacilityOpeningHours.builder().closeTimeHours(domainOpenHr.getCloseTimeHours()).closeTimeMinutes(domainOpenHr.getCloseTimeMinutes())
					.entityId(rowid).entityTypeId(entityType).openDay(domainOpenHr.getOpenDay()).openTimeHours(domainOpenHr.getOpenTimeHours()).openTimeMinutes(domainOpenHr.getOpenTimeMinutes())
					.rowid(domainOpenHr.getRowid()==null?null:domainOpenHr.getRowid()).build());
		}
		return facOpenHrsList;
	}
	
	@Override
	public List<FacilityServiceRelationDomain> createFacilityServiceRelation(List<FacilityServiceRelationDomain> domainFacSerRelList, String rowid, EntityType entityType){
		List<FacilityServiceRelation> facSerRelList = facSerRelMapper(domainFacSerRelList, rowid, entityType);
		facSerRelRepo.saveAll(facSerRelList);
		return domainFacSerRelList;
	}
	
	@Override
	public List<FacilityServiceRelationDomain> updateFacilityServiceRelation(List<FacilityServiceRelationDomain> domainFacSerRelList, String rowid, EntityType entityType){
		List<FacilityServiceRelation> facSerRelList = facSerRelMapper(domainFacSerRelList, rowid, entityType);
		facSerRelRepo.saveAll(facSerRelList);
		return domainFacSerRelList;
	}
	
	
	private List<FacilityServiceRelation> facSerRelMapper(List<FacilityServiceRelationDomain> domainSerRelList, String rowid, EntityType entityType){
		List<FacilityServiceRelation> facSerRelList = new ArrayList<FacilityServiceRelation>();
		for(FacilityServiceRelationDomain domainSerRel : domainSerRelList) {
			Optional<FacilityService> facSerDB = facServiceRepo.findById(domainSerRel.getFacilityService().getRowid());
			if(facSerDB.isPresent()) {
				facSerRelList.add(FacilityServiceRelation.builder().entityId(rowid).entityTypeId(entityType).facilityService(facSerDB.get())
						.validThroughDate(domainSerRel.getValidThroughDate()).rowid(domainSerRel.getRowid()==null?null:domainSerRel.getRowid()).build());
			}
		}
		return facSerRelList;
	}
	
	@Override
	public List<FacilityDetailTypeDomain> createFacilityDetailType(List<FacilityDetailTypeDomain> domainDetailTypeList, OperationalFacilityDetail opsFacDet){
		List<FacilityDetailTypeRelation> facDetTypeRel = facDetTypeMapper(domainDetailTypeList, opsFacDet);
		facDetTypeRepo.saveAll(facDetTypeRel);
		return domainDetailTypeList;
	}
	
	@Override
	public List<FacilityDetailTypeDomain> updateFacilityDetailType(List<FacilityDetailTypeDomain> domainDetailTypeList, OperationalFacilityDetail opsFacDet){
		List<FacilityDetailTypeRelation> facDetTypeRel = facDetTypeMapper(domainDetailTypeList, opsFacDet);
		facDetTypeRepo.saveAll(facDetTypeRel);
		return domainDetailTypeList;
	}
	
	private List<FacilityDetailTypeRelation> facDetTypeMapper(List<FacilityDetailTypeDomain> domainFacDetTypeList, OperationalFacilityDetail opsFacDet){
		List<FacilityDetailTypeRelation> facDetTypeRelList = new ArrayList<FacilityDetailTypeRelation>();
		for(FacilityDetailTypeDomain domainFacDetType : domainFacDetTypeList) {
			Optional<FacilityType> facTypeDB = null;
			try {
				facTypeDB = facTypeRepo.findById(domainFacDetType.getFacilityType().getRowid());
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			if(facTypeDB.isPresent()) {
				facDetTypeRelList.add(FacilityDetailTypeRelation.builder().facilityType(facTypeDB.get()).operationalFacilityDetail(opsFacDet).relationshipValidThrough(domainFacDetType.getRelationshipValidThrough())
						.rowid(domainFacDetType.getRowid()==null?null:domainFacDetType.getRowid()).build());
			}
		}
		return facDetTypeRelList;
	}
	
}
