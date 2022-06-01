package com.maersk.ops.location.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.domain.CommercialFacilityDomain;
import com.maersk.ops.location.domain.OperationalFacilityDomain;
import com.maersk.ops.location.services.CommercialFacilityService;
import com.maersk.ops.location.services.OperationalFacilityService;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
public class FacilityController {
	
	@Autowired
	private OperationalFacilityService opsFacService;
	
	@Autowired
	private CommercialFacilityService commFacService;
	
	@PostMapping("/create/OpsFacility")
	public OperationalFacilityDomain createOpsFacility(@RequestBody OperationalFacilityDomain domainOpsFac) {
		return opsFacService.createOpsFacility(domainOpsFac);
	}
	
	@PostMapping("/update/OpsFacility")
	public OperationalFacilityDomain updateOpsFacility(@RequestBody OperationalFacilityDomain domainOpsFac) {
		return opsFacService.updateOpsFacility(domainOpsFac);
	}
	
	@PostMapping("/create/CommFacility")
	public CommercialFacilityDomain createCommFac(@RequestBody CommercialFacilityDomain domainCommFac) {
		return commFacService.createCommFacility(domainCommFac);
	}
	
	@PostMapping("/update/CommFacility")
	public CommercialFacilityDomain updateCommFac(@RequestBody CommercialFacilityDomain domainCommFac) {
		return commFacService.updateCommFacility(domainCommFac);
	}

}
