package com.maersk.ops.location.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FAC_OPENING_HOURS")
public class FacilityOpeningHours {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String openDay;
	private String openTimeHours;
	private String openTimeMinutes;
	private String closeTimeHours;
	private String closeTimeMinutes;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "op_fac_id", referencedColumnName = "rowid")
	private OperationalFacility operationalFacility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comm_fac_id",referencedColumnName = "rowid")
	private CommercialFacility commercialFacility;
	
}
