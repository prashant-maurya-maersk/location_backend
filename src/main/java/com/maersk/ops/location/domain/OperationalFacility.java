package com.maersk.ops.location.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "OP_FACILITY")
public class OperationalFacility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String extOwned;
	private String status;
	private String extExposed;
	private String facilityUrl;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id",referencedColumnName = "rowid")
	private FacilityAddress facilityAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_detail_id",referencedColumnName = "rowid")
	private OperationalFacilityDetail facilityDetail;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "operationalFacility")
	private List<FacilityOpeningHours> facilityOpeningHours;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operationalFacility")
	private List<FacilityTransportMode> facilityTransportMode;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "operationalFacility")
	private List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "operationalFacility")
	private List<FacilityContactDetails> facilityContactDetails;
	
	

	
}
