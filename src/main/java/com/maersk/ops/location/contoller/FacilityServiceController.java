package com.maersk.ops.location.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.domain.FacilityServiceDomain;
import com.maersk.ops.location.domain.FacilityTypeDomain;
import com.maersk.ops.location.domain.ServiceGroupDomain;
import com.maersk.ops.location.services.FacilityServices;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
public class FacilityServiceController {
	
	@Autowired
	private FacilityServices facilityServices;
	
	@PostMapping("/create/ServiceGroup")
	public ServiceGroupDomain createServiceGroup(@RequestBody ServiceGroupDomain domainServiceGrp) {
		return facilityServices.createServiceGroup(domainServiceGrp);
	}
	
	@PostMapping("/update/ServiceGroup")
	public ServiceGroupDomain updateServiceGroup(@RequestBody ServiceGroupDomain domainServiceGrp) {
		return facilityServices.updateServiceGroup(domainServiceGrp);
	}
	
	@PostMapping("/create/FacService")
	public FacilityServiceDomain createFacService(@RequestBody FacilityServiceDomain domainFacService) {
		return facilityServices.createFacService(domainFacService);
	}
	
	@PostMapping("/update/FacService")
	public FacilityServiceDomain updateFacService(@RequestBody FacilityServiceDomain domainFacService) {
		return facilityServices.updateFacService(domainFacService);
	}
	
	@PostMapping("create/FacType")
	public FacilityTypeDomain createFacType(@RequestBody FacilityTypeDomain dmainFacType) {
		return facilityServices.createFacType(dmainFacType);
	}
	
	@PostMapping("update/FacType")
	public FacilityTypeDomain updateFacType(@RequestBody FacilityTypeDomain dmainFacType) {
		return facilityServices.updateFacType(dmainFacType);
	}
}
