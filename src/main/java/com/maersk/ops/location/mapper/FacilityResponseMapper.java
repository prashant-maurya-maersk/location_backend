package com.maersk.ops.location.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maersk.ops.location.consumer.object.FacilityResponse;
import com.maersk.ops.location.consumer.object.OpsFacilityAddress;
import com.maersk.ops.location.consumer.object.OpsFacilityAlternateCodes;
import com.maersk.ops.location.consumer.object.OpsFacilityContactDetail;
import com.maersk.ops.location.consumer.object.OpsFacilityDetail;
import com.maersk.ops.location.consumer.object.OpsFacilityOpeningHours;
import com.maersk.ops.location.consumer.object.OpsFacilityServices;
import com.maersk.ops.location.consumer.object.OpsFacilityTransportModes;
import com.maersk.ops.location.consumer.object.OpsFacilityType;
import com.maersk.ops.location.model.AlternateCode;
import com.maersk.ops.location.model.CommercialFacility;
import com.maersk.ops.location.model.CommercialFacilityDetail;
import com.maersk.ops.location.model.EntityType;
import com.maersk.ops.location.model.FacilityAddress;
import com.maersk.ops.location.model.FacilityContactDetails;
import com.maersk.ops.location.model.FacilityDetailTypeRelation;
import com.maersk.ops.location.model.FacilityOpeningHours;
import com.maersk.ops.location.model.FacilityServiceRelation;
import com.maersk.ops.location.model.FacilityTransportMode;
import com.maersk.ops.location.model.OperationalFacility;
import com.maersk.ops.location.model.OperationalFacilityDetail;
import com.maersk.ops.location.repository.AlternateCodeRepo;
import com.maersk.ops.location.repository.EntityTypeRepo;
import com.maersk.ops.location.repository.FacilityAddressRepo;
import com.maersk.ops.location.repository.FacilityDetailTypeRepo;
import com.maersk.ops.location.repository.FacilityServiceRelationRepo;
import com.maersk.ops.location.repository.OpeningHourRepo;

@Service
public class FacilityResponseMapper implements FacilityMapper {
	
	@Autowired
	private FacilityDetailTypeRepo facDetTypeRepo;
	
	@Autowired
	private FacilityAddressRepo facAddressRepo;
	
	@Autowired
	private EntityTypeRepo entityRepo;
	
	@Autowired
	private AlternateCodeRepo altCodeRepo;
	
	@Autowired
	private OpeningHourRepo openHrsRepo;
	
	@Autowired
	private FacilityServiceRelationRepo facSerRelRepo;
	
	@Override
	public FacilityResponse opsFacilityMapper(OperationalFacility opsFac) {
		FacilityResponse facRes = FacilityResponse.builder().FacilityName(opsFac.getName()).FacilityExtOwned(opsFac.getExtOwned())
				.FacilityExtExposed(opsFac.getExtExposed()).FacilityStatus(opsFac.getStatus()).FacilityURL(opsFac.getFacilityUrl()).build();
		
		List<OpsFacilityDetail> opsFacilityDetailList = opsFacilityDetailMapper(opsFac.getFacilityDetail());
		facRes.setOpsFacilityDetail(opsFacilityDetailList);
		
		List<OpsFacilityTransportModes> transportList = transportMapper(opsFac.getFacilityTransportMode());
		facRes.setOpsTransportModes(transportList);
		
		List<OpsFacilityContactDetail> opsContactList = facContactMapper(opsFac.getFacilityContactDetails());
		facRes.setOpsContactDetail(opsContactList);
		
		EntityType entityType = entityRepo.findByEntityName("OPS_FAC").get(0);
		String rowId = opsFac.getRowid().toString();
		
		List<FacilityAddress> facAddressList = facAddressRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityAddress> opsFacAddList = facAddressMapper(facAddressList);
		facRes.setOpsFacilityAddress(opsFacAddList);
		
		List<AlternateCode> altCodeList = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityAlternateCodes> opsFacAltCodeList = facAltCodeMapper(altCodeList);
		facRes.setOpsFacilityAlternateCodes(opsFacAltCodeList);
		
		List<FacilityOpeningHours> openHrsList = openHrsRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityOpeningHours> opsOpeningHrsList = openingHrsMapper(openHrsList);
		facRes.setOpsFacilityOpeningHours(opsOpeningHrsList);
		
		List<FacilityServiceRelation> facServiceRelList = facSerRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityServices> opsFacSerList = facServiceMapper(facServiceRelList);
		facRes.setOpsFacilityServices(opsFacSerList);
		
		return facRes;	
	}
	
	@Override
	public FacilityResponse commFacilityMapper(CommercialFacility commFac) {
		FacilityResponse facRes = FacilityResponse.builder().FacilityName(commFac.getName()).FacilityExtExposed(commFac.getExtExposed())
				.FacilityExtOwned(commFac.getExtOwned()).FacilityStatus(commFac.getStatus()).FacilityURL(commFac.getFacilityUrl()).build();
		
		List<OpsFacilityDetail> commFacDetail = commFacilityDetailMapper(commFac.getCommercialFacilityDetails());
		facRes.setOpsFacilityDetail(commFacDetail);
		
		EntityType entityType = entityRepo.findByEntityName("COMM_FAC").get(0);
		String rowId = commFac.getRowid().toString();
		
		List<FacilityAddress> facAddressList = facAddressRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityAddress> opsFacAddList = facAddressMapper(facAddressList);
		facRes.setOpsFacilityAddress(opsFacAddList);
		
		List<AlternateCode> altCodeList = altCodeRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityAlternateCodes> opsFacAltCodeList = facAltCodeMapper(altCodeList);
		facRes.setOpsFacilityAlternateCodes(opsFacAltCodeList);
		
		List<FacilityOpeningHours> openHrsList = openHrsRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityOpeningHours> opsOpeningHrsList = openingHrsMapper(openHrsList);
		facRes.setOpsFacilityOpeningHours(opsOpeningHrsList);
		
		List<FacilityServiceRelation> facServiceRelList = facSerRelRepo.findByEntityIdAndEntityTypeId(rowId, entityType);
		List<OpsFacilityServices> opsFacSerList = facServiceMapper(facServiceRelList);
		facRes.setOpsFacilityServices(opsFacSerList);
		
		return facRes;
	}
	
	public List<OpsFacilityDetail> opsFacilityDetailMapper(OperationalFacilityDetail opsFacilityDetail){
		List<OpsFacilityDetail> opsFacDet = new ArrayList<OpsFacilityDetail>();
		if(opsFacilityDetail!=null) {
			opsFacDet.add(OpsFacilityDetail.builder().WeightLimitCraneKg(opsFacilityDetail.getWeightLimitCraneKg())
					.WeightLimitYardKg(opsFacilityDetail.getWeightLimitYardKg()).VesselAgent(opsFacilityDetail.getVesselAgent())
					.GPSFlag(opsFacilityDetail.getGpsFlag()).GSMFlag(opsFacilityDetail.getGsmFlag()).OceanFreightPricing(opsFacilityDetail.getOceanFreightPricing())
					.build());
			List<FacilityDetailTypeRelation> opsFacDetTypeList = facDetTypeRepo.findByOperationalFacilityDetail(opsFacilityDetail);
			List<OpsFacilityType> opsFacType = facilityTypeMapper(opsFacDetTypeList);
			opsFacDet.get(0).setOpsFacilityType(opsFacType);
		}
		return opsFacDet;
	}
	
	@Override
	public List<OpsFacilityType> facilityTypeMapper(List<FacilityDetailTypeRelation> opsFacDetTypeList){
		List<OpsFacilityType> opsFacTypeList = new ArrayList<OpsFacilityType>();
		if(opsFacDetTypeList!=null) {
			for(FacilityDetailTypeRelation opsFacType : opsFacDetTypeList) {
				opsFacTypeList.add(OpsFacilityType.builder().Code(opsFacType.getFacilityType().getCode())
						.Name(opsFacType.getFacilityType().getName()).MasterType(opsFacType.getFacilityType().getMasterType())
						.ValidThroughDate(opsFacType.getRelationshipValidThrough()).build());
			}
		}
		return opsFacTypeList;
	}
	
	@Override
	public List<OpsFacilityTransportModes> transportMapper(List<FacilityTransportMode> facTransportList){
		List<OpsFacilityTransportModes> opsTransportList = new ArrayList<OpsFacilityTransportModes>();
		if(facTransportList!=null) {
			for(FacilityTransportMode facTransport : facTransportList) {
				opsTransportList.add(OpsFacilityTransportModes.builder().TransportCode(facTransport.getTransportCode())
						.TransportDescription(facTransport.getTransportDescription()).TransportMode(facTransport.getTransportMode())
						.ValidThroughDate(facTransport.getValidThroughDate()).build());
			}
		}
		return opsTransportList;
	}
	
	@Override
	public List<OpsFacilityContactDetail> facContactMapper(List<FacilityContactDetails> facContactList){
		List<OpsFacilityContactDetail> opsContactList = new ArrayList<OpsFacilityContactDetail>();
		if(facContactList!=null) {
			for(FacilityContactDetails contact : facContactList) {
				opsContactList.add(OpsFacilityContactDetail.builder().Department(contact.getDepartment()).EmailAddress(contact.getEmailAddress())
						.Extension(contact.getExtension()).FaxNumber(contact.getFaxNmbr()).FirstName(contact.getFirstName()).InternaltionalDialingCodeFax(contact.getInternaltionalDialingCodeFax())
						.InternationalDialingCdMobile(contact.getInternationalDialingCdMobile()).InternationalDialingCdPhone(contact.getInternationalDialingCdPhone())
						.JobTitle(contact.getJobTitle()).LastName(contact.getLastName()).MobileNumber(contact.getMobileNumber()).PhoneNumber(contact.getPhoneNumber())
						.ValidThroughDate(contact.getValidThroughDate()).build());
			}
		}
		return opsContactList;
	}
	
	@Override
	public List<OpsFacilityAddress> facAddressMapper(List<FacilityAddress> facAddressList){
		List<OpsFacilityAddress> opsFacAddressList = new ArrayList<OpsFacilityAddress>();
		if(facAddressList!=null) {
			for(FacilityAddress facAddress: facAddressList) {
				opsFacAddressList.add(OpsFacilityAddress.builder().HouseNumber(facAddress.getHouseNumber()).Street(facAddress.getStreet())
						.AddressLine2(facAddress.getAddressLine2()).AddressLine3(facAddress.getAddressLine3()).City(facAddress.getCity())
						.PostalCode(facAddress.getPostalCode()).District(facAddress.getDistrict()).Territory(facAddress.getTerritory())
						.CountryName(facAddress.getCountry()).Latitude(facAddress.getLatitude()).Longitude(facAddress.getLongitude()).build());
			}
		}
		return opsFacAddressList;
	}
	
	@Override
	public List<OpsFacilityAlternateCodes> facAltCodeMapper(List<AlternateCode> altCodeList){
		List<OpsFacilityAlternateCodes> opsAltCodeList = new ArrayList<OpsFacilityAlternateCodes>();
		if(altCodeList!=null) {
			for(AlternateCode altCode: altCodeList) {
				opsAltCodeList.add(OpsFacilityAlternateCodes.builder().Code(altCode.getCode()).CodeType(altCode.getCodetype().getTypeCode()).build());
			}
		}
		return opsAltCodeList;
	}
	
	@Override
	public List<OpsFacilityOpeningHours> openingHrsMapper(List<FacilityOpeningHours> openHrsList){
		List<OpsFacilityOpeningHours> opsOpenHrsList = new ArrayList<OpsFacilityOpeningHours>();
		if(openHrsList!=null) {
			for(FacilityOpeningHours openHour: openHrsList) {
				opsOpenHrsList.add(OpsFacilityOpeningHours.builder().Day(openHour.getOpenDay()).OpenTimeHours(openHour.getOpenTimeHours())
						.OpenTimeMinutes(openHour.getOpenTimeMinutes()).CloseTimeHours(openHour.getCloseTimeHours()).CloseTimeMinutes(openHour.getCloseTimeMinutes())
						.build());
			}
		}
		return opsOpenHrsList;
	}
	
	@Override
	public List<OpsFacilityServices> facServiceMapper(List<FacilityServiceRelation> facSerRelList){
		List<OpsFacilityServices> facServicesList = new ArrayList<OpsFacilityServices>();
		if(facSerRelList!=null) {
			for(FacilityServiceRelation facService: facSerRelList) {
				facServicesList.add(OpsFacilityServices.builder().ServiceCode(facService.getFacilityService().getServiceGroupCode().getCode())
						.ServiceDescription(facService.getFacilityService().getDescription()).ServiceName(facService.getFacilityService().getName())
						.ValidThroughDate(facService.getValidThroughDate()).build());
			}
		}
		return facServicesList;
	}
	
	@Override
	public List<OpsFacilityDetail> commFacilityDetailMapper(List<CommercialFacilityDetail> commFacDetList){
		List<OpsFacilityDetail> facDetList = new ArrayList<OpsFacilityDetail>();
		if(commFacDetList!=null) {
			for(CommercialFacilityDetail commFacDet : commFacDetList) {
				facDetList.add(OpsFacilityDetail.builder().FacilityBrand(commFacDet.getFacilityBrand()).FacilityType(commFacDet.getCommercialFacilityType())
						.InternationalDialCode(commFacDet.getInternationalDialingCode()).TelephoneNumber(commFacDet.getPhoneNumber())
						.ImportEnquiriesEmail(commFacDet.getImportEnquiriesEmail()).ExportEnquiriesEmail(commFacDet.getExportEnquiriesEmail()).build());
			}
		}
		return facDetList;
	}

}
