package com.maersk.ops.location.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.FacilityAddressDomain;
import com.maersk.ops.location.domain.FacilityContactDetailDomain;
import com.maersk.ops.location.domain.FacilityDetailTypeDomain;
import com.maersk.ops.location.domain.FacilityOpeningHoursDomain;
import com.maersk.ops.location.domain.FacilityServiceRelationDomain;
import com.maersk.ops.location.domain.FacilityTransportModeDomain;
import com.maersk.ops.location.domain.OperationalFacilityDetailDomain;
import com.maersk.ops.location.domain.OperationalFacilityDomain;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.FacilityContactDetails;
import com.maersk.ops.location.model.FacilityTransportMode;
import com.maersk.ops.location.model.OperationalFacility;
import com.maersk.ops.location.model.OperationalFacilityDetail;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.OperationalFacilityRepo;
import com.maersk.ops.location.services.FacilityServices;
import com.maersk.ops.location.services.GeoServices;
import com.maersk.ops.location.services.OperationalFacilityService;

@Service
public class OperationalFacilityServiceImpl implements OperationalFacilityService {
	
	@Autowired
	private OperationalFacilityRepo opsFacRepo;
	
	@Autowired
	private FacilityServices facServices;
	
	@Autowired
	private GeoServices geoServices;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Override
	public OperationalFacilityDomain createOpsFacility(OperationalFacilityDomain domainOpsFacility) {
		OperationalFacility opsFac = opsFacilityMapper(domainOpsFacility, new OperationalFacility());
		opsFac = opsFacRepo.save(opsFac);
		if(domainOpsFacility.getFacilityDetail()!=null && domainOpsFacility.getFacilityDetail().getFacilityDetailTypes()!=null && domainOpsFacility.getFacilityDetail().getFacilityDetailTypes().size()>0) {
			List<FacilityDetailTypeDomain> facDetTypeList = facServices.createFacilityDetailType(domainOpsFacility.getFacilityDetail().getFacilityDetailTypes(), opsFac.getFacilityDetail());
			domainOpsFacility.getFacilityDetail().setFacilityDetailTypes(facDetTypeList);
		}
		EntityType entityType = entityRepo.findByEntityName("OPS_FAC").get(0);
		if(domainOpsFacility.getFacilityAddress()!=null) {
			FacilityAddressDomain facAddress = facServices.createFacAddress(domainOpsFacility.getFacilityAddress(), opsFac.getRowid().toString(), entityType);
			domainOpsFacility.setFacilityAddress(facAddress);
		}
		if(domainOpsFacility.getAlternateCodes()!=null && domainOpsFacility.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeList = geoServices.createAltCode(domainOpsFacility.getAlternateCodes(), opsFac.getRowid().toString(), entityType);
			domainOpsFacility.setAlternateCodes(altCodeList);
		}
		if(domainOpsFacility.getFacilityOpeningHours()!=null && domainOpsFacility.getFacilityOpeningHours().size()>0) {
			List<FacilityOpeningHoursDomain> facOpenHrsList = facServices.createOpeningHours(domainOpsFacility.getFacilityOpeningHours(), opsFac.getRowid().toString(), entityType);
			domainOpsFacility.setFacilityOpeningHours(facOpenHrsList);
		}
		if(domainOpsFacility.getFacilityServices()!=null && domainOpsFacility.getFacilityServices().size()>0) {
			List<FacilityServiceRelationDomain> facSerRelList = facServices.createFacilityServiceRelation(domainOpsFacility.getFacilityServices(), opsFac.getRowid().toString(), entityType);
			domainOpsFacility.setFacilityServices(facSerRelList);
		}
		domainOpsFacility.setRowid(opsFac.getRowid());
		return domainOpsFacility;
	}
	
	@Override
	public OperationalFacilityDomain updateOpsFacility(OperationalFacilityDomain domainOpsFacility) {
		Optional<OperationalFacility> opsFacDB = null;
		try {
			opsFacDB = opsFacRepo.findById(domainOpsFacility.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(opsFacDB.isPresent()) {
			OperationalFacility opsFac = opsFacilityMapper(domainOpsFacility, opsFacDB.get());
			opsFac = opsFacRepo.save(opsFac);
			if(domainOpsFacility.getFacilityDetail()!=null && domainOpsFacility.getFacilityDetail().getFacilityDetailTypes()!=null && domainOpsFacility.getFacilityDetail().getFacilityDetailTypes().size()>0) {
				List<FacilityDetailTypeDomain> facDetTypeList = facServices.updateFacilityDetailType(domainOpsFacility.getFacilityDetail().getFacilityDetailTypes(), opsFac.getFacilityDetail());
				domainOpsFacility.getFacilityDetail().setFacilityDetailTypes(facDetTypeList);
			}
			EntityType entityType = entityRepo.findByEntityName("OPS_FAC").get(0);
			if(domainOpsFacility.getFacilityAddress()!=null) {
				FacilityAddressDomain facAddress = facServices.updateFacAddress(domainOpsFacility.getFacilityAddress(), opsFac.getRowid().toString(), entityType);
				domainOpsFacility.setFacilityAddress(facAddress);
			}
			if(domainOpsFacility.getAlternateCodes()!=null && domainOpsFacility.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeList = geoServices.updateAltCode(domainOpsFacility.getAlternateCodes(), opsFac.getRowid().toString(), entityType);
				domainOpsFacility.setAlternateCodes(altCodeList);
			}
			if(domainOpsFacility.getFacilityOpeningHours()!=null && domainOpsFacility.getFacilityOpeningHours().size()>0) {
				List<FacilityOpeningHoursDomain> facOpenHrsList = facServices.updateOpeningHours(domainOpsFacility.getFacilityOpeningHours(), opsFac.getRowid().toString(), entityType);
				domainOpsFacility.setFacilityOpeningHours(facOpenHrsList);
			}
			if(domainOpsFacility.getFacilityServices()!=null && domainOpsFacility.getFacilityServices().size()>0) {
				List<FacilityServiceRelationDomain> facSerRelList = facServices.updateFacilityServiceRelation(domainOpsFacility.getFacilityServices(), opsFac.getRowid().toString(), entityType);
				domainOpsFacility.setFacilityServices(facSerRelList);
			}
			domainOpsFacility.setRowid(opsFac.getRowid());
			return domainOpsFacility;
		}
		return null;
	}
	
	private OperationalFacility opsFacilityMapper(OperationalFacilityDomain domainOpsFac, OperationalFacility opsFac) {
		opsFac = opsFac.builder().extExposed(domainOpsFac.getExtExposed()).extOwned(domainOpsFac.getExtOwned()).facilityUrl(domainOpsFac.getFacilityUrl())
				.name(domainOpsFac.getName()).status(domainOpsFac.getStatus()).build();
		if(domainOpsFac.getFacilityDetail()!=null) {
			OperationalFacilityDetail opsFacDetail = opsFacDetMapper(domainOpsFac.getFacilityDetail(), opsFac);
			opsFac.setFacilityDetail(opsFacDetail);
		}
		if(domainOpsFac.getFacilityTransportMode()!=null && domainOpsFac.getFacilityTransportMode().size()>0) {
			List<FacilityTransportMode> facTransportList = facTransportMapper(domainOpsFac.getFacilityTransportMode(), opsFac);
			opsFac.setFacilityTransportMode(facTransportList);
		}
		if(domainOpsFac.getFacilityContactDetails()!=null && domainOpsFac.getFacilityContactDetails().size()>0) {
			List<FacilityContactDetails> facContactList = contactDetailMapper(domainOpsFac.getFacilityContactDetails(), opsFac);
			opsFac.setFacilityContactDetails(facContactList);
		}
		return opsFac;	
	}
	
	private OperationalFacilityDetail opsFacDetMapper(OperationalFacilityDetailDomain domainOpsFacDetail, OperationalFacility opsFac) {
		OperationalFacilityDetail opsFacDet = OperationalFacilityDetail.builder().gpsFlag(domainOpsFacDetail.getGpsFlag()).gsmFlag(domainOpsFacDetail.getGsmFlag())
				.oceanFreightPricing(domainOpsFacDetail.getOceanFreightPricing()).operationalFacility(opsFac).vesselAgent(domainOpsFacDetail.getVesselAgent())
				.weightLimitCraneKg(domainOpsFacDetail.getWeightLimitCraneKg()).weightLimitYardKg(domainOpsFacDetail.getWeightLimitYardKg())
				.rowid(domainOpsFacDetail.getRowid()==null?null:domainOpsFacDetail.getRowid()).build();
		
		return opsFacDet;
		
	}
	
	private List<FacilityTransportMode> facTransportMapper(List<FacilityTransportModeDomain> domainTransportList, OperationalFacility opsFac){
		List<FacilityTransportMode> facTransportList = new ArrayList<FacilityTransportMode>();
		for(FacilityTransportModeDomain domainTransport: domainTransportList) {
			facTransportList.add(FacilityTransportMode.builder().operationalFacility(opsFac).transportCode(domainTransport.getTransportCode())
					.transportDescription(domainTransport.getTransportDescription()).transportMode(domainTransport.getTransportMode()).validThroughDate(domainTransport.getValidThroughDate())
					.rowid(domainTransport.getRowid()==null?null:domainTransport.getRowid()).build());
		}
		return facTransportList;
	}
	
	private List<FacilityContactDetails> contactDetailMapper(List<FacilityContactDetailDomain> domainContactList, OperationalFacility opsFac){
		List<FacilityContactDetails> facContactList = new ArrayList<FacilityContactDetails>();
		for(FacilityContactDetailDomain domainContact: domainContactList) {
			facContactList.add(FacilityContactDetails.builder().department(domainContact.getDepartment()).emailAddress(domainContact.getEmailAddress()).extension(domainContact.getExtension())
					.faxNmbr(domainContact.getFaxNmbr()).firstName(domainContact.getFirstName()).internaltionalDialingCodeFax(domainContact.getInternaltionalDialingCodeFax())
					.internationalDialingCdMobile(domainContact.getInternationalDialingCdMobile()).internationalDialingCdPhone(domainContact.getInternationalDialingCdPhone())
					.jobTitle(domainContact.getJobTitle()).lastName(domainContact.getLastName()).mobileNumber(domainContact.getMobileNumber()).operationalFacility(opsFac)
					.phoneNumber(domainContact.getPhoneNumber()).validThroughDate(domainContact.getValidThroughDate())
					.rowid(domainContact.getRowid()==null?null:domainContact.getRowid()).build());
		}
		return facContactList;
	}
	
	
	
}
