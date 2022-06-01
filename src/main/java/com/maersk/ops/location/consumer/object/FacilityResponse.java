package com.maersk.ops.location.consumer.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResponse {
	  private String FacilityName;
	  private String FacilityType;
	  private String FacilityExtOwned;
	  private String FacilityStatus;
	  private String FacilityExtExposed;
	  private String FacilityURL;
	  private String FacilityDODAAC;
	  private List<OpsFacilityAddress> OpsFacilityAddress;
	  private List<OpsFacilityParentDetails> OpsFacilityParentDetails;
	  private List<OpsFacilityDetail> OpsFacilityDetail;
	  private List<OpsFacilityAlternateCodes> OpsFacilityAlternateCodes;
	  private List<OpsFacilityOpeningHours> OpsFacilityOpeningHours;
	  private List<OpsFacilityTransportModes> OpsTransportModes;
	  private List<OpsFacilityServices> OpsFacilityServices;
	  private List<OpsFacilityFence> OpsFacilityFence;
	  private List<OpsFacilityBDADetails> OpsFacilityBDADetails;
	  private List<OpsFacilityContactDetail> OpsContactDetail;
}
