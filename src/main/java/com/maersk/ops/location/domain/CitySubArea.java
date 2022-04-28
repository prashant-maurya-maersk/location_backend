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
@Table(name = "CITY_SUBAREA")
public class CitySubArea {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	private String name;
	private String status;
	private String description;
	private String isMaerskCity;
	private String validFrom;
	private String validTo;
	private String portFlag;
	private String latitude;
	private String longitude;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timezone_id",referencedColumnName = "rowid")
	private Timezone timezone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dst_id",referencedColumnName = "rowid")
	private DaylightSavingTime dst;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "olson_id",referencedColumnName = "rowid")
	private OlsonTimezone olsonTimezone;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "subarea")
	private List<AlternateName> alternateNames;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "subarea")
	private List<AlternateCode> alternateCodes;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "subarea")
	private List<GeoFence> geoFences;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "subarea")
	private List<BusinessDefinedArea> bdaDetails;

}
