package com.maersk.ops.location.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "FAC_ADDRESS")
public class FacilityAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String houseNumber;
	private String street;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String postalCode;
	private String district;
	private String territory;
	private String country;
	private String latitude;
	private String longitude;
	
	private String entityId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entityTypeId",referencedColumnName = "rowid")
	private EntityType entityTypeId;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
	
//	@OneToOne(mappedBy = "facilityAddress")
//	private OperationalFacility operationalFacility;
//	
//	@OneToOne(mappedBy = "facilityAddress")
//	private CommercialFacility commFacility;
}
