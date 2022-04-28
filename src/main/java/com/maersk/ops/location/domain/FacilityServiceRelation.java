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
@Table(name = "FAC_SERVICE_REL")
public class FacilityServiceRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "op_fac_id",referencedColumnName = "rowid")
	private OperationalFacility operationalFacility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_ser_id",referencedColumnName = "rowid")
	private FacilityService facilityService;
	
	private String validThroughDate;

}
