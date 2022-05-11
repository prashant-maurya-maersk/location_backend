package com.maersk.ops.location.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.FacilityAddressDomain;
import com.maersk.ops.location.domain.FacilityDetailTypeDomain;
import com.maersk.ops.location.domain.FacilityOpeningHoursDomain;
import com.maersk.ops.location.domain.FacilityServiceDomain;
import com.maersk.ops.location.domain.FacilityServiceRelationDomain;
import com.maersk.ops.location.domain.FacilityTypeDomain;
import com.maersk.ops.location.domain.ServiceGroupDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.FacilityDetailTypeRelation;
import com.maersk.ops.location.model.OperationalFacilityDetail;

@Service
public interface FacilityServices {
	
	public ServiceGroupDomain createServiceGroup(ServiceGroupDomain domainSerGrp);
	public ServiceGroupDomain updateServiceGroup(ServiceGroupDomain domainSerGrp);
	
	public FacilityServiceDomain createFacService(FacilityServiceDomain domainService);
	public FacilityServiceDomain updateFacService(FacilityServiceDomain domainService);
	
	public FacilityTypeDomain createFacType(FacilityTypeDomain domainFacType);
	public FacilityTypeDomain updateFacType(FacilityTypeDomain domainFacType);
	
	public FacilityAddressDomain createFacAddress(FacilityAddressDomain domainAddress, String rowid, EntityType entityType);
	public FacilityAddressDomain updateFacAddress(FacilityAddressDomain domainAddress, String rowid, EntityType entityType);
	
	public List<FacilityOpeningHoursDomain> createOpeningHours(List<FacilityOpeningHoursDomain> domainOpeningHrsList, String rowid, EntityType entityType);
	public List<FacilityOpeningHoursDomain> updateOpeningHours(List<FacilityOpeningHoursDomain> domainOpeningHrsList, String rowid, EntityType entityType);
	
	public List<FacilityServiceRelationDomain> createFacilityServiceRelation(List<FacilityServiceRelationDomain> domainFacSerRelList, String rowid, EntityType entityType);
	public List<FacilityServiceRelationDomain> updateFacilityServiceRelation(List<FacilityServiceRelationDomain> domainFacSerRelList, String rowid, EntityType entityType);
	
	public List<FacilityDetailTypeDomain> createFacilityDetailType(List<FacilityDetailTypeDomain> domainDetailTypeList, OperationalFacilityDetail opsFacDet);
	public List<FacilityDetailTypeDomain> updateFacilityDetailType(List<FacilityDetailTypeDomain> domainDetailTypeList, OperationalFacilityDetail opsFacDet);
}
