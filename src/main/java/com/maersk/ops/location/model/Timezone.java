package com.maersk.ops.location.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "timezone")
public class Timezone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String code;
	private String name;
	private String offsetMin;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "timezone")
	private DaylightSavingTime dst;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
	
}
