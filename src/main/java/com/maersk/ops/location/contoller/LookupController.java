package com.maersk.ops.location.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.lookups.BdaLookup;
import com.maersk.ops.location.lookups.BdaTypeLookup;
import com.maersk.ops.location.lookups.CityLookup;
import com.maersk.ops.location.lookups.CodeTypeLookup;
import com.maersk.ops.location.lookups.ContinentLookup;
import com.maersk.ops.location.lookups.CountryLookup;
import com.maersk.ops.location.lookups.FacilityServiceLookup;
import com.maersk.ops.location.lookups.FacilityTypeLookup;
import com.maersk.ops.location.lookups.LookupService;
import com.maersk.ops.location.lookups.OlsonLookup;
import com.maersk.ops.location.lookups.ServiceGroupLookup;
import com.maersk.ops.location.lookups.StateLookup;
import com.maersk.ops.location.lookups.TimezoneLookup;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
public class LookupController {
	
	@Autowired
	private LookupService lookupService;
	
	@GetMapping("/lookup/timezone")
	public List<TimezoneLookup> timeLookup(){
		return lookupService.getTimezoneLookup();
	}
	
	@GetMapping("/lookup/olson")
	public List<OlsonLookup> olsonLookup(){
		return lookupService.getOlsonLookup();
	}
	
	@GetMapping("/lookup/serviceGroups")
	public List<ServiceGroupLookup> serGrpLkp(){
		return lookupService.getServiceGroup();
	}
	
	@GetMapping("/lookup/city")
	public List<CityLookup> getCities(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "portFlag", required = false) String portFlag,
			@RequestParam(value = "maerskCity", required = false) String maerskCity) {
		return lookupService.getCity(name, status, portFlag, maerskCity);
	}
	
	@GetMapping("/lookup/state")
	public List<StateLookup> getStates(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "status",required = false) String status,
			@RequestParam(value = "workaroundReason",required = false) String workaroundReason){
		return lookupService.getState(name, status, workaroundReason);
	}
	
	@GetMapping("/lookup/country")
	public List<CountryLookup> getCountry(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "status",required = false) String status){
		return lookupService.getCountry(name, status);
	}
	
	@GetMapping("/lookup/continent")
	public List<ContinentLookup> getContinent(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "status",required = false) String status){
		return lookupService.getContinent(name, status);
	}
	
	@GetMapping("/lookup/bda")
	public List<BdaLookup> getBDA(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "status",required = false) String status,
			@RequestParam(value = "bdaType",required = false) Long bdaTypeId){
		return lookupService.getBda(name, status, bdaTypeId);
	}
	
	@GetMapping("/lookup/bdaType")
	public List<BdaTypeLookup> getBdaTypes(){
		return lookupService.getBdaType();
	}
	
	@GetMapping("/lookup/facType")
	public List<FacilityTypeLookup> getFacTypes(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "code",required = false) String code,
			@RequestParam(value = "uniqueValueFlag",required = false) String uniqueValueFlag){
		return lookupService.getFacType(code, name, uniqueValueFlag);
	}
	
	@GetMapping("/lookup/facService")
	public List<FacilityServiceLookup> getFacServices(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "code",required = false) String code,
			@RequestParam(value = "description",required = false) String description,
			@RequestParam(value = "groupCode",required = false) Long grpCodeId,
			@RequestParam(value = "isActive",required = false) String isActive){
		return lookupService.getFacilityServices(name, code, isActive, description, grpCodeId);
	}
	
	@GetMapping("/lookup/codeType")
	public List<CodeTypeLookup> getAltCodeTypes(){
		return lookupService.getTypeCodes();
	}
}
