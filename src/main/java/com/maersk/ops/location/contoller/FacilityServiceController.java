package com.maersk.ops.location.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.domain.FacilityServiceDomain;
import com.maersk.ops.location.domain.FacilityTypeDomain;
import com.maersk.ops.location.domain.ServiceGroupDomain;
import com.maersk.ops.location.services.FacilityServices;

@RestController
public class FacilityServiceController {
	
	@Autowired
	private FacilityServices facilityServices;
	
	@PostMapping("/create/ServiceGroup")
	public ServiceGroupDomain createServiceGroup(ServiceGroupDomain domainServiceGrp) {
		return facilityServices.createServiceGroup(domainServiceGrp);
	}
	
	@PostMapping("/update/ServiceGroup")
	public ServiceGroupDomain updateServiceGroup(ServiceGroupDomain domainServiceGrp) {
		return facilityServices.updateServiceGroup(domainServiceGrp);
	}
	
	@PostMapping("/create/FacService")
	public FacilityServiceDomain createFacService(FacilityServiceDomain domainFacService) {
		return facilityServices.createFacService(domainFacService);
	}
	
	@PostMapping("/update/FacService")
	public FacilityServiceDomain updateFacService(FacilityServiceDomain domainFacService) {
		return facilityServices.updateFacService(domainFacService);
	}
	
	@PostMapping("create/FacType")
	public FacilityTypeDomain createFacType(FacilityTypeDomain dmainFacType) {
		return facilityServices.createFacType(dmainFacType);
	}
	
	@PostMapping("update/FacType")
	public FacilityTypeDomain updateFacType(FacilityTypeDomain dmainFacType) {
		return facilityServices.updateFacType(dmainFacType);
	}
}
