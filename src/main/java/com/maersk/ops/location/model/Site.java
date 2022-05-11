package com.maersk.ops.location.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "SITE")
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String rowIdObject;
	private String description;
	private String workaroundReason;
	private String siteType;
	private String gpsFlag;
	private String gsmFlag;
	private String street;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String postalCode;
	private String latitude;
	private String longitude;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "site")
//	private List<AlternateName> alternateNames;
//	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "site")
//	private List<AlternateCode> alternateCodes;
//	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "site")
//	private List<GeoFence> geoFences;
//	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "site")
//	private List<BDA> bdaDetails;

}
