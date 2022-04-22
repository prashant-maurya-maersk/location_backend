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
@Table(name = "timezone")
public class Timezone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long rowid;
	
	private String code;
	private String name;
	private String offsetMin;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dst_id",referencedColumnName = "rowid")
	private DaylightSavingTime dst;
	
	@OneToOne(mappedBy = "timezone")
	private City city;
}
