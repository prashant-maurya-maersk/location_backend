package com.maersk.ops.location.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DST")
public class DaylightSavingTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String code;
	
	private String name;
	
	@OneToOne(mappedBy = "dst")
	private Timezone timezone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dst")
	List<DstDisplacement> dstDisplacements;

}
