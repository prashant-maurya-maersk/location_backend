package com.maersk.ops.location.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATE")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String status;
	private String description;
	private String workaroundReason;
	private String validFrom;
	private String validTo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timezone_id",referencedColumnName = "rowid")
	private Timezone timeZone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dst_rowid",referencedColumnName = "rowid")
	private DaylightSavingTime dst;	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "state")
	private List<AlternateName> alternateNames;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "state")
	private List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "state")
	private List<BusinessDefinedArea> bdaDetails;
	
	
	
}
