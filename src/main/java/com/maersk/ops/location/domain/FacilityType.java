package com.maersk.ops.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FAC_TYPE")
public class FacilityType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String code;
	private String name;
	private String uniqueValueFlag;
	private String mustBeFlag;
	private String masterType;
}
