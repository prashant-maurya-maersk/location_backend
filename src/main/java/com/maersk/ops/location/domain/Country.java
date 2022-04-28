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
@Table( name = "COUNTRY")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	private String name;
	private String status;
	private String description;
	private String workaroundReason;
	private String validFrom;
	private String validTo;
	private String restricted;
	private String postalCodeMandatory;
	private String stateMandatory;
    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timezone_id",referencedColumnName = "rowid")
	private Timezone timezone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dst_id",referencedColumnName = "rowid")
	private DaylightSavingTime dst;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<AlternateName> alternateNames;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<BusinessDefinedArea> bdaDetails;
	

}
