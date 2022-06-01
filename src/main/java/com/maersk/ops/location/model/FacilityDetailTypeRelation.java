package com.maersk.ops.location.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FAC_DET_TYPE_REL")
public class FacilityDetailTypeRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ops_fac_det_id",referencedColumnName = "rowid")
	private OperationalFacilityDetail operationalFacilityDetail;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fac_type_id",referencedColumnName = "rowid")
	private FacilityType facilityType;
	
	private String relationshipValidThrough;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
	
}
