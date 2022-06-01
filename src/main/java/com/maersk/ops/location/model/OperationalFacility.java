package com.maersk.ops.location.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "OPS_FACILITY")
public class OperationalFacility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String name;
	private String extOwned;
	private String status;
	private String extExposed;
	private String facilityUrl;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_detail_id", referencedColumnName = "rowid")
	private OperationalFacilityDetail facilityDetail;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "operationalFacility")
//	private List<FacilityOpeningHours> facilityOpeningHours;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operationalFacility")
	private List<FacilityTransportMode> facilityTransportMode;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "operationalFacility")
//	private List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "operationalFacility")
	private List<FacilityContactDetails> facilityContactDetails;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}	
}
