package com.maersk.ops.location.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FAC_CONTACT_DET")
public class FacilityContactDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ops_fac_id",referencedColumnName = "rowid")
	private OperationalFacility operationalFacility;
}
