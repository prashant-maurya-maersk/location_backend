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
@Table(name = "FAC_OPENING_HOURS")
public class FacilityOpeningHours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String openDay;
	private String openTimeHours;
	private String openTimeMinutes;
	private String closeTimeHours;
	private String closeTimeMinutes;
	
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
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "op_fac_id", referencedColumnName = "rowid")
//	private OperationalFacility operationalFacility;
//	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "comm_fac_id",referencedColumnName = "rowid")
//	private CommercialFacility commercialFacility;
	
}
