package com.maersk.ops.location.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.domain.CommercialFacilityDomain;
import com.maersk.ops.location.domain.OperationalFacilityDomain;
import com.maersk.ops.location.services.CommercialFacilityService;
import com.maersk.ops.location.services.OperationalFacilityService;

@RestController
public class FacilityController {
	
	@Autowired
	private OperationalFacilityService opsFacService;
	
	@Autowired
	private CommercialFacilityService commFacService;
	
	@PostMapping("/create/OpsFacility")
	public OperationalFacilityDomain createOpsFacility(OperationalFacilityDomain domainOpsFac) {
		return opsFacService.createOpsFacility(domainOpsFac);
	}
	
	@PostMapping("/update/OpsFacility")
	public OperationalFacilityDomain updateOpsFacility(OperationalFacilityDomain domainOpsFac) {
		return opsFacService.updateOpsFacility(domainOpsFac);
	}
	
	@PostMapping("/create/CommFacility")
	public CommercialFacilityDomain createCommFac(CommercialFacilityDomain domainCommFac) {
		return commFacService.createCommFacility(domainCommFac);
	}
	
	@PostMapping("/update/CommFacility")
	public CommercialFacilityDomain updateCommFac(CommercialFacilityDomain domainCommFac) {
		return commFacService.updateCommFacility(domainCommFac);
	}

}
