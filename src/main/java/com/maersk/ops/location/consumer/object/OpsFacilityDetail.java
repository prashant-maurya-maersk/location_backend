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
public class OpsFacilityDetail {
	 private String  WeightLimitCraneKg;
	 private String  WeightLimitYardKg;
	 private String  VesselAgent;
	 private String  GPSFlag;
	 private String  GSMFlag;
	 private String  OceanFreightPricing;
	 private String  FacilityBrand;
	 private String  FacilityType;
	 private String  ExportEnquiriesEmail;
	 private String  ImportEnquiriesEmail;
	 private String  FacilityFunction;
	 private String  FacilityFunctionDesc;
	 private String  InternationalDialCode;
	 private String  TelephoneNumber;
	 private List<OpsFacilityType> OpsFacilityType;
}
