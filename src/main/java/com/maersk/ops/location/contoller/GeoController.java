package com.maersk.ops.location.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maersk.ops.location.domain.CityDomain;
import com.maersk.ops.location.domain.CitySubAreaDomain;
import com.maersk.ops.location.domain.ContinentDomain;
import com.maersk.ops.location.domain.CountryDomain;
import com.maersk.ops.location.domain.PostalCodeDomain;
import com.maersk.ops.location.domain.SiteDomain;
import com.maersk.ops.location.domain.StateDomain;
import com.maersk.ops.location.services.CityService;
import com.maersk.ops.location.services.CitySubareaService;
import com.maersk.ops.location.services.ContinentService;
import com.maersk.ops.location.services.CountryService;
import com.maersk.ops.location.services.PostalCodeService;
import com.maersk.ops.location.services.SiteService;
import com.maersk.ops.location.services.StateService;

@RestController
public class GeoController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CitySubareaService subareaService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private PostalCodeService postalService;
	
	@Autowired
	private ContinentService continentService;
	
	@PostMapping("/create/City")
	public CityDomain createCity(@RequestBody CityDomain city){
		return cityService.createCity(city);
	}
	
	@PostMapping("/update/City")
	public CityDomain updateCity(@RequestBody CityDomain city) {
		return cityService.updateCity(city);
	}
	
	@PostMapping("/create/State")
	public StateDomain createState(@RequestBody StateDomain state) {
		return stateService.createState(state);
	}
	
	@PostMapping("/update/State")
	public StateDomain updateState(@RequestBody StateDomain state) {
		return stateService.updateState(state);
	}
	
	@PostMapping("/create/CitySubarea")
	public CitySubAreaDomain createSubarea(@RequestBody CitySubAreaDomain domainSubarea) {
		return subareaService.createSubarea(domainSubarea);
	}
	
	@PostMapping("/update/CitySubarea")
	public CitySubAreaDomain updateSubarea(CitySubAreaDomain domainSubarea) {
		return subareaService.updateSubarea(domainSubarea);
	}
	
	@PostMapping("/create/Site")
	public SiteDomain createSite(SiteDomain domainSite) {
		return siteService.createSite(domainSite);
	}
	
	@PostMapping("/update/Site")
	public SiteDomain updateSite(SiteDomain domainSite) {
		return siteService.updateSite(domainSite);
	}
	
	@PostMapping("/create/Country")
	public CountryDomain createCountry(CountryDomain domainCountry) {
		return countryService.createCountry(domainCountry);
	}
	
	@PostMapping("/update/Country")
	public CountryDomain updateCountry(CountryDomain domainCountry) {
		return countryService.updateCountry(domainCountry);
	}
	
	@PostMapping("/create/PostalCode")
	public PostalCodeDomain createPostal(PostalCodeDomain domainPostal) {
		return postalService.createPostalCode(domainPostal);
	}
	
	@PostMapping("/update/PostalCode")
	public PostalCodeDomain updatePostal(PostalCodeDomain domainPostal) {
		return postalService.updatePostalCode(domainPostal);
	}
	
	@PostMapping("/create/Continent")
	public ContinentDomain createContinent(ContinentDomain domainContinent) {
		return continentService.createContinent(domainContinent);
	}
	
	@PostMapping("/update/Continent")
	public ContinentDomain updateContinent(ContinentDomain domainContinent) {
		return continentService.updateContinent(domainContinent);
	}
	
}
