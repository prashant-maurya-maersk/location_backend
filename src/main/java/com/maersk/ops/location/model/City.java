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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rowid;
	
	@Column(name = "uuid")
	private String uuid;
	
	private String name;
	private String status;
	private String validFrom;
	private String validTo;
	private String description;
	private String workaroundReason;
	private String portFlag;
	private String isMaerskCity;
	private String latitude;
	private String longitude;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timezone_id",referencedColumnName = "rowid")
	private Timezone timezone;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "olson_id",referencedColumnName = "rowid")
	private OlsonTimezone olsonTimezone;
	
	@PrePersist
	public void initializeUUID() {
	    if (uuid == null) {
	    	uuid = UUID.randomUUID().toString().replace("-", "");
	    }
	}
	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
//	List<AlternateName> alternateNames;
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
//	List<AlternateCode> alternateCodes;
//	
//	@OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
//	List<BDA> bdaDetails;
}
