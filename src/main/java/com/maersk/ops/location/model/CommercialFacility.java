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
@Table(name = "COMM_FACILITY")
public class CommercialFacility {
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
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "address_id",referencedColumnName = "rowid")
//	private FacilityAddress facilityAddress;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "commercialFacility")
	private List<CommercialFacilityDetail> commercialFacilityDetails;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "commercialFacility")
//	private List<FacilityOpeningHours> facilityOpeningHours;
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "commercialFacility")
//	private List<AlternateCode> alternateCodes;
	
}
