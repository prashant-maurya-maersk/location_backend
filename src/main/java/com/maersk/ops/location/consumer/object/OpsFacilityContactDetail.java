package com.maersk.ops.location.consumer.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpsFacilityContactDetail {
	private String FirstName;
	private String LastName;
	private String JobTitle;
	private String Department;
	private String InternationalDialingCdPhone;
	private String Extension;
	private String PhoneNumber;
	private String InternationalDialingCdMobile;
	private String MobileNumber;
	private String InternaltionalDialingCodeFax;
	private String FaxNumber;
	private String EmailAddress;
	private String ValidThroughDate;
}
