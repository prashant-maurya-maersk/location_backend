package com.maersk.ops.location.mapper;

import org.springframework.stereotype.Service;

import com.maersk.ops.location.consumer.object.GeographyResponse;
import com.maersk.ops.location.model.City;
import com.maersk.ops.location.model.CitySubArea;
import com.maersk.ops.location.model.Continent;
import com.maersk.ops.location.model.Country;
import com.maersk.ops.location.model.PostalCode;
import com.maersk.ops.location.model.Site;
import com.maersk.ops.location.model.State;

@Service
public interface GeographyMapper {
	
	public GeographyResponse geoCityMapper(City city);
	public GeographyResponse geoStateMapper(State state);
	public GeographyResponse geoSubareaMapper(CitySubArea subarea);
	public GeographyResponse geoSiteMapper(Site site);
	public GeographyResponse geoPostalMapper(PostalCode postal);
	public GeographyResponse geoCountryMapper(Country country);
	public GeographyResponse geoContinentMapper(Continent continent);

}
