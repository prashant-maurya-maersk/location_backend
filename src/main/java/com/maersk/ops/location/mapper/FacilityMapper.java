package com.maersk.ops.location.mapper;

import java.util.List;

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
import com.maersk.ops.location.model.FacilityAddress;
import com.maersk.ops.location.model.FacilityContactDetails;
import com.maersk.ops.location.model.FacilityDetailTypeRelation;
import com.maersk.ops.location.model.FacilityOpeningHours;
import com.maersk.ops.location.model.FacilityServiceRelation;
import com.maersk.ops.location.model.FacilityTransportMode;
import com.maersk.ops.location.model.OperationalFacility;

@Service
public interface FacilityMapper {
	
	public FacilityResponse opsFacilityMapper(OperationalFacility opsFac);
	public FacilityResponse commFacilityMapper(CommercialFacility commFac);
	
	public List<OpsFacilityType> facilityTypeMapper(List<FacilityDetailTypeRelation> opsFacDetTypeList);
	public List<OpsFacilityTransportModes> transportMapper(List<FacilityTransportMode> facTransportList);
	public List<OpsFacilityContactDetail> facContactMapper(List<FacilityContactDetails> facContactList);
	
	public List<OpsFacilityAddress> facAddressMapper(List<FacilityAddress> facAddressList);
	public List<OpsFacilityAlternateCodes> facAltCodeMapper(List<AlternateCode> altCodeList);
	public List<OpsFacilityOpeningHours> openingHrsMapper(List<FacilityOpeningHours> openHrsList);
	public List<OpsFacilityServices> facServiceMapper(List<FacilityServiceRelation> facSerRelList);
	
	public List<OpsFacilityDetail> commFacilityDetailMapper(List<CommercialFacilityDetail> commFacDetList);
}
