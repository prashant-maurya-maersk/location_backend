package com.maersk.ops.location.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityContactDetailDomain {

	private Long rowid;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String department;
	private String internationalDialingCdPhone;
	private String extension;
	private String phoneNumber;
	private String internationalDialingCdMobile;
	private String mobileNumber;
	private String internaltionalDialingCodeFax;
	private String faxNmbr;
	private String emailAddress;
	private String validThroughDate;
}
