package com.maersk.ops.location.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FAC_SERVICE")
public class FacilityService {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String description;
	private String vasCodes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "service_grp_id",referencedColumnName = "rowid")
	private ServiceGroups serviceGroupCode;
	
	private String isActive;
}