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
@Table(name = "FAC_SERVICE")
public class FacilityService {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String name;
	private String description;
	private String vasCodes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "service_grp_id",referencedColumnName = "rowid")
	private ServiceGroups serviceGroupCode;
	
	private String isActive;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
}
