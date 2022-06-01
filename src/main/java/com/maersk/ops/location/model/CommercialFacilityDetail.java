package com.maersk.ops.location.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
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
@Table(name = "COMM_FAC_DET")
public class CommercialFacilityDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String facilityBrand;
	private String facilityBrandCode;
	private String commercialFacilityType;
	private String internationalDialingCode;
	private String phoneNumber;
	private String commercialFacilityFunction;
	private String exportEnquiriesEmail;
	private String importEnquiriesEmail;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comm_fac_id",referencedColumnName = "rowid")
	private CommercialFacility commercialFacility;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
	
}
