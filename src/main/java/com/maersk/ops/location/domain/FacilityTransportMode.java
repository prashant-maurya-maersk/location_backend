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
@Table(name = "FAC_TRAN_MODE")
public class FacilityTransportMode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String transportMode; 
	private String transportCode; 
	private String transportDescription; 
	private String validThroughDate; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "op_fac_id",referencedColumnName = "rowid")
	private OperationalFacility operationalFacility;
}
