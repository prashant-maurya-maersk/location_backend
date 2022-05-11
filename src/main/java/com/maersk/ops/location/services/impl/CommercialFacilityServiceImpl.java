package com.maersk.ops.location.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.domain.AlternateCodeDomain;
import com.maersk.ops.location.domain.CommercialFacilityDetailDomain;
import com.maersk.ops.location.domain.CommercialFacilityDomain;
import com.maersk.ops.location.domain.FacilityAddressDomain;
import com.maersk.ops.location.domain.FacilityOpeningHoursDomain;
import com.maersk.ops.location.domain.FacilityServiceRelationDomain;
import com.maersk.ops.location.model.CommercialFacility;
import com.maersk.ops.location.model.CommercialFacilityDetail;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.repository.CommercialFacilityRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.services.CommercialFacilityService;
import com.maersk.ops.location.services.FacilityServices;
import com.maersk.ops.location.services.GeoServices;

@Service
public class CommercialFacilityServiceImpl implements CommercialFacilityService {
	
	@Autowired
	private CommercialFacilityRepo commFacRepo;
	
	@Autowired
	private FacilityServices facilityServices;
	
	@Autowired
	private GeoServices geoServices;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Override
	public CommercialFacilityDomain createCommFacility(CommercialFacilityDomain domainComFac) {
		CommercialFacility commFacility = commFacMapper(domainComFac, new CommercialFacility());
		if(domainComFac.getCommercialFacilityDetails()!=null && domainComFac.getCommercialFacilityDetails().size()>0) {
			List<CommercialFacilityDetail> commFacDetlist = commFacDetailMapper(domainComFac.getCommercialFacilityDetails(), commFacility);
			commFacility.setCommercialFacilityDetails(commFacDetlist);
		}
		commFacility = commFacRepo.save(commFacility);
		EntityType entityType = entityRepo.findByEntityName("COMM_FAC").get(0);
		if(domainComFac.getFacilityAddress()!=null) {
			FacilityAddressDomain facAddress = facilityServices.createFacAddress(domainComFac.getFacilityAddress(), commFacility.getRowid().toString(), entityType);
			domainComFac.setFacilityAddress(facAddress);
		}
		if(domainComFac.getAlternateCodes()!=null && domainComFac.getAlternateCodes().size()>0) {
			List<AlternateCodeDomain> altCodeList = geoServices.createAltCode(domainComFac.getAlternateCodes(), commFacility.getRowid().toString(), entityType);
			domainComFac.setAlternateCodes(altCodeList);
		}
		if(domainComFac.getFacilityOpeningHours()!=null && domainComFac.getFacilityOpeningHours().size()>0) {
			List<FacilityOpeningHoursDomain> openHrs = facilityServices.createOpeningHours(domainComFac.getFacilityOpeningHours(), commFacility.getRowid().toString(), entityType);
			domainComFac.setFacilityOpeningHours(openHrs);
		}
		if(domainComFac.getFacilityServices()!=null && domainComFac.getFacilityServices().size()>0) {
			List<FacilityServiceRelationDomain> facSerRelList = facilityServices.createFacilityServiceRelation(domainComFac.getFacilityServices(), commFacility.getRowid().toString(), entityType);
			domainComFac.setFacilityServices(facSerRelList);
		}
		domainComFac.setRowid(commFacility.getRowid());
		return domainComFac;
	}
	
	@Override
	public CommercialFacilityDomain updateCommFacility(CommercialFacilityDomain domainComFac) {
		Optional<CommercialFacility> commFacDB = null;
		try {
			commFacDB = commFacRepo.findById(domainComFac.getRowid());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		if(commFacDB.isPresent()) {
			CommercialFacility commFac = commFacMapper(domainComFac, commFacDB.get());
			if(domainComFac.getCommercialFacilityDetails()!=null && domainComFac.getCommercialFacilityDetails().size()>0) {
				List<CommercialFacilityDetail> commFacDetlist = commFacDetailMapper(domainComFac.getCommercialFacilityDetails(), commFac);
				commFac.setCommercialFacilityDetails(commFacDetlist);
			}
			commFacRepo.save(commFac);
			EntityType entityType = entityRepo.findByEntityName("COMM_FAC").get(0);
			if(domainComFac.getFacilityAddress()!=null) {
				FacilityAddressDomain facAddress = facilityServices.updateFacAddress(domainComFac.getFacilityAddress(), commFac.getRowid().toString(), entityType);
				domainComFac.setFacilityAddress(facAddress);
			}
			if(domainComFac.getAlternateCodes()!=null && domainComFac.getAlternateCodes().size()>0) {
				List<AlternateCodeDomain> altCodeList = geoServices.updateAltCode(domainComFac.getAlternateCodes(), commFac.getRowid().toString(), entityType);
				domainComFac.setAlternateCodes(altCodeList);
			}
			if(domainComFac.getFacilityOpeningHours()!=null && domainComFac.getFacilityOpeningHours().size()>0) {
				List<FacilityOpeningHoursDomain> openHrs = facilityServices.updateOpeningHours(domainComFac.getFacilityOpeningHours(), commFac.getRowid().toString(), entityType);
				domainComFac.setFacilityOpeningHours(openHrs);
			}
			if(domainComFac.getFacilityServices()!=null && domainComFac.getFacilityServices().size()>0) {
				List<FacilityServiceRelationDomain> facSerRelList = facilityServices.updateFacilityServiceRelation(domainComFac.getFacilityServices(), commFac.getRowid().toString(), entityType);
				domainComFac.setFacilityServices(facSerRelList);
			}
			domainComFac.setRowid(commFac.getRowid());
		}
		return domainComFac;
	}
	
	private CommercialFacility commFacMapper(CommercialFacilityDomain domainComFac, CommercialFacility commFac) {
		if(domainComFac != null) {
			return commFac.builder().extExposed(domainComFac.getExtExposed()).extOwned(domainComFac.getExtOwned())
					.facilityUrl(domainComFac.getFacilityUrl()).name(domainComFac.getName()).status(domainComFac.getStatus())
					.rowid(domainComFac.getRowid()==null?null:domainComFac.getRowid()).build();
		}
		return null;
	}
	
	private List<CommercialFacilityDetail> commFacDetailMapper(List<CommercialFacilityDetailDomain> domainFacDetailList, CommercialFacility commFac){
		List<CommercialFacilityDetail> commFacDetList = new ArrayList<CommercialFacilityDetail>();
		for(CommercialFacilityDetailDomain domainFacDet : domainFacDetailList) {
			commFacDetList.add(CommercialFacilityDetail.builder().commercialFacility(commFac).commercialFacilityFunction(domainFacDet.getCommercialFacilityFunction())
					.commercialFacilityType(domainFacDet.getCommercialFacilityType()).exportEnquiriesEmail(domainFacDet.getExportEnquiriesEmail())
					.facilityBrand(domainFacDet.getFacilityBrand()).facilityBrandCode(domainFacDet.getFacilityBrandCode()).importEnquiriesEmail(domainFacDet.getImportEnquiriesEmail())
					.internationalDialingCode(domainFacDet.getInternationalDialingCode()).phoneNumber(domainFacDet.getPhoneNumber())
					.rowid(domainFacDet.getRowid()==null?null:domainFacDet.getRowid()).build());
		}
		return commFacDetList;
	}

}
