package com.maersk.ops.location.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FAC_TRANS_MODE")
public class FacilityTransportMode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String transportMode; 
	private String transportCode; 
	private String transportDescription; 
	private String validThroughDate; 
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ops_fac_id",referencedColumnName = "rowid")
	private OperationalFacility operationalFacility;
}
