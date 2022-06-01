package com.maersk.ops.location.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.consumer.object.GeographyAlternateCodes;
import com.maersk.ops.location.consumer.object.GeographyAlternateNames;
import com.maersk.ops.location.consumer.object.GeographyBDADetails;
import com.maersk.ops.location.consumer.object.GeographyParentDetails;
import com.maersk.ops.location.consumer.object.GeographyResponse;
import com.maersk.ops.location.model.AlternateCode;
import com.maersk.ops.location.model.AlternateName;
import com.maersk.ops.location.model.BdaRelation;
import com.maersk.ops.location.model.City;
import com.maersk.ops.location.model.CitySubArea;
import com.maersk.ops.location.model.Continent;
import com.maersk.ops.location.model.Country;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.ParentDetailRelation;
import com.maersk.ops.location.model.PostalCode;
import com.maersk.ops.location.model.Site;
import com.maersk.ops.location.model.State;
import com.maersk.ops.location.repository.AlternateCodeRepo;
import com.maersk.ops.location.repository.AlternateNameRepo;
import com.maersk.ops.location.repository.BdaRelationRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.ParentRepo;

@Service
public class GeoResponseMapper implements GeographyMapper {
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private AlternateNameRepo altNameRepo;
	
	@Autowired
	private AlternateCodeRepo altCodeRepo;
	
	@Autowired
	private ParentRepo parentRepo;
	
	@Autowired
	private BdaRelationRepo bdaRelRepo;
	
	@Override
	public GeographyResponse geoCityMapper(City city) {
		String rowId = city.getRowid().toString();
		
		GeographyResponse geoResponse = GeographyResponse.builder().geoType("City").name(city.getName()).status(city.getStatus()).validFrom(city.getValidFrom())
				.validTo(city.getValidTo()).longitude(city.getLongitude()).latitude(city.getLatitude()).timeZone(city.getTimezone().getName()).olsonTimezone(city.getOlsonTimezone().getName())
				.description(city.getDescription()).workaroundReason(city.getWorkaroundReason()).portFlag(city.getPortFlag()).isMaerskCity(city.getIsMaerskCity()).build();
		
		EntityType entityType = entityRepo.findByEntityName("CITY").get(0);
		
		List<AlternateName> altNamesModel = altNameRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateNames> geoAltNames = geoAltNameMapper(altNamesModel);
		geoResponse.setGeographyAlternateNames(geoAltNames);
		
		List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		List<ParentDetailRelation> parentRelModel = parentRepo.findByChildIdAndChildType(rowId, entityType);
		List<GeographyParentDetails> geoParentList = geoParentDetailMapper(parentRelModel);
		geoResponse.setGeographyParentDetails(geoParentList);
		
		List<BdaRelation> bdaDetailModel = bdaRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyBDADetails> geoBdaList = geoBDADetailMapper(bdaDetailModel);
		geoResponse.setGeographyBDADetails(geoBdaList);
		
		return geoResponse;
	}
	
	@Override
    public GeographyResponse geoStateMapper(State state) {
    	String rowId = state.getRowid().toString();
    	
    	GeographyResponse geoResponse = GeographyResponse.builder().name(state.getName()).status(state.getStatus())
    			.description(state.getDescription()).workaroundReason(state.getWorkaroundReason()).validFrom(state.getValidFrom())
    			.validTo(state.getValidTo()).timeZone(state.getTimeZone().getName()).build();
    	
    	EntityType entityType = entityRepo.findByEntityName("STATE").get(0);
    	
    	List<AlternateName> altNamesModel = altNameRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateNames> geoAltNames = geoAltNameMapper(altNamesModel);
		geoResponse.setGeographyAlternateNames(geoAltNames);
		
		List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		List<ParentDetailRelation> parentRelModel = parentRepo.findByChildIdAndChildType(rowId, entityType);
		List<GeographyParentDetails> geoParentList = geoParentDetailMapper(parentRelModel);
		geoResponse.setGeographyParentDetails(geoParentList);
		
		List<BdaRelation> bdaDetailModel = bdaRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyBDADetails> geoBdaList = geoBDADetailMapper(bdaDetailModel);
		geoResponse.setGeographyBDADetails(geoBdaList);
		
		return geoResponse;
    }
    
	@Override
    public GeographyResponse geoSubareaMapper(CitySubArea subarea) {
    	String rowId = subarea.getRowid().toString();
    	
    	GeographyResponse geoResponse = GeographyResponse.builder().name(subarea.getName()).status(subarea.getStatus()).description(subarea.getDescription())
    			.validFrom(subarea.getValidFrom()).validTo(subarea.getValidTo()).portFlag(subarea.getPortFlag()).longitude(subarea.getLongitude())
    			.latitude(subarea.getLatitude()).timeZone(subarea.getTimezone().getName()).olsonTimezone(subarea.getOlsonTimezone().getName()).build();
    	
    	EntityType entityType = entityRepo.findByEntityName("SUBAREA").get(0);
    	
    	List<AlternateName> altNamesModel = altNameRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateNames> geoAltNames = geoAltNameMapper(altNamesModel);
		geoResponse.setGeographyAlternateNames(geoAltNames);
		
		List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		List<ParentDetailRelation> parentRelModel = parentRepo.findByChildIdAndChildType(rowId, entityType);
		List<GeographyParentDetails> geoParentList = geoParentDetailMapper(parentRelModel);
		geoResponse.setGeographyParentDetails(geoParentList);
		
		List<BdaRelation> bdaDetailModel = bdaRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyBDADetails> geoBdaList = geoBDADetailMapper(bdaDetailModel);
		geoResponse.setGeographyBDADetails(geoBdaList);
		
		return geoResponse;
    }
    
	@Override
    public GeographyResponse geoSiteMapper(Site site) {
    	String rowId = site.getRowid().toString();
    	
    	GeographyResponse geoResponse = GeographyResponse.builder().name(site.getName()).status(site.getStatus()).validFrom(site.getValidFrom())
    			.validTo(site.getValidTo()).description(site.getDescription()).workaroundReason(site.getWorkaroundReason()).siteType(site.getSiteType())
    			.gpsFlag(site.getGpsFlag()).gsmFlag(site.getGsmFlag()).streetNumber(site.getStreet()).addressLine1(site.getAddressLine1())
    			.addressLine2(site.getAddressLine2()).addressLine3(site.getAddressLine3()).postalCode(site.getPostalCode()).longitude(site.getLongitude())
    			.latitude(site.getLatitude()).build();
    	
    	EntityType entityType = entityRepo.findByEntityName("SITE").get(0);
    	
    	List<AlternateName> altNamesModel = altNameRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateNames> geoAltNames = geoAltNameMapper(altNamesModel);
		geoResponse.setGeographyAlternateNames(geoAltNames);
		
		List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		List<ParentDetailRelation> parentRelModel = parentRepo.findByChildIdAndChildType(rowId, entityType);
		List<GeographyParentDetails> geoParentList = geoParentDetailMapper(parentRelModel);
		geoResponse.setGeographyParentDetails(geoParentList);
		
		List<BdaRelation> bdaDetailModel = bdaRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyBDADetails> geoBdaList = geoBDADetailMapper(bdaDetailModel);
		geoResponse.setGeographyBDADetails(geoBdaList);
		
		return geoResponse;
    }
    
	@Override
    public GeographyResponse geoPostalMapper(PostalCode postal) {
    	String rowId = postal.getRowid().toString();
    	
    	GeographyResponse geoResponse = GeographyResponse.builder().name(postal.getName()).postalCode(postal.getPostalcode()).status(postal.getStatus())
    			.description(postal.getDescription()).validFrom(postal.getValidFrom()).validTo(postal.getValidTo()).build();
    	
    	EntityType entityType = entityRepo.findByEntityName("POSTAL_CODE").get(0);
    	
    	List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		List<ParentDetailRelation> parentRelModel = parentRepo.findByChildIdAndChildType(rowId, entityType);
		List<GeographyParentDetails> geoParentList = geoParentDetailMapper(parentRelModel);
		geoResponse.setGeographyParentDetails(geoParentList);
		
		List<BdaRelation> bdaDetailModel = bdaRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyBDADetails> geoBdaList = geoBDADetailMapper(bdaDetailModel);
		geoResponse.setGeographyBDADetails(geoBdaList);
		
		return geoResponse;
    }
    
	@Override
    public GeographyResponse geoCountryMapper(Country country) {
    	String rowId = country.getRowid().toString();
    	
    	GeographyResponse geoResponse = GeographyResponse.builder().name(country.getName()).status(country.getStatus()).description(country.getDescription())
    			.workaroundReason(country.getWorkaroundReason()).validFrom(country.getValidFrom()).validTo(country.getValidTo()).restricted(country.getRestricted())
    			.postalCodeMandatoryFlag(country.getPostalCodeMandatory()).stateProvienceMandatory(country.getStateMandatory()).timeZone(country.getTimezone().getName())
    			.build();
    	
    	EntityType entityType = entityRepo.findByEntityName("POSTAL_CODE").get(0);
    	
    	List<AlternateName> altNamesModel = altNameRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateNames> geoAltNames = geoAltNameMapper(altNamesModel);
		geoResponse.setGeographyAlternateNames(geoAltNames);
		
		List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		List<ParentDetailRelation> parentRelModel = parentRepo.findByChildIdAndChildType(rowId, entityType);
		List<GeographyParentDetails> geoParentList = geoParentDetailMapper(parentRelModel);
		geoResponse.setGeographyParentDetails(geoParentList);
		
		List<BdaRelation> bdaDetailModel = bdaRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyBDADetails> geoBdaList = geoBDADetailMapper(bdaDetailModel);
		geoResponse.setGeographyBDADetails(geoBdaList);
		
		return geoResponse;
    }
    
	@Override
    public GeographyResponse geoContinentMapper(Continent continent) {
    	String rowId = continent.getRowid().toString();
    	
    	GeographyResponse geoResponse = GeographyResponse.builder().name(continent.getName()).status(continent.getStatus()).validFrom(continent.getValidFrom())
    			.validTo(continent.getValidTo()).description(continent.getDescription()).workaroundReason(continent.getWorkaroundReason()).build();
    	
    	EntityType entityType = entityRepo.findByEntityName("POSTAL_CODE").get(0);
    	
    	List<AlternateName> altNamesModel = altNameRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateNames> geoAltNames = geoAltNameMapper(altNamesModel);
		geoResponse.setGeographyAlternateNames(geoAltNames);
		
		List<AlternateCode> altCodeModel = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<GeographyAlternateCodes> geoAltCodes = geoAltCodeMapper(altCodeModel);
		geoResponse.setGeographyAlternateCodes(geoAltCodes);
		
		return geoResponse;
    }
	
	private List<GeographyAlternateNames> geoAltNameMapper(List<AlternateName> altNameList ){
		List<GeographyAlternateNames> geoAltNameList = new ArrayList<GeographyAlternateNames>();
		if(altNameList!=null) {
			for(AlternateName altName : altNameList) {
				geoAltNameList.add(GeographyAlternateNames.builder().description(altName.getDescription()).name(altName.getName())
						.status(altName.getStatus()).build());
			}
		}
		return geoAltNameList;
	}
	
	private List<GeographyAlternateCodes> geoAltCodeMapper(List<AlternateCode> altCodeList){
		List<GeographyAlternateCodes> geoAltCodeList = new ArrayList<GeographyAlternateCodes>();
		if(altCodeList!=null) {
			for(AlternateCode altCode :altCodeList) {
				geoAltCodeList.add(GeographyAlternateCodes.builder().code(altCode.getCode()).codeType(altCode.getCodetype().getTypeCode()).build());
			}
		}
		return geoAltCodeList;
	}
	
	private List<GeographyParentDetails> geoParentDetailMapper(List<ParentDetailRelation> parentList){
		List<GeographyParentDetails> geoParentList = new ArrayList<GeographyParentDetails>();
		if(parentList != null) {
			for(ParentDetailRelation parent : parentList) {
				GeographyParentDetails.builder().name(parent.getParentName()).type(parent.getParentType().getEntityName()).build();
			}
		}
		return geoParentList;
	}
	
	private List<GeographyBDADetails> geoBDADetailMapper(List<BdaRelation> bdaDetailList){
		List<GeographyBDADetails> geoBDAList = new ArrayList<GeographyBDADetails>();
		if(bdaDetailList!=null) {
			for(BdaRelation bdaRel: bdaDetailList) {
				geoBDAList.add(GeographyBDADetails.builder().bdaType(bdaRel.getBda().getBdaType().getName()).name(bdaRel.getBda().getName())
				.build());
			}
		}
		return geoBDAList;
	}
	
	
}
