package com.maersk.ops.location.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "FAC_SERVICE_REL")
public class FacilityServiceRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String entityId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "entityTypeId",referencedColumnName = "rowid")
	private EntityType entityTypeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_service_id",referencedColumnName = "rowid")
	private FacilityService facilityService;
	
	private String validThroughDate;

}
